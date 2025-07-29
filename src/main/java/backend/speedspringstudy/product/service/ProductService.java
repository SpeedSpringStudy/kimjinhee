package backend.speedspringstudy.product.service;

import backend.speedspringstudy.category.entity.Category;
import backend.speedspringstudy.category.exception.CategoryNotFoundException;
import backend.speedspringstudy.category.repository.CategoryRepository;
import backend.speedspringstudy.product.dto.ProductRequestDTO;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.product.entity.Product;
import backend.speedspringstudy.product.exception.ProductNotFoundException;
import backend.speedspringstudy.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductResponseDTO> getProductList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).stream()
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getCategory().getId()
                ))
                .toList();
    }

    public void postProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException());

        product.setCategory(category);

        productRepository.save(product);
    }

    public void putProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException());

        product.setCategory(category);

        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}