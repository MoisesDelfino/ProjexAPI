package com.projexapi.projexapi.Projeto;

import com.projexapi.projexapi.Enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProjetoRepository extends PagingAndSortingRepository<Projeto, Long>,
            QuerydslPredicateExecutor<Projeto> {

    Page<Projeto> findByStatus(Status status, Pageable pageable);

    Page<Projeto> findBySetorId(Long idSetor, Pageable pageable);


    int countByStatus(Status status);

    Projeto findFirstByOrderBySequencialDesc();
}

