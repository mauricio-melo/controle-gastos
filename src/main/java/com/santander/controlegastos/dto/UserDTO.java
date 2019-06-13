package com.santander.controlegastos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

//    @ApiModelProperty(notes = "Id do usuario", example = "1")
    private Long id;
//
//    @NotNull(message = "Campo \"nome\" precisa ser informado.")
//    @ApiModelProperty(notes = "Nome do usuario", example = "José Bezerra", required = true, position = 1)
    private String name;
}
