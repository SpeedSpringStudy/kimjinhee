package backend.speedspringstudy.wish.repository;

import backend.speedspringstudy.wish.entity.Wish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    boolean existsByMemberIdAndProductId(Long memberId, Long productId);

    Optional<Wish> findByMemberIdAndProductId(Long memberId, Long productId);

    Page<Wish> findAllByMemberId(Long memberId, Pageable pageable);

}