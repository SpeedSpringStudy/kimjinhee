package backend.speedspringstudy.productOption.dto;

public record ProductOptionRequestDTO(
        Long productId,
        Long optionId,
        Integer quantity
) {}
