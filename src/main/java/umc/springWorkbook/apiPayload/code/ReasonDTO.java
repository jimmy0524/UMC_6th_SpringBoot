package umc.springWorkbook.apiPayload.code;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReasonDTO {
    private Boolean isSuccess;
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
