package backend.speedspringstudy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {

    private String name;

    private String description;

    private Long price;
}
