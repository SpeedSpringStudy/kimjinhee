package backend.speedspringstudy.repository;

import backend.speedspringstudy.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 0L;

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product save(Product product) {
        product.setId(++currentId);
        products.put(product.getId(), product);
        return product;
    }

    public void update(Long id, Product product) {
        product.setId(id);
        products.put(id, product);
    }

    public void delete(Long id) {
        products.remove(id);
    }
}
