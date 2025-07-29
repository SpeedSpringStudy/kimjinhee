package backend.speedspringstudy.category.dto;

public record CategoryRequestDTO(
        String name,
        String color,
        String imageUrl,
        String description
) {}