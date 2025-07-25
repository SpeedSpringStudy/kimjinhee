package backend.speedspringstudy.wish.service;

import backend.speedspringstudy.member.dao.MemberDAO;
import backend.speedspringstudy.member.entity.Member;
import backend.speedspringstudy.member.exception.MemberNotFoundException;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.product.entity.Product;
import backend.speedspringstudy.product.exception.ProductNotFoundException;
import backend.speedspringstudy.wish.dao.WishDAO;
import backend.speedspringstudy.wish.entity.Wish;
import backend.speedspringstudy.wish.exception.WishAlreadyExistsException;
import backend.speedspringstudy.wish.exception.WishNotFoundException;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishDAO wishDAO;
    private final MemberDAO memberDAO;
    private final EntityManager em;

    @Transactional
    public void postWish(Long memberId, Long productId) {
        Member member = memberDAO.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        if (wishDAO.existsByMemberAndProduct(memberId, productId)) {
            throw new WishAlreadyExistsException();
        }

        Product product = em.find(Product.class, productId);
        if (product == null)
            throw new ProductNotFoundException();

        Wish wish = Wish.builder()
                .member(member)
                .product(product)
                .build();

        wishDAO.save(wish);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getWishList(Long memberId) {
        List<Wish> wishes = wishDAO.findWishListByMemberId(memberId);

        return wishes.stream()
                .map(Wish::getProduct)
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice()
                ))
                .toList();
    }

    @Transactional
    public void deleteWish(Long memberId, Long productId) {
        Product product = em.find(Product.class, productId);
        if (product == null)
            throw new ProductNotFoundException();

        Wish wish = wishDAO.findByMemberAndProduct(memberId, productId)
                .orElseThrow(WishNotFoundException::new);

        wishDAO.delete(wish);
    }
}