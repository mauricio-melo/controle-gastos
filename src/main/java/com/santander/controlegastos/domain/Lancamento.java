package com.santander.controlegastos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.santander.controlegastos.enumerators.TipoCategoria;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(NON_EMPTY)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lancamento")
public class Lancamento implements Serializable{
    private static final long serialVersionUID = 1136147349842813472L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_lancamento")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "tipo_categoria")
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipoCategoria;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDateTime data;

    @CreatedDate
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

}
