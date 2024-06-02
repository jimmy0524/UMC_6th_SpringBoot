package umc.springWorkbook.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.springWorkbook.apiPayload.code.BaseErrorCode;
import umc.springWorkbook.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
