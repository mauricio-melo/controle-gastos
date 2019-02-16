package com.santander.controlegastos.mapper;

import com.santander.controlegastos.commons.AbstractMapper;
import com.santander.controlegastos.domain.Lancamento;
import com.santander.controlegastos.dto.LancamentoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LancamentoMapper  extends AbstractMapper<Lancamento,LancamentoDTO> {
    @Override
    public LancamentoDTO toDTO(Lancamento entity) { return super.toDTO(entity); }

    @Override
    public Lancamento toEntity(LancamentoDTO dto) { return super.toEntity(dto);}

    @Override
    public List<LancamentoDTO> toDTOList(List<Lancamento> entity) { return super.toDTOList(entity); }

    @Override
    public List<Lancamento> toEntityList(List<LancamentoDTO> dto) { return super.toEntityList(dto);}
}
