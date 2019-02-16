package com.santander.controlegastos.dto;

import com.santander.controlegastos.enumerators.TipoCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDTO {
    private Long id;
    private String descricao;
    private TipoCategoria tipoCategoria;
    private BigDecimal valor;
    private Date data;
    private Long idUsuario;
}
