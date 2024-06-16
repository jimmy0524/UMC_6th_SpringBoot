package umc.springWorkbook.apiPayload.exception.handler;

import umc.springWorkbook.apiPayload.code.BaseErrorCode;
import umc.springWorkbook.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {

    public RegionHandler(BaseErrorCode code) {
        super(code);
    }
}
