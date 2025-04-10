package com.project.loginApi.repositories;

import com.project.loginApi.entities.Peso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesoRepository extends JpaRepository<Peso, Long> {
}
