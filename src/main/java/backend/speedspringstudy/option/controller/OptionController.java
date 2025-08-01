package backend.speedspringstudy.option.controller;

import backend.speedspringstudy.option.dto.OptionRequestDTO;
import backend.speedspringstudy.option.dto.OptionResponseDTO;
import backend.speedspringstudy.option.service.OptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    public void postOption(@RequestBody @Valid OptionRequestDTO optionRequestDTO) {
        optionService.postOption(optionRequestDTO);
    }

    @GetMapping
    public List<OptionResponseDTO> findAllOptions() {
        return optionService.findAllOptions();
    }

    @PutMapping("/{id}")
    public void putOption(
            @PathVariable Long id,
            @RequestBody @Valid OptionRequestDTO dto
    ) {
        optionService.putOption(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
    }
}