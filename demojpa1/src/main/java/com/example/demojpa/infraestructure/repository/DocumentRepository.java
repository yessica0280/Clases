package com.example.demojpa.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demojpa.domain.passport;

public interface DocumentRepository extends JpaRepository<passport, Long>{

} 
