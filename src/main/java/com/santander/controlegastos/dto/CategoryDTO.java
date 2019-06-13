package com.santander.controlegastos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

//    @ApiModelProperty(notes = "Category id's", example = "1")
    private Long id;

//    @NotNull(message = "Field \"name\" needs to be informed.")
//    @ApiModelProperty(notes = "Category's name", example = "ALIMENTACAO", required = true, position = 1)
    private String name;
}
