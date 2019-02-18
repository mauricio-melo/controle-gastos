package com.santander.controlegastos.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    @ApiModelProperty(notes = "Id do lancamento", example = "1")
    private Long id;

    @NotNull(message = "Campo \"nome\" precisa ser informado.")
    @ApiModelProperty(notes = "Nome da categoria", example = "ALIMENTACAO", required = true, position = 1)
    private String nome;

    @NotNull(message = "Campo \"descricao\" precisa ser informado.")
    @ApiModelProperty(notes = "Situação da categoria", example = "true", required = true, position = 1)
    private Boolean ativo;


}
