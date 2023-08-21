package me.jwt_spring.controllers;

import me.jwt_spring.models.*;
import me.jwt_spring.repository.InternRepository;
import me.jwt_spring.repository.MentorRepository;
import me.jwt_spring.repository.UserRepository;
import me.jwt_spring.services.AuthenticationService;
import me.jwt_spring.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private InternRepository internRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private MentorService mentorService;

    @GetMapping("/")
    public String helloAdminController(){
        return "Admin access level";
    }

    // Admin add mentor
    @PostMapping("/registerMentor")
    public Users registerMentor(@RequestBody RegistrationDTO body){
        return authenticationService.registerMentor(body.getUsername(), body.getPassword());
    }

    // Admin update thông tin intern theo id
    @PutMapping("/update-intern/{intern-id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody Intern newUser, @PathVariable("intern-id") Integer internId) {
        Optional<Intern> optionalUser = internRepository.findById(internId);

        if (optionalUser.isPresent()) {
            Intern existingUser = optionalUser.get();
            existingUser.setFullName(newUser.getFullName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPosition(newUser.getPosition());

            Intern updatedUser = internRepository.save(existingUser);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "update User successfully", updatedUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "User not found", null)
            );
        }
    }

    // Sửa thông tin mentor
    @PutMapping("/update-mentor/{mentor-id}")
    ResponseEntity<ResponseObject> updateMentor(@RequestBody Mentor updateMentor, @PathVariable("mentor-id") Integer mentorId) {
        Optional<Mentor> optionalMentor = mentorRepository.findById(mentorId);

        if (optionalMentor.isPresent()) {
            Mentor existingUser = optionalMentor.get();
            existingUser.setFullName(updateMentor.getFullName());
            existingUser.setEmail(updateMentor.getEmail());
            existingUser.setPosition(updateMentor.getPosition());

            Mentor updatedUser = mentorRepository.save(existingUser);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "update Mentor successfully", updatedUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("error", "Mentor not found", null)
            );
        }
    }

    // Đặt mentor cho intern
    @PutMapping("/set-mentor/{intern-id}/assign-intern/{mentor-id}")
    public ResponseEntity<?> setMentorForIntern(@PathVariable("intern-id") Integer internId, @PathVariable("mentor-id") Integer mentorId) {
        try {
            Intern newIntern = mentorService.setMentorForIntern(internId, mentorId);
            return ResponseEntity.ok(newIntern);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    // Xóa mentor
    @DeleteMapping("/delete-mentor/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable("id") Integer id) {
        boolean exists = mentorRepository.existsById(id);
        if (exists) {
            mentorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Mentor successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find Mentor to delete", "")
        );
    }

    // Xóa intern
    @DeleteMapping("/delete-intern/{id}")
    ResponseEntity<ResponseObject> deleteIntern(@PathVariable("id") Integer id) {
        boolean exists = internRepository.existsById(id);
        if (exists) {
            internRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find user to delete", "")
        );
    }

    //Tìm kiếm user bằng id
    @GetMapping("/find-user/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable("id") Integer id) {
        Optional<Users> foundUser = userRepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query intern successfully", foundUser)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Cannot find intern with id = "+id, "")
                );
    }
}