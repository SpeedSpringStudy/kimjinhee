package backend.speedspringstudy.wish.controller;

import backend.speedspringstudy.common.resolver.MemberId;
import backend.speedspringstudy.product.dto.ProductResponseDTO;
import backend.speedspringstudy.wish.dto.WishListRequestDTO;
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
    public void postWish(@MemberId Long memberId, @RequestBody WishRequestDTO wishRequestDTO) {
        wishService.postWish(memberId, wishRequestDTO.productId());
    }

    @GetMapping("")
    public List<ProductResponseDTO> getWishList(@MemberId Long memberId, @RequestBody WishListRequestDTO wishListRequestDTO) {
        return wishService.getWishList(memberId, wishListRequestDTO.page(), wishListRequestDTO.size());
    }

    @DeleteMapping("")
    public void deleteWish(@MemberId Long memberId, @RequestBody WishRequestDTO wishRequestDTO) {
        wishService.deleteWish(memberId, wishRequestDTO.productId());
    }
}
