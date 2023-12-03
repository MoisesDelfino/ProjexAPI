package com.projexapi.projexapi.Auditoria.Projeto;

import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Projeto.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjetoAuditoriaRepository extends PagingAndSortingRepository<ProjetoAuditoria, Long>,
        QuerydslPredicateExecutor<ProjetoAuditoria> {
}