package backend.speedspringstudy.product.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
