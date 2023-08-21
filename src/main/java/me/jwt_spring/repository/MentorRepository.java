package me.jwt_spring.repository;

import me.jwt_spring.models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {

}
