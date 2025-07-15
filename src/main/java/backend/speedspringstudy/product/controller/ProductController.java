package backend.speedspringstudy.product.controller;

import backend.speedspringstudy.product.dto.ProductRequestDTO;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDTO> getProductList() {
        return productService.getProductList();
    }

    @PostMapping
    public void postProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        productService.postProduct(productRequestDTO);
    }

    @PutMapping("/{id}")
    public void putProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        productService.putProduct(id, productRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
