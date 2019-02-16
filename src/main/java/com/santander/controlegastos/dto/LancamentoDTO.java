package com.santander.controlegastos.dto;

import com.santander.controlegastos.enumerators.TipoCategoria;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LancamentoDTO {
    private Long id;
    private String descricao;
    private TipoCategoria tipoCategoria;
    private BigDecimal valor;
    private LocalDateTime data;
    private UsuarioDTO usuario;
}
