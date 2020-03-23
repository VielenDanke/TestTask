package kz.danke.test.task.model;

import kz.danke.test.task.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "BIRTH_DATE")
    private String dateOfBirth;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ACTIVATION_CODE")
    private String activationCode;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID"))
    @Enumerated(EnumType.STRING)
    private Set<GrantedAuthority> authorities = new HashSet<>();

    public static User setUserDTO(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .dateOfBirth(userDTO.getDateOfBirth())
                .email(userDTO.getEmail())
                .build();
    }
}
