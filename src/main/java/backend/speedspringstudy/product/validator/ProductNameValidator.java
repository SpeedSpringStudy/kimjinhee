package backend.speedspringstudy.product.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    private static final Pattern ALLOWED_PATTERN =
            Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ()\\[\\]{}+&/_\\s\\-]*$");

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (!ALLOWED_PATTERN.matcher(name).matches()) {
            return false;
        }

        String lower = name.toLowerCase();
        if (lower.contains("카카오") || lower.contains("kakao")) {
            return false;
        }

        return true;
    }
}
