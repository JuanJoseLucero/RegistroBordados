package com.mycompany.bordados.controller;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import com.mycompany.bordados.modelo.Persona;
import com.mycompany.bordados.modelo.Bordador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.PedidoBordado;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mycompany.bordados.facade.BordadorFacade;
import com.mycompany.bordados.facade.ClienteFacade;
import com.mycompany.bordados.facade.DiseniadorFacade;
import com.mycompany.bordados.facade.PedidoBordadoCabeceraFacade;
import com.mycompany.bordados.facade.PedidoBordadoFacade;
import com.mycompany.bordados.facade.PersonaFacade;
import com.mycompany.bordados.controller.util.MobilePageController;
import javax.inject.Named;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidoBordadoCabeceraController")
@ViewScoped
public class PedidoBordadoCabeceraController extends AbstractController<PedidoBordadoCabecera> {

	@Inject
	private BordadorController borCodigoController;
	@Inject
	private ClienteController cliCodigoController;
	@Inject
	private DiseniadorController disCodigoController;
	@Inject
	private MobilePageController mobilePageController;
	@Inject
	private PedidoBordadoFacade pedidoBordadoFacade;
	@Inject
	private PedidoBordadoCabeceraFacade pedidoBordadoCabeceraFacade;
	@Inject
	private PersonaFacade personaFacade;
	@Inject
	private DiseniadorFacade diseniadorFacade;
	@Inject
	private BordadorFacade bordadorFacade;
	@Inject
	private ClienteFacade clienteFacade;

	// Flags to indicate if child collections are empty
	private boolean isPedidoBordadoListEmpty;
	private List<PedidoBordado> lstNueva = new ArrayList<>();
	private PedidoBordado pedidoBordadoSelecccion;
	private PedidoBordadoCabecera pedidoBordadoCabecera = new PedidoBordadoCabecera();
	private Persona persona;
	private Integer indice = 0;
	private Boolean diseniador=false;
	private Boolean bordador=false;
	private Boolean cliente=false;
	
	private String actualizarDiseniador=":PedidoBordadoCabeceraListForm:disCodigo";
	private String actualizarBordador=":PedidoBordadoCabeceraListForm:borCodigo";
	private String actualizarCliente=":PedidoBordadoCabeceraListForm:cliCodigo";
	private String actualizar ="";

	private List<Diseniador> lstDiseniador = new ArrayList<>();
	private List<Bordador> lstBordador = new ArrayList<>();
	private List<Cliente> lstCliente = new ArrayList<>();
	
	private BigDecimal total;
	
	@PostConstruct
	public void init() {
		lstDiseniador= diseniadorFacade.findAll();
		lstBordador=bordadorFacade.findAll();
		lstCliente = clienteFacade.findAll();
		total = new BigDecimal("0");
	}
	
	public void calcularEnCaliente() {
		pedidoBordadoSelecccion.setPed_valor_unitario(calcularValorUnitario());
		pedidoBordadoSelecccion.setPed_subtotal(calcularValorSubtotal());
	}

	public void prepareCreateDetalle() {
		pedidoBordadoSelecccion = new PedidoBordado();
		pedidoBordadoSelecccion.setPed_subtotal(BigDecimal.ZERO);
		pedidoBordadoSelecccion.setPed_valor_unitario(BigDecimal.ZERO);
	}

	public PedidoBordadoCabeceraController() {
		super(PedidoBordadoCabecera.class);
	}

	public BigDecimal calcularTotal(){
		BigDecimal total = new BigDecimal("0");
		for(PedidoBordado b : lstNueva) {
			total= total.add(b.getPed_subtotal());
		}
		return total;
	}
	public void guardarAbstract() {
		try {
			persona.setPerFcaducado(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2999"));
			persona.setPerNombre(persona.getPerNombre().toUpperCase());
			persona.setPerApellido(persona.getPerApellido().toUpperCase());
			persona.setPerDireccion(persona.getPerDireccion().toUpperCase());
			personaFacade.create(persona);
		if(diseniador) {
				Diseniador d = new Diseniador();
				d.setDisCaducado(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2999"));
				d.setPerCodigo(persona);
				diseniadorFacade.create(d);
				lstDiseniador= diseniadorFacade.findAll();
				pedidoBordadoCabecera.setDisCodigo(d);
			}else if(bordador) {
				Bordador b = new Bordador();
				b.setBorCaducado(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2999"));
				b.setPerCodigo(persona);
				bordadorFacade.create(b);
				lstBordador = bordadorFacade.findAll();
				pedidoBordadoCabecera.setBorCodigo(b);
			}else if(cliente) {
				Cliente c = new Cliente();
				c.setCliFcaducado(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2999"));
				c.setPerCodigo(persona);
				clienteFacade.create(c);
				lstCliente = clienteFacade.findAll();
				pedidoBordadoCabecera.setCliCodigo(c);
			}
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void activarDiseniador() {
		persona = new Persona();
		diseniador=true;
		bordador=false;
		cliente=false;
		actualizar=actualizarDiseniador;
	}
	
	public void activarBordador() {
		persona = new Persona();
		diseniador=false;
		bordador=true;
		cliente=false;
		actualizar=actualizarBordador;
	}
	
	public void activarCliente() {
		persona = new Persona();
		diseniador=false;
		bordador=false;
		cliente=true;
		actualizar=actualizarCliente;
	}
	
	public void crearPedidoDetalle() {
		pedidoBordadoCabecera.setPcaFecha(new Date());
		pedidoBordadoSelecccion.setPedCodigo(indice);//COMENTARIO
		pedidoBordadoSelecccion.setPcaCodigo(pedidoBordadoCabecera);
		pedidoBordadoSelecccion.setPedNombreBordado(pedidoBordadoSelecccion.getPedNombreBordado().toUpperCase());
		pedidoBordadoSelecccion.setPed_observaciones(pedidoBordadoSelecccion.getPed_observaciones().toUpperCase());
		lstNueva.add(pedidoBordadoSelecccion);
		indice++;
		total = total.add(pedidoBordadoSelecccion.getPed_subtotal());
	}
	
	public BigDecimal calcularValorUnitario() {
		return ((((pedidoBordadoSelecccion.getPedPuntadas()!=null?pedidoBordadoSelecccion.getPedPuntadas():BigDecimal.ZERO)
				.multiply(pedidoBordadoSelecccion.getPedValor()!=null?pedidoBordadoSelecccion.getPedValor():BigDecimal.ZERO))
				.divide(new BigDecimal("1000")))).setScale(2, RoundingMode.CEILING).add(pedidoBordadoSelecccion.getPed_ganacia()!=null?pedidoBordadoSelecccion.getPed_ganacia():BigDecimal.ZERO);
	}
	
	public BigDecimal calcularValorSubtotal(){
		return ((((pedidoBordadoSelecccion.getPedPuntadas()!=null?pedidoBordadoSelecccion.getPedPuntadas():BigDecimal.ZERO)
				.multiply(pedidoBordadoSelecccion.getPedValor()!=null?pedidoBordadoSelecccion.getPedValor():BigDecimal.ZERO))
				.divide(new BigDecimal("1000"))).setScale(2,RoundingMode.CEILING).add(pedidoBordadoSelecccion.getPed_ganacia()!=null?pedidoBordadoSelecccion.getPed_ganacia():BigDecimal.ZERO).multiply(pedidoBordadoSelecccion.getPedCantidad())).setScale(2,
						RoundingMode.CEILING);
	}

	public void generarContrato() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(!lstNueva.isEmpty()) {
			pedidoBordadoCabecera.setPcaTotal(total);
			pedidoBordadoCabeceraFacade.create(pedidoBordadoCabecera);
			for (PedidoBordado pedidoBordado : lstNueva) {
				pedidoBordado.setPedCodigo(null);
				pedidoBordadoFacade.create(pedidoBordado);
			}
			lstNueva.clear();
			pedidoBordadoCabecera= new PedidoBordadoCabecera();
			total = BigDecimal.ZERO;
			context.addMessage(null, new FacesMessage("TRANSACCION EXITOSA", "SE HA CREADO UN NUEVO CONTRATO"));
		}else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR", "NO SE HAN ENCONTRADO DETALLES"));
		}
	}

	public void actualizarDetalle() {
		Integer numero = obtenerPoscicion();
		pedidoBordadoSelecccion.setPed_valor_unitario(calcularValorUnitario());
		pedidoBordadoSelecccion.setPed_subtotal(calcularValorSubtotal());
		lstNueva.set(numero, pedidoBordadoSelecccion);
		total=calcularTotal();
	}

	public Integer obtenerPoscicion() {
		Integer respuesta = 0;
		for (PedidoBordado p : lstNueva) {
			if (p.getPedCodigo().equals(pedidoBordadoSelecccion.getPedCodigo())) {
				respuesta = p.getPedCodigo();
				System.out.println("Se encontro igualdad");
				break;
			}
		}
		return respuesta;
	}

	/**
	 * Resets the "selected" attribute of any parent Entity controllers.
	 */
	public void resetParents() {
		borCodigoController.setSelected(null);
		cliCodigoController.setSelected(null);
		disCodigoController.setSelected(null);
	}

	/**
	 * Set the "is[ChildCollection]Empty" property for OneToMany fields.
	 */
	@Override
	protected void setChildrenEmptyFlags() {
		this.setIsPedidoBordadoListEmpty();
	}

	/**
	 * Sets the "selected" attribute of the Bordador controller in order to display
	 * its data in its View dialog.
	 *
	 * @param event
	 *            Event object for the widget that triggered an action
	 */
	public void prepareBorCodigo(ActionEvent event) {
		PedidoBordadoCabecera selected = this.getSelected();
		if (selected != null && borCodigoController.getSelected() == null) {
			borCodigoController.setSelected(selected.getBorCodigo());
		}
	}

	/**
	 * Sets the "selected" attribute of the Cliente controller in order to display
	 * its data in its View dialog.
	 *
	 * @param event
	 *            Event object for the widget that triggered an action
	 */
	public void prepareCliCodigo(ActionEvent event) {
		PedidoBordadoCabecera selected = this.getSelected();
		if (selected != null && cliCodigoController.getSelected() == null) {
			cliCodigoController.setSelected(selected.getCliCodigo());
		}
	}

	/**
	 * Sets the "selected" attribute of the Diseniador controller in order to
	 * display its data in its View dialog.
	 *
	 * @param event
	 *            Event object for the widget that triggered an action
	 */
	public void prepareDisCodigo(ActionEvent event) {
		PedidoBordadoCabecera selected = this.getSelected();
		if (selected != null && disCodigoController.getSelected() == null) {
			disCodigoController.setSelected(selected.getDisCodigo());
		}
	}

	public boolean getIsPedidoBordadoListEmpty() {
		return this.isPedidoBordadoListEmpty;
	}

	private void setIsPedidoBordadoListEmpty() {
		PedidoBordadoCabecera selected = this.getSelected();
		if (selected != null) {
			PedidoBordadoCabeceraFacade ejbFacade = (PedidoBordadoCabeceraFacade) this.getFacade();
			this.isPedidoBordadoListEmpty = ejbFacade.isPedidoBordadoListEmpty(selected);
		} else {
			this.isPedidoBordadoListEmpty = true;
		}
	}

	/**
	 * Sets the "items" attribute with a collection of PedidoBordado entities that
	 * are retrieved from PedidoBordadoCabecera and returns the navigation outcome.
	 *
	 * @return navigation outcome for PedidoBordado page
	 */
	public String navigatePedidoBordadoList() {
		PedidoBordadoCabecera selected = this.getSelected();
		if (selected != null) {
			PedidoBordadoCabeceraFacade ejbFacade = (PedidoBordadoCabeceraFacade) this.getFacade();
			List<PedidoBordado> selectedPedidoBordadoList = ejbFacade.findPedidoBordadoList(selected);
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PedidoBordado_items",
					selectedPedidoBordadoList);
		}
		return this.mobilePageController.getMobilePagesPrefix() + "/app/pedidoBordado/index";
	}

	public List<PedidoBordado> getLstNueva() {
		return lstNueva;
	}

	public void setLstNueva(List<PedidoBordado> lstNueva) {
		this.lstNueva = lstNueva;
	}

	public PedidoBordadoCabecera getPedidoBordadoCabecera() {
		return pedidoBordadoCabecera;
	}

	public void setPedidoBordadoCabecera(PedidoBordadoCabecera pedidoBordadoCabecera) {
		this.pedidoBordadoCabecera = pedidoBordadoCabecera;
	}

	public PedidoBordado getPedidoBordadoSelecccion() {
		return pedidoBordadoSelecccion;
	}

	public void setPedidoBordadoSelecccion(PedidoBordado pedidoBordadoSelecccion) {
		this.pedidoBordadoSelecccion = pedidoBordadoSelecccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Diseniador> getLstDiseniador() {
		return lstDiseniador;
	}

	public void setLstDiseniador(List<Diseniador> lstDiseniador) {
		this.lstDiseniador = lstDiseniador;
	}

	public List<Bordador> getLstBordador() {
		return lstBordador;
	}

	public void setLstBordador(List<Bordador> lstBordador) {
		this.lstBordador = lstBordador;
	}

	public String getActualizar() {
		return actualizar;
	}

	public void setActualizar(String actualizar) {
		this.actualizar = actualizar;
	}

	public List<Cliente> getLstCliente() {
		return lstCliente;
	}

	public void setLstCliente(List<Cliente> lstCliente) {
		this.lstCliente = lstCliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}

