package me.jwt_spring.services;

import jakarta.transaction.Transactional;
import me.jwt_spring.models.Intern;
import me.jwt_spring.models.Mentor;
import me.jwt_spring.repository.InternRepository;
import me.jwt_spring.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MentorService extends UserService{
    @Autowired
    private InternRepository internRepository;
    @Autowired
    private MentorRepository mentorRepository;
    public Intern setMentorForIntern(Integer internId, Integer mentorId) throws EmptyResultDataAccessException {
        Mentor mentor = mentorRepository.findById(mentorId).orElse(null);
        if (mentor == null) {
            throw new EmptyResultDataAccessException("No mentor with provided id", 0);
        }

        Intern intern = internRepository.findById(internId).orElse(null);
        if (intern == null) {
            throw new EmptyResultDataAccessException("No intern with provided id", 0);
        }
        internRepository.setMentorForIntern(internId, mentor);
        return internRepository.findById(internId).orElse(null);
    }
}
