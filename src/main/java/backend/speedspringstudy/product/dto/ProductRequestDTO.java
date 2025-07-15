package backend.speedspringstudy.product.dto;

import backend.speedspringstudy.product.validator.ValidProductName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    @NotBlank
    @Size(max = 15)
    @ValidProductName
    private String name;

    private String description;

    @NotNull
    private Long price;
}
