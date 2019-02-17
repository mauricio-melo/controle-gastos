package com.santander.controlegastos.translate;

import com.santander.controlegastos.domain.Lancamento;
import com.santander.controlegastos.dto.LancamentoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LancamentoTranslate {

    public Lancamento toEntity1(LancamentoDTO dto){
        return Lancamento.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .valor(dto.getValor())
                .dataLancamento(dto.getData())
                .build();
    }

    public LancamentoDTO toDTO(Lancamento entity) {
        return LancamentoDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .categoria(entity.getCategoria())
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

