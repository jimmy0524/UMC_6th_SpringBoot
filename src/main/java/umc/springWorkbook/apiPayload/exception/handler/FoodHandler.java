package umc.springWorkbook.apiPayload.exception.handler;

import umc.springWorkbook.apiPayload.code.BaseErrorCode;
import umc.springWorkbook.apiPayload.exception.GeneralException;

public class FoodHandler extends GeneralException {

    public FoodHandler(BaseErrorCode code) {
        super(code);
    }
}
