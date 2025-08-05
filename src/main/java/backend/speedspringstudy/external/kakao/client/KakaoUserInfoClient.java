package backend.speedspringstudy.external.kakao.client;

import backend.speedspringstudy.external.kakao.dto.KakaoUserInfoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoUserInfoClient", url = "https://kapi.kakao.com")
public interface KakaoUserInfoClient {

        @GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_JSON_VALUE)
        KakaoUserInfoResponseDTO getUserInfo(
                @RequestHeader("Authorization") String accessToken
    );
}
