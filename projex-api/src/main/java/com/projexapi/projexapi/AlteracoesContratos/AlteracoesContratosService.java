package com.projexapi.projexapi.AlteracoesContratos;

import com.projexapi.projexapi.Comentario.Comentario;
import com.projexapi.projexapi.Comentario.ComentarioRepresentation;
import com.projexapi.projexapi.Contrato.Contrato;
import com.projexapi.projexapi.Contrato.ContratoService;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Exceptions.NotfoundException;
import com.projexapi.projexapi.Projeto.Projeto;
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
public class AlteracoesContratosService {

    private AlteracoesContratosRepository alteracoesContratosRepository;

    public AlteracoesContratos criarAlteracaoContrato(ContratoService contratoService,
                                                      Long idContrato,
                                                      AlteracoesContratoRepresentation.Criar criar) {


        Contrato contrato = contratoService.buscarUmContrato(idContrato);

        AlteracoesContratos AlteracaoContratoCriada = this.alteracoesContratosRepository.save(AlteracoesContratos.builder()
                .contrato(contrato)
                .valor(criar.getValor())
                .tipo(criar.getTipo())
                .ativo(Ativo.A)
                .build());


        return AlteracaoContratoCriada;
    }

    public Page<AlteracoesContratos> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.alteracoesContratosRepository.findAll(filtroURI, pageable);

    }

    public Page<AlteracoesContratos> buscarTodos(Pageable pageable) {
        return this.alteracoesContratosRepository.findAll(pageable);

    }

    public AlteracoesContratos buscarUmaAlteracaoContrato(Long idAlteracaoContrato) {

        return this.getAlteracaoContrato(idAlteracaoContrato);

    }
    private AlteracoesContratos getAlteracaoContrato(Long idAlteracaoContrato) {

        Optional<AlteracoesContratos> alteracaoContratoAtual =
                this.alteracoesContratosRepository.findById(idAlteracaoContrato);

        if (alteracaoContratoAtual.isPresent()) {
            return alteracaoContratoAtual.get();
        } else {
            throw new NotfoundException("Alteração de contrato não encontrado");
        }
    }
    public AlteracoesContratos atualizar(
            Long idAlteracaoContrato,
            AlteracoesContratoRepresentation.Atualizar atualizar) {

        this.getAlteracaoContrato(idAlteracaoContrato);

        AlteracoesContratos alteracaoContratoParaAtualizar = AlteracoesContratos.builder()
                .id(idAlteracaoContrato)
                .valor(atualizar.getValor())
                .tipo(atualizar.getTipo())
                .ativo(atualizar.getAtivo())
                .build();

        return this.alteracoesContratosRepository.save(alteracaoContratoParaAtualizar);

    }
    public AlteracoesContratos inativar(Long idAlteracaoContrato) {

        AlteracoesContratos alteracoesContratos = this.buscarUmaAlteracaoContrato(idAlteracaoContrato);

        return this.alteracoesContratosRepository.save(AlteracoesContratos.builder()
                .id(idAlteracaoContrato)
                .contrato(alteracoesContratos.getContrato())
                .valor(alteracoesContratos.getValor())
                .tipo(alteracoesContratos.getTipo())
                .ativo(Ativo.I)
                .build());
    }
    public Page<AlteracoesContratos> buscarAlteracoesPorContrato(Long idContrato, Pageable pageable) {
        return alteracoesContratosRepository.findByContratoId(idContrato, pageable);
    }
}
