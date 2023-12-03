package com.projexapi.projexapi.Risco;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RiscoRepository extends PagingAndSortingRepository<Risco, Long>,
        QuerydslPredicateExecutor<Risco> {
}
