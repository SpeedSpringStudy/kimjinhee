package backend.speedspringstudy.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "입력값이 올바르지 않습니다."),
    BUSINESS_ERROR(HttpStatus.BAD_REQUEST, "C002", "요청을 처리할 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 오류가 발생했습니다."),

    JWT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "J001", "인증되지 않은 사용자입니다."),
    JWT_ACCESS_DENIED(HttpStatus.FORBIDDEN, "J002", "접근 권한이 없습니다."),

    LOGIN_MEMBER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A001", "존재하지 않는 사용자입니다."),
    LOGIN_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "A002", "비밀번호가 올바르지 않습니다."),

    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "M001", "이미 사용 중인 이메일입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M002", "존재하지 않는 사용자입니다."),

    PRODUCT_NAME_INVALID(HttpStatus.BAD_REQUEST, "P001", "잘못된 상품명 입력입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P002", "존재하지 않는 상품입니다."),

    OPTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "O001", "이미 존재하는 옵션명입니다."),
    OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "O002", "존재하지 않는 옵션입니다."),

    PRODUCT_OPTION_ALREADY_EXISTS(HttpStatus.CONFLICT, "PO001", "이미 존재하는 상품 옵션명입니다."),
    PRODUCT_OPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "PO002", "존재하지 않는 상품 옵션입니다."),
    PRODUCT_OPTION_INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "P003", "해당 상품 옵션의 재고가 부족합니다."),
    PRODUCT_OPTION_STOCK_CONFLICT(HttpStatus.CONFLICT, "P004", "재고 차감 중 충돌이 발생했습니다."),

    CATEGORY_ALREADY_EXISTS(HttpStatus.CONFLICT, "C001", "이미 존재하는 카테고리입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "존재하지 않는 카테고리입니다."),

    WISH_ALREADY_EXISTS(HttpStatus.CONFLICT, "W001", "이미 위시리스트에 추가된 상품입니다."),
    WISH_NOT_FOUND(HttpStatus.NOT_FOUND, "W002", "위시리스트에 존재하지 않는 상품입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}