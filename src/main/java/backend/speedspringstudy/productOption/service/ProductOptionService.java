package backend.speedspringstudy.productOption.service;

import backend.speedspringstudy.option.entity.Option;
import backend.speedspringstudy.option.exception.OptionNotFoundException;
import backend.speedspringstudy.option.repository.OptionRepository;
import backend.speedspringstudy.product.entity.Product;
import backend.speedspringstudy.product.exception.ProductNotFoundException;
import backend.speedspringstudy.product.repository.ProductRepository;
import backend.speedspringstudy.productOption.dto.ProductOptionRequestDTO;
import backend.speedspringstudy.productOption.dto.ProductOptionResponseDTO;
import backend.speedspringstudy.productOption.entity.ProductOption;
import backend.speedspringstudy.productOption.exception.ProductOptionAlreadyExists;
import backend.speedspringstudy.productOption.exception.ProductOptionInsufficientStockException;
import backend.speedspringstudy.productOption.exception.ProductOptionNotFoundException;
import backend.speedspringstudy.productOption.exception.ProductOptionStockConflictException;
import backend.speedspringstudy.productOption.repository.ProductOptionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    public void postProductOption(ProductOptionRequestDTO productOptionRequestDTO) {
        if (productOptionRepository.existsByProductIdAndOptionId(productOptionRequestDTO.productId(), productOptionRequestDTO.optionId())) {
            throw new ProductOptionAlreadyExists();
        }

        Product product = productRepository.findById(productOptionRequestDTO.productId())
                .orElseThrow(() -> new ProductNotFoundException());
        Option option = optionRepository.findById(productOptionRequestDTO.optionId())
                .orElseThrow(() -> new OptionNotFoundException());

        ProductOption po = ProductOption.builder()
                .product(product)
                .option(option)
                .quantity(productOptionRequestDTO.quantity())
                .build();

        productOptionRepository.save(po);
    }

    public List<ProductOptionResponseDTO> findAllProductOptions() {
        return productOptionRepository.findAll().stream()
                .map(po -> new ProductOptionResponseDTO(
                        po.getId(),
                        po.getProduct().getName(),
                        po.getOption().getName(),
                        po.getQuantity()
                ))
                .toList();
    }

    public void putProductOption(Long id, ProductOptionRequestDTO dto) {
        ProductOption po = productOptionRepository.findById(id)
                .orElseThrow(() -> new ProductOptionNotFoundException());

        po.updateQuantity(dto.quantity());
    }

    public void deleteProductOption(Long id) {
        productOptionRepository.deleteById(id);
    }

    @Transactional
    public void decreaseQuantity(Long productOptionId, int orderQty) {
        ProductOption option = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new ProductOptionNotFoundException());

        if (option.getQuantity() < orderQty) {
            throw new ProductOptionInsufficientStockException();
        }

        option.setQuantity(option.getQuantity() - orderQty);
    }

    public void decreaseQuantityWithRetry(Long id, int qty) {
        int attempts = 2;
        while (attempts-- > 0) {
            try {
                decreaseQuantity(id, qty);
                return;
            } catch (OptimisticLockingFailureException e) {
            }
        }
        throw new ProductOptionStockConflictException();
    }
}