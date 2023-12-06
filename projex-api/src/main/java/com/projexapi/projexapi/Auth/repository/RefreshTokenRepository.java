package com.projexapi.projexapi.Auth.repository;


import com.projexapi.projexapi.Auth.Models.RefreshToken;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(Usuario user);
}

