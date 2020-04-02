package kz.danke.test.task.model;

import kz.danke.test.task.service.annotation.constraints.EmailConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Column(name = "USERNAME", nullable = false, unique = true)
    @NotBlank(message = "Username cannot be null")
    @Size(min = 3, max = 128, message = "Invalid username")
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotBlank(message = "Password cannot be null")
    private String password;
    @Column(name = "NUMBER")
    private Integer number = 0;
    @Column(name = "BIRTH_DATE")
    private String dateOfBirth;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Email cannot be null")
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
