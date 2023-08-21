package me.jwt_spring.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
@Entity
@Table(name = "interns")
public class Intern extends Users{
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Intern(Integer internId, String username, String encodedPassword, Set<Role> authorities) {
        super(internId, username, encodedPassword, authorities);
    }

    public Intern() {

    }
}
