package com.projexapi.projexapi.Contrato;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Projeto.ProjetoService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ContratoService {
    private ContratoRepository contratoRepository;

    public Page<Contrato> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.contratoRepository.findAll(filtroURI, pageable);

    }
    public Page<Contrato> buscarTodos(Pageable pageable) {
        return this.contratoRepository.findAll(pageable);

    }
    public Contrato buscarUmContrato(Long idContrato) {

        return this.getContrato(idContrato);

    }
    private Contrato getContrato(Long idContrato) {

        Optional<Contrato> contratoAtual =
                this.contratoRepository.findById(idContrato);

        if (contratoAtual.isPresent()) {
            return contratoAtual.get();
        } else {
            throw new NotfoundException("Contrato não encontrado");
        }
    }
    public Contrato criarContrato(ProjetoService projetoService,
                                  Long idProjeto,
                                  ContratoRepresentation.Criar criar) {


        Projeto projeto = projetoService.buscarUmProjeto(idProjeto);

        Contrato contratoCriado = this.contratoRepository.save(Contrato.builder()
                .projeto(projeto)
                .data_vigencia(criar.getData_vigencia())
                .razao_social(criar.getRazao_social())
                .numero_contrato(criar.getNumero_contrato())
                .objeto(criar.getObjeto())
                .valor_inicial(criar.getValor_inicial())
                .valor_final(criar.getValor_final())
                .ativo(Ativo.A)
                .build());


        return contratoCriado;
    }
    public Contrato atualizar(
            Long idContrato,
            ContratoRepresentation.Atualizar atualizar) {

        this.getContrato(idContrato);

        Contrato contratoParaAtualizar = Contrato.builder()
                .id(idContrato)
                .data_vigencia(atualizar.getData_vigencia())
                .razao_social(atualizar.getRazao_social())
                .numero_contrato(atualizar.getNumero_contrato())
                .objeto(atualizar.getObjeto())
                .valor_inicial(atualizar.getValor_inicial())
                .valor_final(atualizar.getValor_final())
                //.projeto(atualizar.getProjeto())
                .ativo(atualizar.getAtivo())
                .build();

        return this.contratoRepository.save(contratoParaAtualizar);

    }
    public Contrato inativar(Long idContrato) {

        Contrato contrato = this.buscarUmContrato(idContrato);


        return this.contratoRepository.save(Contrato.builder()
                .id(idContrato)
                .data_vigencia(contrato.getData_vigencia())
                .razao_social(contrato.getRazao_social())
                .numero_contrato(contrato.getNumero_contrato())
                .objeto(contrato.getObjeto())
                .valor_inicial(contrato.getValor_inicial())
                .valor_final(contrato.getValor_final())
                .projeto(contrato.getProjeto())
                .ativo(Ativo.I)
                .build());
    }
}
