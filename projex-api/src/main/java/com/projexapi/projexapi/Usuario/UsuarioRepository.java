package com.projexapi.projexapi.Usuario;

import com.projexapi.projexapi.Setor.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, JpaRepository<Usuario, Long>,
        QuerydslPredicateExecutor<Usuario> {
    Optional<Usuario> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
