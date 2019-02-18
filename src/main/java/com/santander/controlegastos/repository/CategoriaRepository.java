package com.santander.controlegastos.repository;

import com.santander.controlegastos.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNomeContaining(String nome);
}
