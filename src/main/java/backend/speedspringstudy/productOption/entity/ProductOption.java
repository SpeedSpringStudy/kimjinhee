package backend.speedspringstudy.productOption.entity;

import backend.speedspringstudy.option.entity.Option;
import backend.speedspringstudy.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Option option;

    @Column(nullable = false)
    private Integer quantity;

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}