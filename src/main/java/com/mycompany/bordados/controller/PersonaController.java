package com.mycompany.bordados.controller;

import com.mycompany.bordados.modelo.Persona;
import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Bordador;
import java.util.List;
import com.mycompany.bordados.facade.PersonaFacade;
import com.mycompany.bordados.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "personaController")
@ViewScoped
public class PersonaController extends AbstractController<Persona> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDiseniadorListEmpty;
    private boolean isClienteListEmpty;
    private boolean isBordadorListEmpty;

    public PersonaController() {
        // Inform the Abstract parent controller of the concrete Persona Entity
        super(Persona.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDiseniadorListEmpty();
        this.setIsClienteListEmpty();
        this.setIsBordadorListEmpty();
    }

    public boolean getIsDiseniadorListEmpty() {
        return this.isDiseniadorListEmpty;
    }

    private void setIsDiseniadorListEmpty() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //this.isDiseniadorListEmpty = ejbFacade.isDiseniadorListEmpty(selected);
        } else {
            this.isDiseniadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Diseniador entities that
     * are retrieved from Persona and returns the navigation outcome.
     *
     * @return navigation outcome for Diseniador page
     */
    public String navigateDiseniadorList() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //List<Diseniador> selectedDiseniadorList = ejbFacade.findDiseniadorList(selected);
            //FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Diseniador_items", selectedDiseniadorList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/diseniador/index";
    }

    public boolean getIsClienteListEmpty() {
        return this.isClienteListEmpty;
    }

    private void setIsClienteListEmpty() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //this.isClienteListEmpty = ejbFacade.isClienteListEmpty(selected);
        } else {
            this.isClienteListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Cliente entities that are
     * retrieved from Persona and returns the navigation outcome.
     *
     * @return navigation outcome for Cliente page
     */
    public String navigateClienteList() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //List<Cliente> selectedClienteList = ejbFacade.findClienteList(selected);
            //FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Cliente_items", selectedClienteList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/cliente/index";
    }

    public boolean getIsBordadorListEmpty() {
        return this.isBordadorListEmpty;
    }

    private void setIsBordadorListEmpty() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //this.isBordadorListEmpty = ejbFacade.isBordadorListEmpty(selected);
        } else {
            this.isBordadorListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Bordador entities that
     * are retrieved from Persona and returns the navigation outcome.
     *
     * @return navigation outcome for Bordador page
     */
    public String navigateBordadorList() {
        Persona selected = this.getSelected();
        if (selected != null) {
            PersonaFacade ejbFacade = (PersonaFacade) this.getFacade();
            //List<Bordador> selectedBordadorList = ejbFacade.findBordadorList(selected);
            //FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Bordador_items", selectedBordadorList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/bordador/index";
    }
    
    

}
