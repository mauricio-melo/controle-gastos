package com.santander.controlegastos.translate;

import com.santander.controlegastos.domain.Lancamento;
import com.santander.controlegastos.dto.LancamentoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LancamentoTranslate {

    public Lancamento toEntity(LancamentoDTO dto) {
        return toEntity(dto, Lancamento.builder().build());
    }

    public Lancamento toEntity(LancamentoDTO dto, Lancamento entity) {
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setDataLancamento(dto.getData());
        return entity;
    }

    public LancamentoDTO toDTO(Lancamento entity) {
        return LancamentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .codigoCategoria(entity.getCategoria().getId())
                .valor(entity.getValor())
                .data(entity.getDataLancamento())
                .codigoUsuario(entity.getUsuario().getId())
                .build();
    }

    public List<LancamentoDTO> toDTOList(List<Lancamento> entityList) {
        List<LancamentoDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }

}

