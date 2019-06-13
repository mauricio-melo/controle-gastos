package com.santander.controlegastos.repository;

import com.santander.controlegastos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
