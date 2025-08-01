package backend.speedspringstudy.productOption.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class ProductOptionNotFoundException extends BusinessException {
    public ProductOptionNotFoundException() {
        super(ErrorCode.PRODUCT_OPTION_NOT_FOUND);
    }
}
