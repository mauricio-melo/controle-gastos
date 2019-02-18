package com.santander.controlegastos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class LancamentoDTO {

    @ApiModelProperty(notes = "Id do lancamento", example = "1")
    private Long id;

    @NotNull(message = "Campo \"descricao\" precisa ser informado.")
    @ApiModelProperty(notes = "Descrição do lancamento", example = "Carrefour", required = true, position = 1)
    private String descricao;

    @ApiModelProperty(notes = "Codigo da categoria do lancamento", example = "1", position = 2)
    private Long codigoCategoria;

    @NotNull(message = "Campo \"valor\" precisa ser informado.")
    @ApiModelProperty(notes = "Valor do lancamento", example = "120.30", required = true, position = 3)
    private BigDecimal valor;

    @NotNull(message = "Campo \"data\" precisa ser informado.")
    @ApiModelProperty(notes = "Data do lancamento", example = "2018-12-27", required = true, position = 4)
    private Date data;

    @NotNull(message = "Campo \"codigoUsuario\" precisa ser informado.")
    @ApiModelProperty(notes = "Codigo do usuario do lancamento", example = "1", required = true, position = 5)
    private Long codigoUsuario;
}
