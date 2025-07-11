package backend.speedspringstudy.dto;

import lombok.Getter;

@Getter
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Long price;

    public ProductResponseDTO(Long id, String name, String description, Long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
