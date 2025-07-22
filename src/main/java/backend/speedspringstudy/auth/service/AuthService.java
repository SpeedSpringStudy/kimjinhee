package backend.speedspringstudy.auth.service;

import backend.speedspringstudy.Member.dao.MemberDAO;
import backend.speedspringstudy.auth.dto.LoginRequestDTO;
import backend.speedspringstudy.auth.dto.LoginResponseDTO;
import backend.speedspringstudy.Member.entity.Member;
import backend.speedspringstudy.auth.exception.LoginInvalidPasswordException;
import backend.speedspringstudy.auth.exception.LoginMemberNotFoundException;
import backend.speedspringstudy.auth.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberDAO memberDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO request, HttpServletResponse response) {

        Member member = memberDAO.findByEmail(request.getEmail())
                .orElseThrow(LoginMemberNotFoundException::new);

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new LoginInvalidPasswordException();
        }

        String accessToken = jwtTokenProvider.generateAccessToken(member.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getEmail());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }
}