package com.projexapi.projexapi.Auditoria.Setor;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SetorAuditoriaRepository extends PagingAndSortingRepository<SetorAuditoria, Long>,
        QuerydslPredicateExecutor<SetorAuditoria>{
}
