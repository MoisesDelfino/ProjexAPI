package com.projexapi.projexapi.Etapa;

import com.projexapi.projexapi.Enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EtapaRepository extends PagingAndSortingRepository<Etapa, Long>,
        QuerydslPredicateExecutor<Etapa> {

    Page<Etapa> findByStatus(Status status, Pageable pageable);

    int countByStatus(Status status);

}

