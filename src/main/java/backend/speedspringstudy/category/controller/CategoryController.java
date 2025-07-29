package backend.speedspringstudy.category.controller;

import backend.speedspringstudy.category.dto.CategoryRequestDTO;
import backend.speedspringstudy.category.dto.CategoryResponseDTO;
import backend.speedspringstudy.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> result = categoryService.findAllCategories();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public void postCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.postCategory(categoryRequestDTO);
    }

    @PutMapping("/{id}")
    public void putCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.putCategory(id, categoryRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
