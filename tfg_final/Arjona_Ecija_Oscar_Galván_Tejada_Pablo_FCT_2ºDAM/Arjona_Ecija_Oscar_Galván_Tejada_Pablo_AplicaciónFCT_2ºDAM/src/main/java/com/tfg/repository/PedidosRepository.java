package com.tfg.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tfg.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos,Integer> {
	List<Pedidos> findByEstado(String estado);

    @Modifying
    @Query("UPDATE Pedidos p SET p.estado = 'pendiente' WHERE p.id = :paramIdPedido")
    int pendiente(@Param("paramIdPedido") int idPedido);

    @Modifying
    @Query("UPDATE Pedidos p SET p.estado = 'entregado' WHERE p.id = :paramIdPedido")
    int entregado(@Param("paramIdPedido") int idPedido);

}
