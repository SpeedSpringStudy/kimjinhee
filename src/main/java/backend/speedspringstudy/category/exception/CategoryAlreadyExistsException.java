package backend.speedspringstudy.category.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class CategoryAlreadyExistsException extends BusinessException {
    public CategoryAlreadyExistsException() {
        super(ErrorCode.CATEGORY_ALREADY_EXISTS);
    }
}
