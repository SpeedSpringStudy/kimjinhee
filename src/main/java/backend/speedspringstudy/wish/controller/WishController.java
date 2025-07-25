package backend.speedspringstudy.wish.controller;

import backend.speedspringstudy.common.resolver.MemberId;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.wish.dto.WishRequestDTO;
import backend.speedspringstudy.wish.service.WishService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

    @PostMapping("")
    public void postWish(@MemberId Long memberId, @RequestBody WishRequestDTO request) {
        wishService.postWish(memberId, request.getProductId());
    }

    @GetMapping("")
    public List<ProductResponseDTO> getWishList(@MemberId Long memberId) {
        return wishService.getWishList(memberId);
    }

    @DeleteMapping("")
    public void deleteWish(@MemberId Long memberId, @RequestBody WishRequestDTO request) {
        wishService.deleteWish(memberId, request.getProductId());
    }
}
