package com.santander.controlegastos.repository;

import com.santander.controlegastos.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByUsuario_idOrderByDataLancamentoDesc(final Long userId);
}
