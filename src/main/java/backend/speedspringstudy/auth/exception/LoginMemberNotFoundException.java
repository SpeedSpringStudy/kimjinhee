package backend.speedspringstudy.auth.exception;

import backend.speedspringstudy.common.exception.BusinessException;
import backend.speedspringstudy.common.exception.ErrorCode;

public class LoginMemberNotFoundException extends BusinessException {
  public LoginMemberNotFoundException() {
    super(ErrorCode.LOGIN_MEMBER_NOT_FOUND);
  }
}
