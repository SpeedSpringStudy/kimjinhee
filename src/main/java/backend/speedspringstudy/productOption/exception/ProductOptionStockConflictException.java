package backend.speedspringstudy.productOption.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class ProductOptionStockConflictException extends BusinessException {
    public ProductOptionStockConflictException() {
        super(ErrorCode.PRODUCT_OPTION_STOCK_CONFLICT);
    }
}
