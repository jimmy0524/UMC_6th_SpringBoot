package umc.springWorkbook.apiPayload.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorReasonDTO {
    private Boolean isSuccess;
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
