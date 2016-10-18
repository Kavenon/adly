package pl.edu.agh.student.model;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails, SaltSource {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;

    @ElementCollection(targetClass = UserRole.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
	private Set<UserRole> userRole = new HashSet<>(0);

	public User() {
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole.stream().map(x-> new SimpleGrantedAuthority(x.getAuthority())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

    @Override
    public Object getSalt(UserDetails user) {
        return user.getUsername()+ "12415";
    }
}
