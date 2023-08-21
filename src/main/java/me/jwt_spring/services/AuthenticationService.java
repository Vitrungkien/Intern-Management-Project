package me.jwt_spring.services;

import java.util.HashSet;
import java.util.Set;

import me.jwt_spring.models.*;
import me.jwt_spring.repository.InternRepository;
import me.jwt_spring.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.jwt_spring.repository.RoleRepository;
import me.jwt_spring.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private InternRepository internRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

//    public Users registerUser(String username, String password){
//
//        String encodedPassword = passwordEncoder.encode(password);
//        Role userRole = roleRepository.findByAuthority("INTERN").get();
//
//        Set<Role> authorities = new HashSet<>();
//
//        authorities.add(userRole);
//
//
//        return userRepository.save(new Users(0, username, encodedPassword, authorities));
//    }

    public Users registerIntern(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("INTERN").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);


        return internRepository.save(new Intern(0,username, encodedPassword, authorities));
    }

    public Users registerMentor(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("MENTOR").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return mentorRepository.save(new Mentor(0, username, encodedPassword, authorities));
    }

    public LoginResponseDTO loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }

}
