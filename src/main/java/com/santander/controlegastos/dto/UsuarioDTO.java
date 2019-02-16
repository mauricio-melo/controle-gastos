package com.santander.controlegastos.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private Boolean ativo;
}
