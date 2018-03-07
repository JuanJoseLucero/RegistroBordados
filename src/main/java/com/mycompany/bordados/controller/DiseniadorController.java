package com.mycompany.bordados.controller;

import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import java.util.List;
import com.mycompany.bordados.facade.DiseniadorFacade;
import com.mycompany.bordados.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "diseniadorController")
@ViewScoped
public class DiseniadorController extends AbstractController<Diseniador> {

    @Inject
    private PersonaController perCodigoController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPedidoBordadoCabeceraListEmpty;

    public DiseniadorController() {
        // Inform the Abstract parent controller of the concrete Diseniador Entity
        super(Diseniador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        perCodigoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPedidoBordadoCabeceraListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Persona controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePerCodigo(ActionEvent event) {
        Diseniador selected = this.getSelected();
        if (selected != null && perCodigoController.getSelected() == null) {
            perCodigoController.setSelected(selected.getPerCodigo());
        }
    }

    public boolean getIsPedidoBordadoCabeceraListEmpty() {
        return this.isPedidoBordadoCabeceraListEmpty;
    }

    private void setIsPedidoBordadoCabeceraListEmpty() {
        Diseniador selected = this.getSelected();
        if (selected != null) {
            DiseniadorFacade ejbFacade = (DiseniadorFacade) this.getFacade();
            this.isPedidoBordadoCabeceraListEmpty = ejbFacade.isPedidoBordadoCabeceraListEmpty(selected);
        } else {
            this.isPedidoBordadoCabeceraListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PedidoBordadoCabecera
     * entities that are retrieved from Diseniador and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PedidoBordadoCabecera page
     */
    public String navigatePedidoBordadoCabeceraList() {
        Diseniador selected = this.getSelected();
        if (selected != null) {
            DiseniadorFacade ejbFacade = (DiseniadorFacade) this.getFacade();
            List<PedidoBordadoCabecera> selectedPedidoBordadoCabeceraList = ejbFacade.findPedidoBordadoCabeceraList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PedidoBordadoCabecera_items", selectedPedidoBordadoCabeceraList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/pedidoBordadoCabecera/index";
    }

}
