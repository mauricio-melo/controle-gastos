package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.Category;
import com.santander.controlegastos.dto.CategoryDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.mapper.CategoryMapper;
import com.santander.controlegastos.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Lazy}))
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryDTO save(final CategoryDTO dto){
        Category category = this.mapper.toEntity(dto);
        category.setName(dto.getName().toUpperCase());
        return this.mapper.toDTO(this.repository.save(category));
    }

    public CategoryDTO update(final Long id, final CategoryDTO dto) {
        final Category category = this.mapper.toEntity(this.findById(id));
        category.setName(dto.getName());
        return this.mapper.toDTO(this.repository.save(category));
    }

    public CategoryDTO findById(final Long id){
        return this.mapper.toDTO(
                Optional.of(this.repository.getOne(id))
                        .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<CategoryDTO> findAll() {
        return this.mapper.toDTOList(this.repository.findAll());
    }

    public void delete(final Long id) {
        this.repository.deleteById(id);
    }

    public List<CategoryDTO> findByNameContaining(final String name){
        return this.mapper.toDTOList(this.repository.findByNameContaining(name.toUpperCase()));
    }
}
