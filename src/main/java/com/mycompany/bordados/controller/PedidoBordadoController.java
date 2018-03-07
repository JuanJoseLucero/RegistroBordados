package com.mycompany.bordados.controller;

import com.mycompany.bordados.modelo.PedidoBordado;
import com.mycompany.bordados.facade.PedidoBordadoFacade;
import com.mycompany.bordados.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pedidoBordadoController")
@ViewScoped
public class PedidoBordadoController extends AbstractController<PedidoBordado> {

    @Inject
    private PedidoBordadoCabeceraController pcaCodigoController;
    @Inject
    private MobilePageController mobilePageController;

    public PedidoBordadoController() {
        // Inform the Abstract parent controller of the concrete PedidoBordado Entity
        super(PedidoBordado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        pcaCodigoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the PedidoBordadoCabecera controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePcaCodigo(ActionEvent event) {
        PedidoBordado selected = this.getSelected();
        if (selected != null && pcaCodigoController.getSelected() == null) {
            pcaCodigoController.setSelected(selected.getPcaCodigo());
        }
    }

}
