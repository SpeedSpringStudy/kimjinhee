package backend.speedspringstudy.auth.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class LoginInvalidPasswordException extends BusinessException {
    public LoginInvalidPasswordException() {
        super(ErrorCode.LOGIN_INVALID_PASSWORD);
    }
}
