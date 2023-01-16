package com.clases.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clases.springboot.app.models.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
