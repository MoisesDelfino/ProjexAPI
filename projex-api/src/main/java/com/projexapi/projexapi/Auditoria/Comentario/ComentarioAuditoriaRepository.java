package com.projexapi.projexapi.Auditoria.Comentario;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComentarioAuditoriaRepository extends PagingAndSortingRepository<ComentarioAuditoria, Long>,
        QuerydslPredicateExecutor<ComentarioAuditoria> {
}
