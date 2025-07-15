package backend.speedspringstudy.product.service;

import backend.speedspringstudy.product.dao.ProductDAO;
import backend.speedspringstudy.product.dto.ProductRequestDTO;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.product.entity.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductResponseDTO> getProductList() {
        return productDAO.findAll().stream()
                .map(p -> new ProductResponseDTO(p.getId(), p.getName(), p.getDescription(), p.getPrice()))
                .collect(Collectors.toList());
    }

    public void postProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());

        productDAO.save(product);
    }

    public void putProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());

        productDAO.update(id, product);
    }

    public void deleteProduct(Long id) {
        productDAO.delete(id);
    }
}
