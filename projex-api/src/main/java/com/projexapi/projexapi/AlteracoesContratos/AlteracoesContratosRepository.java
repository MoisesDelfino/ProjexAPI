package com.projexapi.projexapi.AlteracoesContratos;

import com.projexapi.projexapi.Projeto.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlteracoesContratosRepository extends PagingAndSortingRepository<AlteracoesContratos, Long>,
        QuerydslPredicateExecutor<AlteracoesContratos>  {
    Page<AlteracoesContratos> findByContratoId(Long idContrato, Pageable pageable);
}
