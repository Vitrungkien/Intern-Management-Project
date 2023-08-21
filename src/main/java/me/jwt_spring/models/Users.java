package me.jwt_spring.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
	@Column(unique=true)
    private String username;
    private String password;
	@Column(name = "FullName")
	private String fullName;
	@Column(name = "Email", unique = true)
	private String email;
	@Column(name = "Position")
	private String position;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    public Users() {
		super();
		authorities = new HashSet<>();
	}

	public Users(Integer userId, String username, String password, Set<Role> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.email = null;
		this.fullName = null;
		this.position = null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* If you want account locking capabilities create variables and ways to set them for the methods below */
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}
