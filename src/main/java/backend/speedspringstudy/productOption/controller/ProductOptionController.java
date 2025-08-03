package backend.speedspringstudy.productOption.controller;

import backend.speedspringstudy.productOption.dto.ProductOptionRequestDTO;
import backend.speedspringstudy.productOption.dto.ProductOptionResponseDTO;
import backend.speedspringstudy.productOption.service.ProductOptionService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-options")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @PostMapping
    public void postProductOption(@RequestBody @Valid ProductOptionRequestDTO productOptionRequestDTO) {
        productOptionService.postProductOption(productOptionRequestDTO);
    }

    @GetMapping
    public List<ProductOptionResponseDTO> findAllProductOptions() {
        return productOptionService.findAllProductOptions();
    }

    @PutMapping("/{id}")
    public void putProductOption(@PathVariable Long id, @RequestBody @Valid ProductOptionRequestDTO productOptionRequestDTO) {
        productOptionService.putProductOption(id, productOptionRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProductOption(@PathVariable Long id) {
        productOptionService.deleteProductOption(id);
    }

    @PostMapping("/{id}/decrease")
    public void decreaseQuantity(@PathVariable Long id, @RequestParam int quantity) {
        productOptionService.decreaseQuantityWithRetry(id, quantity);
    }
}
