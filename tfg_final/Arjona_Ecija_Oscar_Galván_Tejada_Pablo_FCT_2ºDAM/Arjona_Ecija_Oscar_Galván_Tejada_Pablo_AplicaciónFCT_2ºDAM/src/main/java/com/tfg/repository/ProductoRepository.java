package com.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto,Integer> {

	
	List<Producto> findByPrecioBetweenOrderByPrecioDesc(double s1, double s2);
	
}
