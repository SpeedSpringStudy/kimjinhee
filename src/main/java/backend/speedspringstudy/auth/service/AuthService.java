package backend.speedspringstudy.auth.service;

import backend.speedspringstudy.auth.dto.LoginRequestDTO;
import backend.speedspringstudy.auth.dto.LoginResponseDTO;
import backend.speedspringstudy.member.entity.Member;
import backend.speedspringstudy.auth.exception.LoginInvalidPasswordException;
import backend.speedspringstudy.auth.exception.LoginMemberNotFoundException;
import backend.speedspringstudy.auth.jwt.JwtTokenProvider;
import backend.speedspringstudy.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO, HttpServletResponse response) {

        Member member = memberRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(LoginMemberNotFoundException::new);

        if (!passwordEncoder.matches(loginRequestDTO.password(), member.getPassword())) {
            throw new LoginInvalidPasswordException();
        }

        String accessToken = jwtTokenProvider.generateAccessToken(member.getEmail(), member.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getEmail(), member.getId());

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