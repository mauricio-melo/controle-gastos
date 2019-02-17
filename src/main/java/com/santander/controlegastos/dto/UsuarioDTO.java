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
public class UsuarioDTO {

    @ApiModelProperty(notes = "Id do usuario", example = "1")
    private Long id;

    @NotNull(message = "Campo \"nome\" precisa ser informado.")
    @ApiModelProperty(notes = "Nome do usuario", example = "José Bezerra", required = true, position = 1)
    private String nome;

    @NotNull(message = "Campo \"descricao\" precisa ser informado.")
    @ApiModelProperty(notes = "Descrição do lancamento", example = "Carrefour", required = true, position = 1)
    private Boolean ativo;
}
