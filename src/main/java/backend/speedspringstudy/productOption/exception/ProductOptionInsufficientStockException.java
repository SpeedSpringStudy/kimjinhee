package backend.speedspringstudy.productOption.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class ProductOptionInsufficientStockException extends BusinessException {
    public ProductOptionInsufficientStockException() {
        super(ErrorCode.PRODUCT_OPTION_INSUFFICIENT_STOCK);
    }
}
