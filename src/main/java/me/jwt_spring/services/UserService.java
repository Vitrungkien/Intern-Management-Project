package me.jwt_spring.services;

import me.jwt_spring.ThrowException.UserNotFoundException;
import me.jwt_spring.models.Intern;
import me.jwt_spring.models.Users;
import me.jwt_spring.repository.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import me.jwt_spring.repository.UserRepository;

@Service
@Primary
public abstract class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternRepository internRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }

    public Intern updateIntern(Intern updatedUser) {
        Intern existingUser = internRepository.findById(updatedUser.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));

    // Update user's information
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPosition(updatedUser.getPosition());

    // Save the updated user
        return internRepository.save(existingUser);
    }

}
