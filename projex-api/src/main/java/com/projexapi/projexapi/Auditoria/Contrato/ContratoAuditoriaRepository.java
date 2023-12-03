package com.projexapi.projexapi.Auditoria.Contrato;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContratoAuditoriaRepository extends PagingAndSortingRepository<ContratoAuditoria, Long>,
        QuerydslPredicateExecutor<ContratoAuditoria> {
}
