package com.tfg.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="UsuarioPerfil")
public class UsuarioPerfil {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer numero;

	@OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

	@OneToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    @Override
    public String toString() {
        return "UsuarioPerfil [numero="+ numero +"+, usuario=" + usuario + ", perfil=" + perfil + "]";
    }

}
