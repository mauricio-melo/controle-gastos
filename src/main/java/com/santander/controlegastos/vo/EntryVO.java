package com.santander.controlegastos.vo;

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
public class EntryVO {
    private String description;
    private BigDecimal amount;
    private Date entryDate;
    private Long userId;
    private Long categoryId;
}
