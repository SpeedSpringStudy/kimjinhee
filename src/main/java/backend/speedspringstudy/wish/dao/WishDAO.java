package backend.speedspringstudy.wish.dao;

import backend.speedspringstudy.wish.entity.Wish;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WishDAO {
    private final EntityManager em;

    public boolean existsByMemberAndProduct(Long memberId, Long productId) {
        String q = "SELECT COUNT(w) FROM Wish w WHERE w.member.id = :memberId AND w.product.id = :productId";
        Long count = em.createQuery(q, Long.class)
                .setParameter("memberId", memberId)
                .setParameter("productId", productId)
                .getSingleResult();
        return count > 0;
    }

    public void save(Wish wish) {
        em.persist(wish);
    }

    public List<Wish> findWishListByMemberId(Long memberId) {
        String q = "SELECT w FROM Wish w JOIN FETCH w.product WHERE w.member.id = :memberId";
        return em.createQuery(q, Wish.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public Optional<Wish> findByMemberAndProduct(Long memberId, Long productId) {
        String q = "SELECT w FROM Wish w WHERE w.member.id = :memberId AND w.product.id = :productId";
        return em.createQuery(q, Wish.class)
                .setParameter("memberId", memberId)
                .setParameter("productId", productId)
                .getResultStream()
                .findFirst();
    }

    public void delete(Wish wish) {
        em.remove(em.contains(wish) ? wish : em.merge(wish));
    }
}