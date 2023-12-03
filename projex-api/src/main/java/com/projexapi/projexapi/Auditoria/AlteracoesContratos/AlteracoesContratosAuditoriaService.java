package com.projexapi.projexapi.Auditoria.AlteracoesContratos;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class AlteracoesContratosAuditoriaService {
    private AlteracoesContratosAuditoriaRepository alteracoesContratosAuditoriaRepository;

    public Page<AlteracoesContratosAuditoria> buscarTodos(Pageable pageable) {
        return this.alteracoesContratosAuditoriaRepository.findAll(pageable);
    }

    public Page<AlteracoesContratosAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.alteracoesContratosAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public AlteracoesContratosAuditoria criarAlteracoesContratosAuditoria(AlteracoesContratosAuditoriaRepresentation.Criar criar) {


        AlteracoesContratosAuditoria alteracoesContratosAuditoriaCriado = this.alteracoesContratosAuditoriaRepository.save(AlteracoesContratosAuditoria.builder()
                .id_alteracao(criar.getId_alteracao_contrato())
                .valor(criar.getValor())
                .tipo(criar.getTipo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return alteracoesContratosAuditoriaCriado;
    }
}
