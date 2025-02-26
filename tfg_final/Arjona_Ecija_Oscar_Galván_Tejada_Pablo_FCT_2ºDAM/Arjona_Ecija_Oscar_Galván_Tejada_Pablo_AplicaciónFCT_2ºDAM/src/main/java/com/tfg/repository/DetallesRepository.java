package com.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.model.Detalle_pedidos;

public interface DetallesRepository extends JpaRepository<Detalle_pedidos,Integer> {

}
