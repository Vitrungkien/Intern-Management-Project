package me.jwt_spring.controllers;

import me.jwt_spring.models.Mentor;
import me.jwt_spring.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mentor")
@CrossOrigin("*")
public class MentorController {
    @Autowired
    private MentorRepository mentorRepository;
    
    @GetMapping("/")
    public String helloAdmineController(){
        return "Mentor level access";
    }

    @GetMapping("/getAll")
    List<Mentor> getAllMentors(){
        return mentorRepository.findAll();
    }

}
