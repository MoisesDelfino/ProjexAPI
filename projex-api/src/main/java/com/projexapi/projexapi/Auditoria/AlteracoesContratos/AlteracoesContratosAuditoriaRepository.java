package com.projexapi.projexapi.Auditoria.AlteracoesContratos;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlteracoesContratosAuditoriaRepository extends PagingAndSortingRepository<AlteracoesContratosAuditoria, Long>,
        QuerydslPredicateExecutor<AlteracoesContratosAuditoria> {
}
