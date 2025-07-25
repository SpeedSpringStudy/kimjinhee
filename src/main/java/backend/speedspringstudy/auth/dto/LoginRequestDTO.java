package backend.speedspringstudy.auth.dto;

public record LoginRequestDTO(
        String email,
        String password
) {}
