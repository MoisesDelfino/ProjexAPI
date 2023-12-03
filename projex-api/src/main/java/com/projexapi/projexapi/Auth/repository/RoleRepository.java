package com.projexapi.projexapi.Auth.repository;

import com.projexapi.projexapi.Enums.ERole;
import com.projexapi.projexapi.Auth.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
