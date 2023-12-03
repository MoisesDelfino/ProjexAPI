package com.projexapi.projexapi.Projeto;

import com.projexapi.projexapi.Etapa.Etapa;
import com.projexapi.projexapi.Etapa.EtapaService;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Setor.SetorService;
import com.projexapi.projexapi.Utils.Message;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProjetoService {
    private ProjetoRepository projetoRepository;

    private EtapaService etapaService;

    public Projeto criarProjeto(SetorService setorService,
                                Long idSetor,
                                ProjetoRepresentation.Criar criar) {


        Setor setor = setorService.buscarUmSetor(idSetor);

        Long maiorSequencial = this.buscaMaiorSequencial();

        Projeto projetoCriado = this.projetoRepository.save(Projeto.builder()
                .setor(setor)
                .sequencial(maiorSequencial + 1)
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .justificativa(criar.getJustificativa())
                .objetivo(criar.getObjetivo())
                .observacao(criar.getObservacao())
                .data_previsao_inicio(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_inicio(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_previsao_fim(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_fim(LocalDateTime.parse("1900-01-01T00:00:00"))
                .orcamento_inicial(criar.getOrcamento_inicial())
                .custo_previsto(BigDecimal.valueOf(0))
                .custo_real(criar.getCusto_real())
                .status(criar.getStatus())
                .stakeholders(criar.getStakeholders())
                .beneficios(criar.getBeneficios())
                .premissa(criar.getPremissa())
                .restricao(criar.getRestricao())
                .responsavel(criar.getResponsavel())
                .dias_prazo_finalizacao((criar.getDias_prazo_finalizacao()))
                .ativo(Ativo.A)
                .arquivos(criar.getArquivos())
                .percentual_conclusao(BigDecimal.valueOf(0))
                .build());


        return projetoCriado;
    }


    public Page<Projeto> buscarTodos(Pageable pageable) {

        return this.projetoRepository.findAll(pageable);
    }


    public Page<Projeto> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.projetoRepository.findAll(filtroURI, pageable);

    }

    public List<Projeto> buscarTodos(Predicate filtroURI) {
        return (List<Projeto>) this.projetoRepository.findAll(filtroURI);

    }

    public Projeto buscarUmProjeto(Long idProjeto) {

        return this.getProjeto(idProjeto);

    }

    public BigDecimal calculaPercentualConclusao(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<Etapa> acoes = projeto.getEtapaList();

        int tamanhoListaAcoes = acoes.size();

        BigDecimal tamanhoListaAcoesBigDecimal = new BigDecimal(tamanhoListaAcoes);

        BigDecimal cem = new BigDecimal(100);

        BigDecimal valorAcaoPercentual = cem.divide(tamanhoListaAcoesBigDecimal, 2, RoundingMode.HALF_UP);

        List<Etapa> acoesFinalizadas = new ArrayList<>();

        for (Etapa etapa : acoes) {
            if (etapa.getStatus() == Status.F) {
                acoesFinalizadas.add(etapa);
            }
        }
        boolean finaliza = true;

        for (Etapa etapa : acoes) {
            if (etapa.getStatus() != Status.F && etapa.getStatus() != Status.C) {
                finaliza = false;
                break;
            }
        }

        BigDecimal percentualProjeto = new BigDecimal(0);

        int tamanhoLista = acoesFinalizadas.size();

        BigDecimal tamanhoBigDecimal = new BigDecimal(tamanhoLista);

        if (finaliza){
            percentualProjeto = BigDecimal.valueOf(100);
        } else {
            percentualProjeto = tamanhoBigDecimal.multiply(valorAcaoPercentual);
        }

        this.atualizaPercentualConclusao(idProjeto, percentualProjeto);


        return percentualProjeto;
    }

    public ResponseEntity<Message> atualizaPercentualConclusao(Long idProjeto, BigDecimal percentual){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        projeto.setPercentual_conclusao(percentual);

        this.projetoRepository.save(projeto);

        return ResponseEntity.ok(new Message("Percetual de conclusão atualizado"));
    }

    public LocalDateTime buscaMenorDataPrevisaoInicio(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<Etapa> acoes = projeto.getEtapaList();

        Etapa primeiraEtapa = acoes.get(0);

        LocalDateTime data_previsao_inicio = primeiraEtapa.getData_previsao_inicio();

        for (Etapa etapa : projeto.getEtapaList()) {
            if (etapa.getData_previsao_inicio().isBefore(data_previsao_inicio)) {
                data_previsao_inicio = etapa.getData_previsao_inicio();
            }
        }

        return data_previsao_inicio;
    }

    public BigDecimal calculaCustoPrevisto(Long idProjeto) {
        List<Etapa> acoes = this.buscarUmProjeto(idProjeto).getEtapaList();

        BigDecimal custoPrevisto = BigDecimal.ZERO;

        for (Etapa etapa : acoes) {
            custoPrevisto = custoPrevisto.add(etapa.getCusto());
        }

        return custoPrevisto;
    }

    public ResponseEntity<Message> incrementaCustoPrevisto(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        BigDecimal custoPrevisto = this.calculaCustoPrevisto(idProjeto);

        projeto.setCusto_previsto(custoPrevisto);

        projetoRepository.save(projeto);

        return ResponseEntity.ok(new Message("Custo previsto inscrementado!"));
    }

    public ResponseEntity<Message> decrementaCustoPrevisto(Long idEtapa){
        Etapa etapa = this.etapaService.buscarUmaEtapa(idEtapa);

        BigDecimal custoEtapa = etapa.getCusto();

        Projeto projeto = etapa.getProjeto();

        BigDecimal custoPrevisto = projeto.getCusto_previsto().subtract(custoEtapa);

        projeto.setCusto_previsto(custoPrevisto);

        projetoRepository.save(projeto);

        return ResponseEntity.ok(new Message("Custo previsto decrementado!"));

    }

    public Long buscaMaiorSequencial(){
        Projeto projeto = this.projetoRepository.findFirstByOrderBySequencialDesc();
        if (projeto != null) {
            return projeto.getSequencial();
        } else {
             return 0L;
        }
    }

    public LocalDateTime buscaMaiorDataPrevisaoFim(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<Etapa> acoes = projeto.getEtapaList();

        Etapa primeiraEtapa = acoes.get(0);

        LocalDateTime data_previsao_fim = primeiraEtapa.getData_previsao_fim();

        for (Etapa etapa : projeto.getEtapaList()) {
            if (etapa.getData_previsao_fim().isAfter(data_previsao_fim)) {
                data_previsao_fim = etapa.getData_previsao_fim();
            }
        }

        return data_previsao_fim;
    }

    public LocalDateTime buscaMaiorDataFim(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<Etapa> acoes = projeto.getEtapaList();

        Etapa primeiraEtapa = acoes.get(0);

        LocalDateTime data_fim = primeiraEtapa.getData_fim();

        for (Etapa etapa : projeto.getEtapaList()) {
            if (etapa.getData_fim().isAfter(data_fim)) {
                data_fim = etapa.getData_fim();
            }
        }

        return data_fim;
    }


    private Projeto getProjeto(Long idProjeto) {

        Optional<Projeto> projetoAtual =
                this.projetoRepository.findById(idProjeto);

        if (projetoAtual.isPresent()) {
            return projetoAtual.get();
        } else {
            throw new NotfoundException("Projeto não encontrado");
        }
    }

    public ResponseEntity<Message> atualizarDatasProjeto(Long idProjeto){

        LocalDateTime menorDataPrevisaoInicio = this.buscaMenorDataPrevisaoInicio(idProjeto);

        LocalDateTime maiorDataPrevisaoFim = this.buscaMaiorDataPrevisaoFim(idProjeto);

        LocalDateTime maiorDataFim = this.buscaMaiorDataFim(idProjeto);

        Projeto projeto = this.getProjeto(idProjeto);

        projeto.setData_previsao_inicio(menorDataPrevisaoInicio);
        projeto.setData_previsao_fim(maiorDataPrevisaoFim);
        projeto.setData_fim(maiorDataFim);

        projetoRepository.save(projeto);

        return ResponseEntity.ok(new Message("Datas alteradas!"));
    }


    public Projeto atualizar(
            Long idProjeto,
            ProjetoRepresentation.Atualizar atualizar) {

        Projeto projeto = this.getProjeto(idProjeto);

        Projeto projetoParaAtualizar = Projeto.builder()
                .id(idProjeto)
                .sequencial(projeto.getSequencial())
                .setor(projeto.getSetor())
                .nome(atualizar.getNome())
                .descricao(atualizar.getDescricao())
                .justificativa(atualizar.getJustificativa())
                .objetivo(atualizar.getObjetivo())
                .observacao(atualizar.getObservacao())
                .data_previsao_inicio(projeto.getData_previsao_inicio())
                .data_inicio(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_previsao_fim(projeto.getData_previsao_fim())
                .data_fim(LocalDateTime.parse("1900-01-01T00:00:00"))
                .orcamento_inicial(atualizar.getOrcamento_inicial())
                .custo_previsto(BigDecimal.valueOf(0))
                .custo_real(atualizar.getCusto_real())
                .status(atualizar.getStatus())
                .stakeholders(atualizar.getStakeholders())
                .beneficios(atualizar.getBeneficios())
                .premissa(atualizar.getPremissa())
                .restricao(atualizar.getRestricao())
                .responsavel(atualizar.getResponsavel())
                .dias_prazo_finalizacao((atualizar.getDias_prazo_finalizacao()))
                .riscoList(atualizar.getRiscoList())
                .ativo(Ativo.A)
                .arquivos(atualizar.getArquivos())
                .percentual_conclusao(BigDecimal.valueOf(0))
                .build();

        return this.projetoRepository.save(projetoParaAtualizar);

    }

    public Projeto inativar(Long idProjeto) {

        Projeto projeto = this.buscarUmProjeto(idProjeto);

        projeto.setAtivo(Ativo.I);


        return this.projetoRepository.save(projeto);
    }

    public ResponseEntity<Message> deletaArquivo(Long idProjeto, String fileName){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<String> arquivos = projeto.getArquivos();

        arquivos.remove(fileName);

        this.projetoRepository.save(projeto);

        return ResponseEntity.ok(new Message("OK"));

    }

    public Projeto iniciarProjeto(Long idProjeto) {


        Projeto projeto = this.getProjeto(idProjeto);

        projeto.setData_inicio(LocalDateTime.now());
        projeto.setData_fim(LocalDateTime.parse("1900-01-01T00:00:00"));
        projeto.setStatus(Status.I);


        return this.projetoRepository.save(projeto);

    }

    public Boolean existemAcoesEmAberto(Long idProjeto){
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        List<Etapa> acoes = projeto.getEtapaList();

        Boolean existem = false;

        for (Etapa etapa : acoes){
            if (etapa.getStatus() != Status.C && etapa.getStatus() != Status.F){
                existem = true;
                break;
            }
        }
        return existem;
    }

    public ResponseEntity<Message> finalizarProjeto(Long idProjeto){

            LocalDateTime maiorDataFim = this.buscaMaiorDataFim(idProjeto);

            Projeto projeto = this.buscarUmProjeto(idProjeto);

            projeto.setStatus(Status.F);
            projeto.setData_fim(maiorDataFim);
            projetoRepository.save(projeto);
            return ResponseEntity.ok(new Message("Projeto finalizado."));

    }

    public Page<Projeto> buscarProjetosPorStatus(Status status, Pageable pageable) {
        return projetoRepository.findByStatus(status, pageable);
    }

    public Page<Projeto> buscarProjetosPorSetor(Long idSetor, Pageable pageable) {
        return projetoRepository.findBySetorId(idSetor, pageable);
    }


    public Boolean verificaSePodeEditarProjetoPorStatus(Long idProjeto) {
        Projeto projeto = this.buscarUmProjeto(idProjeto);

        if (projeto.getStatus() == Status.F || projeto.getStatus() == Status.C) {
            return false;
        } else {
            return true;
        }
    }


}
