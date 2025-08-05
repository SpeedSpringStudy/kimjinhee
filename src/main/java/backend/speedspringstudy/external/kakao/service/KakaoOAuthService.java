package backend.speedspringstudy.external.kakao.service;

import backend.speedspringstudy.external.kakao.client.KakaoOAuthTokenClient;
import backend.speedspringstudy.external.kakao.client.KakaoUserInfoClient;
import backend.speedspringstudy.external.kakao.dto.KakaoUserInfoResponseDTO;
import backend.speedspringstudy.external.kakao.dto.KakaoUserTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoOAuthService {

    private final KakaoOAuthTokenClient kakaoOAuthTokenClient;
    private final KakaoUserInfoClient kakaoUserInfoClient;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public KakaoUserTokenResponseDTO getAccessToken(String code) {
        return kakaoOAuthTokenClient.getAccessToken(
                "authorization_code",
                clientId,
                redirectUri,
                code
        );
    }

    public KakaoUserInfoResponseDTO getUserInfo(String accessToken) {
        return kakaoUserInfoClient.getUserInfo("Bearer " + accessToken);
    }
}