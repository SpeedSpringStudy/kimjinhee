package backend.speedspringstudy.wish.service;

import backend.speedspringstudy.member.entity.Member;
import backend.speedspringstudy.member.exception.MemberNotFoundException;
import backend.speedspringstudy.member.repository.MemberRepository;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.product.entity.Product;
import backend.speedspringstudy.product.exception.ProductNotFoundException;
import backend.speedspringstudy.wish.entity.Wish;
import backend.speedspringstudy.wish.exception.WishAlreadyExistsException;
import backend.speedspringstudy.wish.exception.WishNotFoundException;
import backend.speedspringstudy.wish.repository.WishRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Transactional
    public void postWish(Long memberId, Long productId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        if (wishRepository.existsByMemberIdAndProductId(memberId, productId)) {
            throw new WishAlreadyExistsException();
        }

        Product product = em.find(Product.class, productId);
        if (product == null)
            throw new ProductNotFoundException();

        Wish wish = Wish.builder()
                .member(member)
                .product(product)
                .build();

        wishRepository.save(wish);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getWishList(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return wishRepository.findAllByMemberId(memberId, pageable).stream()
                .map(Wish::getProduct)
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getCategory().getId()
                ))
                .toList();
    }

    @Transactional
    public void deleteWish(Long memberId, Long productId) {
        Product product = em.find(Product.class, productId);
        if (product == null)
            throw new ProductNotFoundException();

        Wish wish = wishRepository.findByMemberIdAndProductId(memberId, productId)
                .orElseThrow(WishNotFoundException::new);

        wishRepository.delete(wish);
    }
}