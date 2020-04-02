package kz.danke.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MESSAGES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DESCRIPTION", nullable = false)
    @NotBlank(message = "Message cannot be blank")
    @Size(min = 3, max = 2048, message = "The message is not valid")
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
