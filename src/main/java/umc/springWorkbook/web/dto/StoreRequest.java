package umc.springWorkbook.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class StoreRequest {

    @Getter
    public static class ReviewDTO {

        @NotBlank
        String content;

        @NotNull
        Float star;

        List<MultipartFile> images;
    }
}
