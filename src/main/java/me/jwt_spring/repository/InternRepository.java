package me.jwt_spring.repository;

import me.jwt_spring.models.Intern;
import me.jwt_spring.models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Integer> {
    @Modifying
    @Query("UPDATE Intern i SET i.mentor = :mentor WHERE i.userId = :userId")
    void setMentorForIntern(@Param("userId") Integer userId, @Param("mentor") Mentor mentor);

    @Query("SELECT u  FROM Intern u WHERE u.fullName LIKE %:keyword% OR u.email like %:keyword% OR u.position like %:keyword%")
    List<Intern> findByKeyword(String keyword);
}
