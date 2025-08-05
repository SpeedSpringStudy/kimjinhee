package backend.speedspringstudy.auth.service;

import backend.speedspringstudy.auth.dto.LoginRequestDTO;
import backend.speedspringstudy.auth.dto.LoginResponseDTO;
import backend.speedspringstudy.member.entity.Member;
import backend.speedspringstudy.auth.exception.LoginInvalidPasswordException;
import backend.speedspringstudy.auth.exception.LoginMemberNotFoundException;
import backend.speedspringstudy.auth.jwt.JwtTokenProvider;
import backend.speedspringstudy.member.repository.MemberRepository;
import backend.speedspringstudy.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
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

        addRefreshTokenToCookie(response, refreshToken);

        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }

    @Transactional
    public LoginResponseDTO kakaoLogin(
            Long kakaoId,
            String email,
            HttpServletResponse response
    ) {
        Member member = memberRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> memberService.kakaoSignup(email, kakaoId));

        String accessToken = jwtTokenProvider.generateAccessToken(member.getEmail(), member.getId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(member.getEmail(), member.getId());

        addRefreshTokenToCookie(response, refreshToken);

        return new LoginResponseDTO(accessToken);
    }

    private void addRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("Strict")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }
}