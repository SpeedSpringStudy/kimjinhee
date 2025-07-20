package backend.speedspringstudy.auth.jwt.handler;

import backend.speedspringstudy.common.exception.ErrorCode;
import backend.speedspringstudy.common.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.JWT_ACCESS_DENIED.getCode(),
                ErrorCode.JWT_ACCESS_DENIED.getMessage(),
                null
        );

        response.setStatus(ErrorCode.JWT_ACCESS_DENIED.getStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}