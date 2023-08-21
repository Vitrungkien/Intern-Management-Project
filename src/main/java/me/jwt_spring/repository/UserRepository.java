package me.jwt_spring.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.jwt_spring.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
}
