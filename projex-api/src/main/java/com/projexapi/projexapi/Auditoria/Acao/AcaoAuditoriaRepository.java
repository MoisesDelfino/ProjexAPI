package com.projexapi.projexapi.Auditoria.Acao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AcaoAuditoriaRepository extends PagingAndSortingRepository<AcaoAuditoria, Long>,
        QuerydslPredicateExecutor<AcaoAuditoria> {
}
