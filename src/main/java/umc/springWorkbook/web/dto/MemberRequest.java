package umc.springWorkbook.web.dto;

import lombok.Getter;
import umc.springWorkbook.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @Size(min = 5, max = 20)
        String address;

        @Size(min = 5, max = 20)
        String specAddress;

        @ExistCategories
        List<Long> foodLikeList;
    }
}
