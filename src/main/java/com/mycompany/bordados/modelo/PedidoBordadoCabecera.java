/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "pedido_bordado_cabecera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoBordadoCabecera.findAll", query = "SELECT p FROM PedidoBordadoCabecera p")
    , @NamedQuery(name = "PedidoBordadoCabecera.findByPcaCodigo", query = "SELECT p FROM PedidoBordadoCabecera p WHERE p.pcaCodigo = :pcaCodigo")
    , @NamedQuery(name = "PedidoBordadoCabecera.findByPcaFecha", query = "SELECT p FROM PedidoBordadoCabecera p WHERE p.pcaFecha = :pcaFecha")
    , @NamedQuery(name = "PedidoBordadoCabecera.findByPcaFechaEntrega", query = "SELECT p FROM PedidoBordadoCabecera p WHERE p.pcaFechaEntrega = :pcaFechaEntrega")
    , @NamedQuery(name = "PedidoBordadoCabecera.findByPcaTotal", query = "SELECT p FROM PedidoBordadoCabecera p WHERE p.pcaTotal = :pcaTotal")})
public class PedidoBordadoCabecera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pca_codigo")
    private Integer pcaCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pca_fecha")
    @Temporal(TemporalType.DATE)
    private Date pcaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pca_fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date pcaFechaEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pca_total")
    private BigDecimal pcaTotal;
    @Column(name = "pca_estado_pago")
    private String pca_estado_pago;
    @JoinColumn(name = "bor_codigo", referencedColumnName = "bor_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bordador borCodigo;
    @JoinColumn(name = "cli_codigo", referencedColumnName = "cli_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente cliCodigo;
    @JoinColumn(name = "dis_codigo", referencedColumnName = "dis_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Diseniador disCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pcaCodigo", fetch = FetchType.LAZY)
    private List<PedidoBordado> pedidoBordadoList;
//    //AUXILIARES
//    private String detallesNombres;
    public PedidoBordadoCabecera() {
    }
    
    public PedidoBordadoCabecera(Integer pcaCodigo) {
        this.pcaCodigo = pcaCodigo;
    }

    public PedidoBordadoCabecera(Integer pcaCodigo, Date pcaFecha, Date pcaFechaEntrega) {
        this.pcaCodigo = pcaCodigo;
        this.pcaFecha = pcaFecha;
        this.pcaFechaEntrega = pcaFechaEntrega;
    }

    public Integer getPcaCodigo() {
        return pcaCodigo;
    }

    public void setPcaCodigo(Integer pcaCodigo) {
        this.pcaCodigo = pcaCodigo;
    }

    public Date getPcaFecha() {
        return pcaFecha;
    }

    public void setPcaFecha(Date pcaFecha) {
        this.pcaFecha = pcaFecha;
    }

    public Date getPcaFechaEntrega() {
        return pcaFechaEntrega;
    }

    public void setPcaFechaEntrega(Date pcaFechaEntrega) {
        this.pcaFechaEntrega = pcaFechaEntrega;
    }

    public BigDecimal getPcaTotal() {
        return pcaTotal;
    }

    public void setPcaTotal(BigDecimal pcaTotal) {
        this.pcaTotal = pcaTotal;
    }

    public Bordador getBorCodigo() {
        return borCodigo;
    }

    public void setBorCodigo(Bordador borCodigo) {
        this.borCodigo = borCodigo;
    }

    public Cliente getCliCodigo() {
        return cliCodigo;
    }

    public void setCliCodigo(Cliente cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    public Diseniador getDisCodigo() {
        return disCodigo;
    }

    public void setDisCodigo(Diseniador disCodigo) {
        this.disCodigo = disCodigo;
    }

    @XmlTransient
    public List<PedidoBordado> getPedidoBordadoList() {
        return pedidoBordadoList;
    }

    public void setPedidoBordadoList(List<PedidoBordado> pedidoBordadoList) {
        this.pedidoBordadoList = pedidoBordadoList;
    }

	public String getPca_estado_pago() {
		return pca_estado_pago;
	}

	public void setPca_estado_pago(String pca_estado_pago) {
		this.pca_estado_pago = pca_estado_pago;
	}
	
//	public String getDetallesNombres() {
//		return detallesNombres;
//	}
//
//	public void setDetallesNombres(String detallesNombres) {
//		this.detallesNombres = detallesNombres;
//	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pcaCodigo != null ? pcaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoBordadoCabecera)) {
            return false;
        }
        PedidoBordadoCabecera other = (PedidoBordadoCabecera) object;
        if ((this.pcaCodigo == null && other.pcaCodigo != null) || (this.pcaCodigo != null && !this.pcaCodigo.equals(other.pcaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bordados.modelo.PedidoBordadoCabecera[ pcaCodigo=" + pcaCodigo + " ]";
    }
    
}
