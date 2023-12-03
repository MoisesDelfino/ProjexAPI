package com.projexapi.projexapi.Auditoria.Risco;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RiscoAuditoriaRepository extends PagingAndSortingRepository<RiscoAuditoria, Long>,
        QuerydslPredicateExecutor<RiscoAuditoria> {
}
