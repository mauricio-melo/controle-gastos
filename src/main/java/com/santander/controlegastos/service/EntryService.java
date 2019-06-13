package com.santander.controlegastos.service;

import com.santander.controlegastos.domain.Entry;
import com.santander.controlegastos.dto.EntryDTO;
import com.santander.controlegastos.exception.ResourceNotFoundException;
import com.santander.controlegastos.mapper.CategoryMapper;
import com.santander.controlegastos.mapper.EntryMapper;
import com.santander.controlegastos.mapper.UserMapper;
import com.santander.controlegastos.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Lazy}))
public class EntryService {

    private final EntryRepository repository;
    private final EntryMapper mapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public EntryDTO save(final EntryDTO entryDTO, final Long userId, final Long categoryId){
        final Entry entry = this.mapper.toEntity(entryDTO);

        entry.setCategory(Optional.ofNullable(categoryId)
                .map(id -> this.categoryMapper.toEntity(this.categoryService.findById(id)))
                .orElseGet(() -> this.repository.findByUser_idAndDescription(userId, entryDTO.getDescription()).getCategory()));

        entry.setUser(Optional.ofNullable(userId)
                .map(id -> this.userMapper.toEntity(this.userService.findById(id))).get());

        return this.mapper.toDTO(this.repository.save(entry));
    }

    public EntryDTO update(final Long id, final EntryDTO entryDTO) {
        final Entry entry = this.mapper.toEntity(this.findById(id));
        entry.setCategory(this.categoryMapper.toEntity(this.categoryService.findById(entryDTO.getCategory().getId())));
        entry.setDescription(entryDTO.getDescription());
        entry.setAmount(entryDTO.getAmount());
        entry.setEntryDate(entryDTO.getEntryDate());
        return this.mapper.toDTO(this.repository.save(entry));
    }

    public EntryDTO findById(final Long id){
        return this.mapper.toDTO(
                Optional.of(this.repository.getOne(id))
                        .orElseThrow(() -> new ResourceNotFoundException(id.toString())));
    }

    public List<EntryDTO> findAll() {
        return this.mapper.toDTOList(repository.findAll());
    }

    public void delete(final Long id) {
        this.repository.deleteById(id);
    }

    public List<EntryDTO> findEntriesByUser(final Long userId){
        return this.mapper.toDTOList(this.repository.findByUser_idOrderByEntryDateDesc(userId));
    }

    public List<EntryDTO> findEntriesByUserAndEntryDate(final Long userId, final Date entryDate){
        return this.mapper.toDTOList(this.repository.findByUser_idAndEntryDate(userId, entryDate));
    }
}
