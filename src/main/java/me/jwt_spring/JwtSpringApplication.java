package me.jwt_spring;

import me.jwt_spring.models.Users;
import me.jwt_spring.models.Role;
import me.jwt_spring.repository.RoleRepository;
import me.jwt_spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JwtSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSpringApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
        return args ->{
//            if(roleRepository.findByAuthority("MENTOR").isPresent()) return;
//            Role adminRole = roleRepository.save(new Role("MENTOR"));
//            roleRepository.save(new Role("USER"));
//
//            Set<Role> roles = new HashSet<>();
//            roles.add(adminRole);
//
//            Users admin = new Users(1, "admin", passwordEncode.encode("password"), roles);
//
//            userRepository.save(admin);
        };
    }
}
