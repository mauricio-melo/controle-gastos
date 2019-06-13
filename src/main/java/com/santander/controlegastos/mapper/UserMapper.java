package com.santander.controlegastos.mapper;

import com.santander.controlegastos.commons.AbstractMapper;
import com.santander.controlegastos.domain.User;
import com.santander.controlegastos.dto.UserDTO;
import com.santander.controlegastos.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<UserDTO> toDTOList(List<User> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public User toEntity(UserDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dto) {
        return super.toEntityList(dto);
    }

    public UserDTO voToDTO(UserVO vo) {
        return UserDTO.builder()
                .name(vo.getName())
                .build();
    }
}