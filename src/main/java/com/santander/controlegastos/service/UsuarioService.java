package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.Usuario;
import com.santander.controlegastos.dto.UsuarioDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.repository.UsuarioRepository;
import com.santander.controlegastos.translate.UsuarioTranslate;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioTranslate translate;

    public UsuarioDTO save(@NonNull final UsuarioDTO dto){
        Usuario entity = this.translate.toEntity(dto);
        return translate.toDTO(this.repository.save(entity));
    }

    public UsuarioDTO findById(final Long id) {
        return translate.toDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<UsuarioDTO> findAll() {
        return translate.toDTOList(repository.findAll());
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}
