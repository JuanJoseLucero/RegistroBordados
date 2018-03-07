package com.mycompany.bordados.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mycompany.bordados.facade.ClienteFacade;
import com.mycompany.bordados.facade.PedidoBordadoCabeceraFacade;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.ListadoBordadoModel;
import com.mycompany.bordados.modelo.PedidoBordado;
import com.mycompany.bordados.modelo.PedidoBordadoCabecera;


@Named(value = "listadoBordadoController")
@ViewScoped
public class ListadoBordadoController implements Serializable {
	
	@Inject
	PedidoBordadoCabeceraFacade pedidoBordadoCabeceraFacade;
	
	@Inject
	ClienteFacade clienteFacade;
	
	private ListadoBordadoModel listadoBordadoModel;
	private List<Cliente> lstCliente;
	private Cliente clienteSelected;
	
	private PedidoBordadoCabecera cabeceraSeleccion;
	
	private PedidoBordado detalleSeleccion;
	
	private BigDecimal totalPendientes;
	private BigDecimal totalCancelado;
	
	@PostConstruct
	private void init() {
		listadoBordadoModel = new ListadoBordadoModel();
		clienteSelected = new Cliente();
		lstCliente = clienteFacade.findAll();
		totalPendientes = BigDecimal.ZERO;
		totalCancelado=BigDecimal.ZERO;
		detalleSeleccion= new PedidoBordado();
	}
	
	public void calcularEnCaliente() {
		detalleSeleccion.setPed_valor_unitario(calcularValorUnitario());
		detalleSeleccion.setPed_subtotal(calcularValorSubtotal());
	}
	
	public void obtenerRegistros() {
		if(listadoBordadoModel.getFecha()==null&&clienteSelected==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "DEBE INGRESAR UN CLIENTE"));
		}else {
			listadoBordadoModel.setLstCabeceras(pedidoBordadoCabeceraFacade.registrosByFechaConnection(listadoBordadoModel.getFecha(),clienteSelected.getCliCodigo()));
			sumatorias(listadoBordadoModel.getLstCabeceras());
		}
	}
	
	public void sumatorias(List<PedidoBordadoCabecera> lst) {
		totalPendientes   = BigDecimal.ZERO;	
		totalCancelado = BigDecimal.ZERO;
		for(PedidoBordadoCabecera obj : lst) {
			if(obj.getPca_estado_pago().equals("0")) {
				totalPendientes = totalPendientes.add(obj.getPcaTotal());
			}else {
				totalCancelado =totalCancelado.add(obj.getPcaTotal());
			}
		}
	}
	
	public void editarCabecera(){
		sumatorias(listadoBordadoModel.getLstCabeceras());
		pedidoBordadoCabeceraFacade.edit(cabeceraSeleccion);
	}
	
	public BigDecimal calcularTotal(){
		BigDecimal total = new BigDecimal("0");
		for(PedidoBordado b : cabeceraSeleccion.getPedidoBordadoList()) {
			total= total.add(b.getPed_subtotal());
		}
		return total;
	}
	
	public void actualizarDetalle() {
		Integer numero = obtenerPoscicion(detalleSeleccion);
		cabeceraSeleccion.getPedidoBordadoList().set(numero, detalleSeleccion);
		cabeceraSeleccion.setPcaTotal(calcularTotal());
		listadoBordadoModel.setLstCabeceras(pedidoBordadoCabeceraFacade.registrosByFechaConnection(listadoBordadoModel.getFecha(),clienteSelected.getCliCodigo()));
		totalCancelado = BigDecimal.ZERO;
		totalPendientes= BigDecimal.ZERO;
		sumatorias(listadoBordadoModel.getLstCabeceras());
	}

	public Integer obtenerPoscicion(PedidoBordado ped) {
		Integer respuesta = 0;
		for (int i =0; i < cabeceraSeleccion.getPedidoBordadoList().size(); i++) {
			if (ped.getPedCodigo().equals(cabeceraSeleccion.getPedidoBordadoList().get(i).getPedCodigo())) {
				return i;
			}
		}
		return null;
	}
	
	public BigDecimal calcularValorUnitario() {
		return ((((detalleSeleccion.getPedPuntadas()!=null?detalleSeleccion.getPedPuntadas():BigDecimal.ZERO)
				.multiply(detalleSeleccion.getPedValor()!=null?detalleSeleccion.getPedValor():BigDecimal.ZERO))
				.divide(new BigDecimal("1000")))).setScale(2, RoundingMode.CEILING).add(detalleSeleccion.getPed_ganacia()!=null?detalleSeleccion.getPed_ganacia():BigDecimal.ZERO);
	}
	
	public BigDecimal calcularValorSubtotal(){
		return ((((detalleSeleccion.getPedPuntadas()!=null?detalleSeleccion.getPedPuntadas():BigDecimal.ZERO)
				.multiply(detalleSeleccion.getPedValor()!=null?detalleSeleccion.getPedValor():BigDecimal.ZERO))
				.divide(new BigDecimal("1000"))).setScale(2,RoundingMode.CEILING).add(detalleSeleccion.getPed_ganacia()!=null?detalleSeleccion.getPed_ganacia():BigDecimal.ZERO).multiply(detalleSeleccion.getPedCantidad())).setScale(2,
						RoundingMode.CEILING);
	}

	
	
	public ListadoBordadoModel getListadoBordadoModel() {
		return listadoBordadoModel;
	}

	public void setListadoBordadoModel(ListadoBordadoModel listadoBordadoModel) {
		this.listadoBordadoModel = listadoBordadoModel;
	}

	public PedidoBordadoCabecera getCabeceraSeleccion() {
		return cabeceraSeleccion;
	}

	public void setCabeceraSeleccion(PedidoBordadoCabecera cabeceraSeleccion) {
		this.cabeceraSeleccion = cabeceraSeleccion;
	}

	public PedidoBordado getDetalleSeleccion() {
		return detalleSeleccion;
	}

	public void setDetalleSeleccion(PedidoBordado detalleSeleccion) {
		this.detalleSeleccion = detalleSeleccion;
	}

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	public Cliente getClienteSelected() {
		return clienteSelected;
	}

	public void setClienteSelected(Cliente clienteSelected) {
		this.clienteSelected = clienteSelected;
	}

	public BigDecimal getTotalPendientes() {
		return totalPendientes;
	}

	public void setTotalPendientes(BigDecimal totalPendientes) {
		this.totalPendientes = totalPendientes;
	}

	public BigDecimal getTotalCancelado() {
		return totalCancelado;
	}

	public void setTotalCancelado(BigDecimal totalCancelado) {
		this.totalCancelado = totalCancelado;
	}
	
}
