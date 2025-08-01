package backend.speedspringstudy.productOption.repository;

import backend.speedspringstudy.productOption.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    boolean existsByProductIdAndOptionId(Long productId, Long optionId);
}
