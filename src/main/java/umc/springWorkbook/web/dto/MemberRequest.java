package umc.springWorkbook.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

public class MemberRequest {

    @Getter
    public static class JoinDto{

        @NotBlank
        @Size(min = 2, max = 10)
        String name;

        @NotNull
        String gender;

        @NotNull
        String birth;

        @NotNull
        Integer birthDay;

        @Size(min = 5, max = 20)
        String address;

        @NotNull
        Double latitude;

        @NotNull
        Double longitude;

        List<Long> foodLikeList;
    }
}
