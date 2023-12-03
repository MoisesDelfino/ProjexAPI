package com.projexapi.projexapi.Contrato;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContratoRepository extends PagingAndSortingRepository<Contrato, Long>,
        QuerydslPredicateExecutor<Contrato> {
}
