package umc.springWorkbook.apiPayload.exception.handler;

import umc.springWorkbook.apiPayload.code.BaseErrorCode;
import umc.springWorkbook.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {

    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}
