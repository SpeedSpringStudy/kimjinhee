package backend.speedspringstudy.option.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OptionRequestDTO(
        @NotBlank
        @Size(max = 30)
        String name
) {}
