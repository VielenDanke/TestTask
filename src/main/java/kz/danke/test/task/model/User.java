package kz.danke.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NUMBER")
    private Integer number = 0;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<GrantedAuthority> authorities = new HashSet<>();
}
