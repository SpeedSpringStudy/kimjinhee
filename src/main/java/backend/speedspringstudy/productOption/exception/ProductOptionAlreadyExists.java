package backend.speedspringstudy.productOption.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class ProductOptionAlreadyExists extends BusinessException {
    public ProductOptionAlreadyExists() {
        super(ErrorCode.PRODUCT_OPTION_ALREADY_EXISTS);
    }
}
