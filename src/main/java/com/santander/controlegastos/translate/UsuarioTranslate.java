package com.santander.controlegastos.translate;

import com.santander.controlegastos.domain.Usuario;
import com.santander.controlegastos.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioTranslate {

    public Usuario toEntity (UsuarioDTO dto){
        return Usuario.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .ativo(dto.getAtivo())
                .build();
    }

    public UsuarioDTO toDTO(Usuario entity) {
        return UsuarioDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .ativo(entity.getAtivo())
                .build();
    }

    public List<UsuarioDTO> toDTOList(List<Usuario> entityList) {
        List<UsuarioDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
    
}
