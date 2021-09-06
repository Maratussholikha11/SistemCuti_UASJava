package org.javajarvis.SistemCuti_UASJava.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "tbl_employee")
@Setter @Getter
@NoArgsConstructor
@Data
public class Employee extends BaseEntity<String> implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "nip", length = 18, unique = true, nullable = false)
    private String nip;

    @Column(name = "nama_lengkap", length = 100, nullable = false)
    private String namaLengkap;

    @Column(name = "divisi", length = 50, nullable = false)
    private String divisi;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
<<<<<<< HEAD
=======

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> result = new ArrayList<SimpleGrantedAuthority>();
        result.add(new SimpleGrantedAuthority("ROLE_" + getRole().getNameRole()));
        return result;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
>>>>>>> bc317f328226ad16b4ecab4924c44b0da14c92d1
}
