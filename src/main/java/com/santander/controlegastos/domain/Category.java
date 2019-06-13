package com.santander.controlegastos.domain;

import com.santander.controlegastos.commons.Auditable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "category")
public class Category extends Auditable implements Serializable{
    private static final long serialVersionUID = 4775539159621725349L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @Column(name = "nam_category", nullable = false)
    private String name;

    @Builder.Default
    @Column(name = "flg_enabled")
    private Boolean enabled = true;
}
