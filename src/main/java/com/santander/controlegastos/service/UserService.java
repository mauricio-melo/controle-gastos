package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.User;
import com.santander.controlegastos.dto.UserDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.mapper.UserMapper;
import com.santander.controlegastos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Lazy}))
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDTO save(final UserDTO dto){
        final User user = this.mapper.toEntity(dto);
        return this.mapper.toDTO(this.repository.save(user));
    }

    public UserDTO update(final Long id, final UserDTO dto) {
        final User user = this.mapper.toEntity(this.findById(id));
        user.setName(dto.getName());
        return this.mapper.toDTO(this.repository.save(user));
    }

    public UserDTO findById(final Long id){
        return this.mapper.toDTO(
                Optional.of(this.repository.findById(id).get())
                        .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<UserDTO> findAll() {
        return this.mapper.toDTOList(this.repository.findAll());
    }

    public void delete(final Long id) {
        this.repository.deleteById(id);
    }
}
