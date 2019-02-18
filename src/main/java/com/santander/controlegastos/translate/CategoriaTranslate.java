package com.santander.controlegastos.translate;

import com.santander.controlegastos.domain.Categoria;
import com.santander.controlegastos.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoriaTranslate {
    public Categoria toEntity(CategoriaDTO dto) {
        return toEntity(dto, Categoria.builder().build());
    }

    public Categoria toEntity(CategoriaDTO dto, Categoria entity) {
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setAtivo(dto.getAtivo());
        return entity;
    }

    public CategoriaDTO toDTO(Categoria entity) {
        return CategoriaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .ativo(entity.getAtivo())
                .build();
    }

    public List<CategoriaDTO> toDTOList(List<Categoria> entityList) {
        List<CategoriaDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }


}
