package backend.speedspringstudy.option.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class OptionAlreadyExistsException extends BusinessException {
    public OptionAlreadyExistsException() {
        super(ErrorCode.OPTION_ALREADY_EXISTS);
    }
}
