package backend.speedspringstudy.option.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class OptionNotFoundException extends BusinessException {
    public OptionNotFoundException() {
        super(ErrorCode.OPTION_NOT_FOUND);
    }
}
