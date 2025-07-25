package backend.speedspringstudy.wish.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class WishAlreadyExistsException extends BusinessException {
    public WishAlreadyExistsException() {
        super(ErrorCode.WISH_ALREADY_EXISTS);
    }
}
