package com.santander.controlegastos.repository;

import com.santander.controlegastos.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByNameContaining(final String name);
}
