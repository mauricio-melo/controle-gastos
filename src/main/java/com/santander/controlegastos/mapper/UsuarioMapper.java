package com.santander.controlegastos.mapper;

import com.santander.controlegastos.commons.AbstractMapper;
import com.santander.controlegastos.domain.Usuario;
import com.santander.controlegastos.dto.UsuarioDTO;

import java.util.List;

public class UsuarioMapper extends AbstractMapper<Usuario,UsuarioDTO> {
    @Override
    public UsuarioDTO toDTO(Usuario entity) { return super.toDTO(entity); }

    @Override
    public Usuario toEntity(UsuarioDTO dto) { return super.toEntity(dto);}

    @Override
    public List<UsuarioDTO> toDTOList(List<Usuario> entity) { return super.toDTOList(entity); }

    @Override
    public List<Usuario> toEntityList(List<UsuarioDTO> dto) { return super.toEntityList(dto);}
}
