package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.Categoria;
import com.santander.controlegastos.dto.CategoriaDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.repository.CategoriaRepository;
import com.santander.controlegastos.translate.CategoriaTranslate;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private CategoriaTranslate translate;

    public CategoriaDTO save(@NonNull final CategoriaDTO dto){
        Categoria entity = this.translate.toEntity(dto);
        return translate.toDTO(this.repository.save(entity));
    }

    public CategoriaDTO update(@NonNull final CategoriaDTO dto){
        Categoria entity = translate.toEntity(dto, translate.toEntity(findById(dto.getId())));
        return translate.toDTO(repository.save(entity));
    }

    public CategoriaDTO findById(final Long id) {
        return translate.toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<CategoriaDTO> findAll() {
        return translate.toDTOList(repository.findAll());
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public List<CategoriaDTO> pesquisar(String nome){
        return translate.toDTOList(repository.findByNomeContaining(nome));
    }
}
