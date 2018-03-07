package com.mycompany.bordados.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.mycompany.bordados.controller.ClienteController;


public class ListadoBordadoModel implements Serializable {
	
	private Integer codigo;
	//@NotNull(message = "El campo no puede estar vacio")
	private Date fecha;
	
	private List<PedidoBordadoCabecera> lstCabeceras ;
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<PedidoBordadoCabecera> getLstCabeceras() {
		return lstCabeceras;
	}

	public void setLstCabeceras(List<PedidoBordadoCabecera> lstCabeceras) {
		this.lstCabeceras = lstCabeceras;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
