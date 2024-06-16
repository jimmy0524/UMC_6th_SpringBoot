package umc.springWorkbook.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.repository.FoodRepository;
import umc.springWorkbook.service.StoreCommandService;
import umc.springWorkbook.validation.annotation.ExistCategories;
import umc.springWorkbook.validation.annotation.ExistStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreCommandService storeCommandService;

    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = storeCommandService.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
        }
        System.out.println(isValid);
        return isValid;
    }
}
