package backend.speedspringstudy.auth.jwt.handler;

import backend.speedspringstudy.common.exception.ErrorCode;
import backend.speedspringstudy.common.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.JWT_UNAUTHORIZED.getCode(),
                ErrorCode.JWT_UNAUTHORIZED.getMessage(),
                null
        );

        response.setStatus(ErrorCode.JWT_UNAUTHORIZED.getStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}