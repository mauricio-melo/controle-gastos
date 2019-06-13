package com.santander.controlegastos.mapper;

import com.santander.controlegastos.commons.AbstractMapper;
import com.santander.controlegastos.domain.Category;
import com.santander.controlegastos.dto.CategoryDTO;
import com.santander.controlegastos.vo.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDTO(Category entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<CategoryDTO> toDTOList(List<Category> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public Category toEntity(CategoryDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<Category> toEntityList(List<CategoryDTO> dto) {
        return super.toEntityList(dto);
    }

    public CategoryDTO voToDTO(CategoryVO vo) {
        return CategoryDTO.builder()
                .name(vo.getName())
                .build();
    }
}
