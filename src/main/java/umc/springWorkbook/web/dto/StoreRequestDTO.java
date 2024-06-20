package umc.springWorkbook.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import umc.springWorkbook.domain.enums.FoodType;

import java.util.List;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO {

        @NotBlank
        String content;

        @NotNull
        Float star;

        List<MultipartFile> images;
    }

    @Getter
    public static class CreateDTO {

        @NotBlank
        String address;

        @NotNull
        FoodType category;

        @NotNull
        Long region;

        @NotBlank
        String name;

        List<MultipartFile> images;
    }
}
