package backend.speedspringstudy.category.dto;

public record CategoryResponseDTO(
        Long id,
        String name,
        String color,
        String imageUrl,
        String description
) {}