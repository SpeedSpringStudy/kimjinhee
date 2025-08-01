package backend.speedspringstudy.option.service;

import backend.speedspringstudy.option.dto.OptionRequestDTO;
import backend.speedspringstudy.option.dto.OptionResponseDTO;
import backend.speedspringstudy.option.entity.Option;
import backend.speedspringstudy.option.exception.OptionAlreadyExistsException;
import backend.speedspringstudy.option.exception.OptionNotFoundException;
import backend.speedspringstudy.option.repository.OptionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionResponseDTO postOption(OptionRequestDTO dto) {
        if (optionRepository.existsByName(dto.name())) {
            throw new OptionAlreadyExistsException();
        }

        Option option = Option.builder()
                .name(dto.name())
                .build();

        return toResponse(optionRepository.save(option));
    }

    public List<OptionResponseDTO> findAllOptions() {
        return optionRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public OptionResponseDTO putOption(Long id, OptionRequestDTO dto) {
        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new OptionNotFoundException());

        option.updateName(dto.name());
        return toResponse(option);
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

    private OptionResponseDTO toResponse(Option option) {
        return new OptionResponseDTO(option.getId(), option.getName());
    }
}