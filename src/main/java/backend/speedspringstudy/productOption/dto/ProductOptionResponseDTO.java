package backend.speedspringstudy.productOption.dto;

public record ProductOptionResponseDTO(
        Long id,
        String productName,
        String optionName,
        Integer quantity
) {}