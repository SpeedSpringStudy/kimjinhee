package backend.speedspringstudy.wish.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class WishNotFoundException extends BusinessException {
    public WishNotFoundException() {
        super(ErrorCode.WISH_NOT_FOUND);
    }
}
