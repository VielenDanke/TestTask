package kz.danke.test.task.model;

import kz.danke.test.task.service.annotation.constraints.EmailConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "USERNAME", nullable = false)
    @NotNull(message = "Username cannot be null")
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotNull(message = "Password cannot be null")
    private String password;
    @Column(name = "NUMBER")
    private Integer number = 0;
    @Column(name = "BIRTH_DATE")
    private String dateOfBirth;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(name = "EMAIL", nullable = false)
    @NotNull(message = "Email cannot be null")
    @EmailConstraint
    private String email;
    @Column(name = "ACTIVATION_CODE")
    private String activationCode;
    @OneToMany(mappedBy = "user")
    private List<Message> messages;
    @Transient
    private MultipartFile multipartFile;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<GrantedAuthority> authorities = new HashSet<>();
}
