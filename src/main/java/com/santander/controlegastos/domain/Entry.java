package com.santander.controlegastos.domain;

import com.santander.controlegastos.commons.Auditable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "entry")
public class Entry extends Auditable implements Serializable{
    private static final long serialVersionUID = 1136147349842813472L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_entry")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "dat_entry", nullable = false)
    private Date entryDate;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_category")
    private Category category;

}
