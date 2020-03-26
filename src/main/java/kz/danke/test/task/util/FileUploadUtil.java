package kz.danke.test.task.util;

import kz.danke.test.task.exceptions.WrongDateException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUploadUtil {

    public String fileUpload(MultipartFile imageFile, String filePath) throws IOException {
        String resultFileName;

        if (imageFile == null) {
            throw new WrongDateException("File cannot be empty");
        } else {
            File uploadDir = new File(filePath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + imageFile.getOriginalFilename();

            imageFile.transferTo(new File(filePath + "/" + resultFileName));
        }
        return resultFileName;
    }
}
