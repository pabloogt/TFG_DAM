package com.tfg.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="pedidos")
public class Pedidos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date fecha_pedido;
	private Date fecha_entrega;
	private String estado;
	private Double importe;
	private Integer cantidad_productos;
	@OneToOne
	@JoinColumn(name="usuario_id")//Foreign key to Categoria
	private Usuario usuario;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha_pedido() {
		return fecha_pedido;
	}
	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	public Date getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Integer getCantidad_productos() {
		return cantidad_productos;
	}
	public void setCantidad_productos(Integer cantidad_productos) {
		this.cantidad_productos = cantidad_productos;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", fecha_pedido=" + fecha_pedido + ", fecha_entrega=" + fecha_entrega + ", estado="
				+ estado + ", importe=" + importe + ", cantidad_productos=" + cantidad_productos + ", usuario="
				+ usuario + "]";
	}

	
	
	
	
	
}
