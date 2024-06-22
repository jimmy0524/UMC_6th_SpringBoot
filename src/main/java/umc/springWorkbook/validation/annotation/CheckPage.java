package umc.springWorkbook.validation.annotation;

import umc.springWorkbook.validation.validator.AlreadyMissionValidator;
import umc.springWorkbook.validation.validator.PageRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageRangeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "page의 범위가 너무 작습니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}