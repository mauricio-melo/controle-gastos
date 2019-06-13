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
@Table(name = "user")
public class User extends Auditable implements Serializable{
    private static final long serialVersionUID = 613740835401650370L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "nam_user")
    private String name;

    @Builder.Default
    @Column(name = "flg_enabled")
    private Boolean enabled = true;
}
