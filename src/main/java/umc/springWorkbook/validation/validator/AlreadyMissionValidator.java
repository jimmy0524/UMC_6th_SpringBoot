package umc.springWorkbook.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.service.StoreCommandService;
import umc.springWorkbook.validation.annotation.AlreadyMission;
import umc.springWorkbook.validation.annotation.ExistStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class AlreadyMissionValidator implements ConstraintValidator<AlreadyMission, Long> {

    private final StoreCommandService storeCommandService;

    @Override
    public void initialize(AlreadyMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = storeCommandService.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGE.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
