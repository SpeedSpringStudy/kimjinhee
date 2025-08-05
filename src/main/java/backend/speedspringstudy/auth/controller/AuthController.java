package backend.speedspringstudy.auth.controller;

import backend.speedspringstudy.auth.dto.KakaoLoginRequestDTO;
import backend.speedspringstudy.auth.dto.LoginRequestDTO;
import backend.speedspringstudy.auth.dto.LoginResponseDTO;
import backend.speedspringstudy.auth.service.AuthService;
import backend.speedspringstudy.external.kakao.dto.KakaoUserInfoResponseDTO;
import backend.speedspringstudy.external.kakao.dto.KakaoUserTokenResponseDTO;
import backend.speedspringstudy.external.kakao.service.KakaoOAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOAuthService kakaoOAuthService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        return authService.login(loginRequestDTO, response);
    }

    @PostMapping("/kakao-login")
    public ResponseEntity<LoginResponseDTO> kakaoLogin(
            @RequestBody KakaoLoginRequestDTO kakaoLoginRequestDTO,
            HttpServletResponse response
    ) {
        KakaoUserTokenResponseDTO kakaoAccessToken = kakaoOAuthService.getAccessToken(kakaoLoginRequestDTO.kakaoCode());
        KakaoUserInfoResponseDTO kakaoUser = kakaoOAuthService.getUserInfo(kakaoAccessToken.accessToken());

        LoginResponseDTO loginResponse = authService.kakaoLogin(
                kakaoUser.id(),
                kakaoUser.email(),
                response
        );

        return ResponseEntity.ok(loginResponse);
    }
}
