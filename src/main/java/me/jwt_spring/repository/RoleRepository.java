package me.jwt_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.jwt_spring .models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByAuthority(String authority);
}
