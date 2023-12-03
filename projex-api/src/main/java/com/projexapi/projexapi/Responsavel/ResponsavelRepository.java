package com.projexapi.projexapi.Responsavel;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResponsavelRepository extends PagingAndSortingRepository<Responsavel, Long>,
        QuerydslPredicateExecutor<Responsavel> {
}
