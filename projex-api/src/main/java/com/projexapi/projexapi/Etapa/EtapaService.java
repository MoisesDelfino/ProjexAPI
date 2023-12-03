package com.projexapi.projexapi.Etapa;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EtapaService {
    @Autowired
    private EtapaRepository etapaRepository;

    public Etapa criarEtapa(ProjetoService projetoService,
                           Long idProjeto,
                           EtapaRepresentation.Criar criar) {


        Projeto projeto = projetoService.buscarUmProjeto(idProjeto);

        Etapa etapaCriada = this.etapaRepository.save(Etapa.builder()
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .data_previsao_inicio(criar.getData_previsao_inicio())
                .data_inicio(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_previsao_fim(criar.getData_previsao_fim())
                .data_fim(LocalDateTime.parse("1900-01-01T00:00:00"))
                .custo(criar.getCusto())
                .status(criar.getStatus())
                .projeto(projeto)
                .ativo(Ativo.A)
                .fase(criar.getFase())
                .build());

        return etapaCriada;
    }
    public Etapa buscarUmaEtapa(Long idEtapa) {

        return this.getEtapa(idEtapa);

    }
    private Etapa getEtapa(Long idEtapa) {

        Optional<Etapa> etapaAtual =
                this.etapaRepository.findById(idEtapa);

        if (etapaAtual.isPresent()) {
            return etapaAtual.get();
        } else {
            throw new NotfoundException("Etapa não encontrada");
        }
    }
    public Page<Etapa> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.etapaRepository.findAll(filtroURI, pageable);
    }

    public Page<Etapa> buscarTodos(Pageable pageable) {
        return this.etapaRepository.findAll(pageable);
    }

    public Etapa atualizar(
            Long idEtapa,
            EtapaRepresentation.Atualizar atualizar) {

        this.getEtapa(idEtapa);

        Etapa etapaParaAtualizar = Etapa.builder()
                .id(idEtapa)
                .nome(atualizar.getNome())
                .descricao(atualizar.getDescricao())
                .data_previsao_inicio(atualizar.getData_previsao_inicio())
                .data_inicio(atualizar.getData_inicio())
                .data_previsao_fim(atualizar.getData_previsao_fim())
                .data_fim(atualizar.getData_fim())
                .custo(atualizar.getCusto())
                .status(atualizar.getStatus())
                .ativo(atualizar.getAtivo())
                .fase(atualizar.getFase())
                .build();

        return this.etapaRepository.save(etapaParaAtualizar);

    }
    public Etapa inativar(Long idEtapa) {

        Etapa etapa = this.buscarUmaEtapa(idEtapa);

        etapa.setStatus(Status.I);


        return this.etapaRepository.save(etapa);
    }
    public Etapa iniciarEtapa(Long idEtapa){

        Etapa etapa = this.getEtapa(idEtapa);

        etapa.setStatus(Status.I);
        etapa.setData_inicio(LocalDateTime.now());
        etapa.setData_fim(LocalDateTime.parse("1900-01-01T00:00:00"));


        return this.etapaRepository.save(etapa);

    }

    public Etapa finalizarEtapa(Long idEtapa){
        Etapa etapa = this.getEtapa(idEtapa);

        etapa.setStatus(Status.F);
        etapa.setData_fim(LocalDateTime.now());


        etapaRepository.save(etapa);

        return etapa;
    }
    public Boolean verificaSePodeIniciarEtapaPorStatus(Long idEtapa){
        Etapa etapa = this.buscarUmaEtapa(idEtapa);

        if (etapa.getStatus() == Status.F || etapa.getStatus() == Status.C) {
            return false;
        } else {
            return true;
        }
    }
    public boolean verificaSeEtapaEstaIniciada(Long idEtapa){
        Etapa etapa = this.buscarUmaEtapa(idEtapa);

        if (etapa.getStatus() == Status.I) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificaSePrimeiraEtapaDoProjeto(Long idEtapa){
        Etapa etapa = this.buscarUmaEtapa(idEtapa);

        Projeto projeto = etapa.getProjeto();

        for (Etapa etapaProjeto : projeto.getEtapaList()) {
            if (etapaProjeto.getStatus() == Status.I) {
                return false; // O projeto possui uma etapa iniciada, não é a primeira
            }
        }
        return true;
    }



    public Page<Etapa> buscarEtapasPorStatus(Status status, Pageable pageable) {
        return etapaRepository.findByStatus(status, pageable);
    }
    public List<Etapa> buscarTodos(Predicate filtroURI) {
        return (List<Etapa>) this.etapaRepository.findAll(filtroURI);

    }
    public Page<Etapa> buscaEtapaPrazoFinalizacao(Pageable pageable) {
        Page<Etapa> etapas = this.buscarTodos(pageable);
        List<Etapa> etapasPrazoFinalizacao = new ArrayList<>();
        for (Etapa etapa : etapas.getContent()) {
            if(etapa.getAtivo() != Ativo.I){
                long diasAteConclusao = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), etapa.getData_previsao_fim().toLocalDate());
                if (diasAteConclusao <= etapa.getProjeto().getDias_prazo_finalizacao()) {
                    etapasPrazoFinalizacao.add(etapa);
                }
            }

        }
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), etapasPrazoFinalizacao.size());
        return new PageImpl<>(etapasPrazoFinalizacao.subList(start, end), pageable, etapasPrazoFinalizacao.size());
    }

    public Page<Etapa> buscaEtapaPrazoFinalizacaoPorSetor(Long idSetor, Pageable pageable) {
        Page<Etapa> etapas = this.buscarTodos(QEtapa.etapa.projeto.setor().id.eq(idSetor), pageable);
        List<Etapa> etapasPrazoFinalizacao = new ArrayList<>();
        for (Etapa etapa : etapas.getContent()) {
            if(etapa.getAtivo() != Ativo.I){
                long diasAteConclusao = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), etapa.getData_previsao_fim().toLocalDate());
                if (diasAteConclusao <= etapa.getProjeto().getDias_prazo_finalizacao()) {
                    etapasPrazoFinalizacao.add(etapa);
                }
            }

        }
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), etapasPrazoFinalizacao.size());
        return new PageImpl<>(etapasPrazoFinalizacao.subList(start, end), pageable, etapasPrazoFinalizacao.size());
    }

    public Integer buscaQtdeEtapaPrazoFinalizacao (Pageable pageable){
        Page<Etapa> etapas = this.buscarTodos(pageable);
        List<Etapa> etapasPrazoFinalizacao = new ArrayList<>();
        for (Etapa etapa : etapas.getContent()) {
            if(etapa.getAtivo() != Ativo.I){
            long diasAteConclusao = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), etapa.getData_previsao_fim().toLocalDate());
            if (diasAteConclusao <= etapa.getProjeto().getDias_prazo_finalizacao()) {
                etapasPrazoFinalizacao.add(etapa);
                }
            }
        }

        return etapasPrazoFinalizacao.size();
    }

    public Integer buscaQtdeEtapaPrazoFinalizacaoPorSetor(Long idSetor, Pageable pageable){
        Page<Etapa> etapas = this.buscarTodos(QEtapa.etapa.projeto.setor().id.eq(idSetor), pageable);
        List<Etapa> etapasPrazoFinalizacao = new ArrayList<>();
        for (Etapa etapa : etapas.getContent()) {
            if(etapa.getAtivo() != Ativo.I){
                long diasAteConclusao = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), etapa.getData_previsao_fim().toLocalDate());
                if (diasAteConclusao <= etapa.getProjeto().getDias_prazo_finalizacao()) {
                    etapasPrazoFinalizacao.add(etapa);
                }
            }
        }

        return etapasPrazoFinalizacao.size();
    }

}
