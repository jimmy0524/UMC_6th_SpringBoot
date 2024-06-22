package umc.springWorkbook.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.service.StoreCommandService;
import umc.springWorkbook.service.StoreQueryService;
import umc.springWorkbook.validation.annotation.AlreadyMission;
import umc.springWorkbook.validation.annotation.CheckPage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class PageRangeValidator implements ConstraintValidator<CheckPage, Integer> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //페이지 번호 1 부터 시작 -> page 1씩 줄여주기, page 0이하면 오류
        Integer newValue = value - 1;
        boolean isValid = storeQueryService.checkPage(newValue);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_RANGE_ERROR.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
