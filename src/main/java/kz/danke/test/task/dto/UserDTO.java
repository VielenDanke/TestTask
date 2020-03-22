package kz.danke.test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String dateOfBirth;
    private MultipartFile imageFile;
}
