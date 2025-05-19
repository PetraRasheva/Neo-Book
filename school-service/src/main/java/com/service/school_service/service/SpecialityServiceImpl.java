package com.service.school_service.service;

import com.service.school_service.dto.CreateSpecialityDto;
import com.service.school_service.dto.SpecialityDto;
import com.service.school_service.exception.SpecialityNotFoundException;
import com.service.school_service.mapper.SpecialityMapper;
import com.service.school_service.model.Speciality;
import com.service.school_service.repository.SpecialityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository repository;
    private final SpecialityMapper mapper;

    @Override
    public SpecialityDto createSpeciality(CreateSpecialityDto dto) {
        Speciality speciality = mapper.toEntity(dto);
        return mapper.toDto(repository.save(speciality));
    }

    @Override
    public SpecialityDto updateSpeciality(Long id, SpecialityDto dto) {
        Speciality speciality = repository.findById(id)
                .orElseThrow(() -> new SpecialityNotFoundException(id));
        speciality.setName(dto.name());
        return mapper.toDto(repository.save(speciality));
    }

    @Override
    public void deleteSpeciality(Long id) {
        if (!repository.existsById(id)) {
            throw new SpecialityNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public SpecialityDto getSpecialityById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new SpecialityNotFoundException(id)));
    }

    @Override
    public List<SpecialityDto> getAllSpecialities() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Speciality getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality not found with id: " + id));
    }
}
