package com.projexapi.projexapi.Setor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface SetorRepository extends PagingAndSortingRepository<Setor, Long>, JpaRepository<Setor, Long>,
        QuerydslPredicateExecutor<Setor> {
    Optional<Setor> findByNome(String nome);

    Boolean existsByNome(String nome);

}


