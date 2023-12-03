package com.projexapi.projexapi.Auditoria.Responsavel;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResponsavelAuditoriaRepository extends PagingAndSortingRepository<ResponsavelAuditoria, Long>,
        QuerydslPredicateExecutor<ResponsavelAuditoria> {
}
