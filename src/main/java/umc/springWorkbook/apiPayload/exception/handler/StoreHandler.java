package umc.springWorkbook.apiPayload.exception.handler;

import umc.springWorkbook.apiPayload.code.BaseErrorCode;
import umc.springWorkbook.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
