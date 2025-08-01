package backend.speedspringstudy.option.repository;

import backend.speedspringstudy.option.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    boolean existsByName(String name);
}
