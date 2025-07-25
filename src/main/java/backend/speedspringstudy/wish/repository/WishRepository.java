package backend.speedspringstudy.wish.repository;

import backend.speedspringstudy.wish.entity.Wish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    boolean existsByMemberIdAndProductId(Long memberId, Long productId);

    Optional<Wish> findByMemberIdAndProductId(Long memberId, Long productId);

    List<Wish> findAllByMemberId(Long memberId);
}