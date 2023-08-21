package me.jwt_spring.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "mentors")
public class Mentor extends Users {
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    private List<Intern> internList;


    public Mentor(Integer mentorId, String username, String encodedPassword, Set<Role> authorities) {
        super(mentorId, username, encodedPassword, authorities);
    }
}
