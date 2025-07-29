package backend.speedspringstudy.category.service;

import backend.speedspringstudy.category.dto.CategoryRequestDTO;
import backend.speedspringstudy.category.dto.CategoryResponseDTO;
import backend.speedspringstudy.category.entity.Category;
import backend.speedspringstudy.category.exception.CategoryAlreadyExistsException;
import backend.speedspringstudy.category.exception.CategoryNotFoundException;
import backend.speedspringstudy.category.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponseDTO(
                        category.getId(),
                        category.getName(),
                        category.getColor(),
                        category.getImageUrl(),
                        category.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void postCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.existsByName(categoryRequestDTO.name())) {
            throw new CategoryAlreadyExistsException();
        }

        Category category = new Category();
        category.setName(categoryRequestDTO.name());
        category.setColor(categoryRequestDTO.color());
        category.setImageUrl(categoryRequestDTO.imageUrl());
        category.setDescription(categoryRequestDTO.description());

        categoryRepository.save(category);
    }

    @Transactional
    public void putCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        category.setName(categoryRequestDTO.name());
        category.setColor(categoryRequestDTO.color());
        category.setImageUrl(categoryRequestDTO.imageUrl());
        category.setDescription(categoryRequestDTO.description());
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException();
        }

        categoryRepository.deleteById(id);
    }

}
