package info.nmrony.spring.tutorials.security_rbac.domain.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

import org.springframework.stereotype.Component;

import info.nmrony.spring.tutorials.security_rbac.services.security.UserService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = UsernameExistConstraintValidator.class)
public @interface UsernameExistConstraint {
    String message() default "{UsernameExistConstraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

@Component
@RequiredArgsConstructor
class UsernameExistConstraintValidator implements ConstraintValidator<UsernameExistConstraint, String> {
    private final UserService userService;

    @Override
    public void initialize(UsernameExistConstraint arg0) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext arg1) {
        return Objects.isNull(username) || !userService.findByUsername(username).isPresent();
    }
}
