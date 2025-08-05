package backend.speedspringstudy.external.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserInfoResponseDTO(
        Long id,
        KakaoAccount kakaoAccount
) {
    public String email() {
        return kakaoAccount.email();
    }

    public record KakaoAccount(
            String email
    ) {}
}