package backend.speedspringstudy.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final ErrorCode SERVER_ERROR = ErrorCode.INTERNAL_SERVER_ERROR;

    // 유효성 검사 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach((fieldError) -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity
                .status(ErrorCode.INVALID_INPUT_VALUE.getStatus())
                .body(new ErrorResponse(
                        ErrorCode.INVALID_INPUT_VALUE.getCode(),
                        ErrorCode.INVALID_INPUT_VALUE.getMessage(),
                        errors
                ));
    }

    // 비즈니스 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(
                        errorCode.getCode(),
                        errorCode.getMessage(),
                        null
                ));
    }

    // 나머지 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedExceptions(Exception e) {
        return ResponseEntity
                .status(SERVER_ERROR.getStatus())
                .body(new ErrorResponse(
                        SERVER_ERROR.getCode(),
                        SERVER_ERROR.getMessage(),
                        null
                ));
    }
}