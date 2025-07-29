package backend.speedspringstudy.product.dto;

import backend.speedspringstudy.product.validator.ValidProductName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(
        @NotBlank
        @Size(max = 15)
        @ValidProductName
        String name,

        String description,

        @NotNull
        Long price,

        @NotNull
        Long categoryId
) {}