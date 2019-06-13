package com.santander.controlegastos.mapper;

import com.santander.controlegastos.commons.AbstractMapper;
import com.santander.controlegastos.domain.Entry;
import com.santander.controlegastos.dto.EntryDTO;
import com.santander.controlegastos.vo.EntryVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntryMapper extends AbstractMapper<Entry, EntryDTO> {

    @Override
    public EntryDTO toDTO(Entry entity) {
        return super.toDTO(entity);
    }

    @Override
    public List<EntryDTO> toDTOList(List<Entry> entity) {
        return super.toDTOList(entity);
    }

    @Override
    public Entry toEntity(EntryDTO dto) {
        return super.toEntity(dto);
    }

    @Override
    public List<Entry> toEntityList(List<EntryDTO> dto) {
        return super.toEntityList(dto);
    }

    public EntryDTO voToDTO(EntryVO vo) {
        return EntryDTO.builder()
                .description(vo.getDescription())
                .amount(vo.getAmount())
                .entryDate(vo.getEntryDate())
                .build();
    }
}
