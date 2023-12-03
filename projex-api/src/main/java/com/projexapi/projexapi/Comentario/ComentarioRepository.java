package com.projexapi.projexapi.Comentario;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComentarioRepository extends PagingAndSortingRepository<Comentario, Long>,
        QuerydslPredicateExecutor<Comentario> {
}
