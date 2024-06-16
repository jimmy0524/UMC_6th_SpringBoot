package umc.springWorkbook.validation.annotation;

import umc.springWorkbook.validation.validator.AlreadyMissionValidator;
import umc.springWorkbook.validation.validator.StoreExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyMissionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyMission {

    String message() default "이미 도전 중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}