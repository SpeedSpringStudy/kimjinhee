package backend.speedspringstudy.product.dto;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Long price
) {}
