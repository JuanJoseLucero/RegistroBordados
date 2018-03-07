/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "pedido_bordado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoBordado.findAll", query = "SELECT p FROM PedidoBordado p")
    , @NamedQuery(name = "PedidoBordado.findByPedCodigo", query = "SELECT p FROM PedidoBordado p WHERE p.pedCodigo = :pedCodigo")
    , @NamedQuery(name = "PedidoBordado.findByPedNombreBordado", query = "SELECT p FROM PedidoBordado p WHERE p.pedNombreBordado = :pedNombreBordado")
    , @NamedQuery(name = "PedidoBordado.findByPedPuntadas", query = "SELECT p FROM PedidoBordado p WHERE p.pedPuntadas = :pedPuntadas")
    , @NamedQuery(name = "PedidoBordado.findByPedValor", query = "SELECT p FROM PedidoBordado p WHERE p.pedValor = :pedValor")
    , @NamedQuery(name = "PedidoBordado.findByPedCantidad", query = "SELECT p FROM PedidoBordado p WHERE p.pedCantidad = :pedCantidad")})
public class PedidoBordado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ped_codigo")
    private Integer pedCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ped_nombre_bordado")
    private String pedNombreBordado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ped_puntadas")
    private BigDecimal pedPuntadas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ped_valor")
    private BigDecimal pedValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ped_cantidad")
    private BigDecimal pedCantidad;
    @NotNull
    @Column(name = "ped_valor_unitario")
    private BigDecimal ped_valor_unitario;
    @NotNull
    @Column(name = "ped_subtotal")
    private BigDecimal ped_subtotal;
    @Column(name = "ped_ganacia")
    private BigDecimal ped_ganacia;
    @Column(name ="ped_observaciones")
    private String ped_observaciones;
    @JoinColumn(name = "pca_codigo", referencedColumnName = "pca_codigo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PedidoBordadoCabecera pcaCodigo;

    public PedidoBordado() {
    }

    public PedidoBordado(Integer pedCodigo) {
        this.pedCodigo = pedCodigo;
    }

    public PedidoBordado(Integer pedCodigo, String pedNombreBordado, BigDecimal pedPuntadas, BigDecimal pedValor, BigDecimal pedCantidad) {
        this.pedCodigo = pedCodigo;
        this.pedNombreBordado = pedNombreBordado;
        this.pedPuntadas = pedPuntadas;
        this.pedValor = pedValor;
        this.pedCantidad = pedCantidad;
    }
    
    public void limpiar() {
    	 this.pedCodigo = 0;
         this.pedNombreBordado = "";
         this.pedPuntadas = BigDecimal.ZERO;
         this.pedValor = BigDecimal.ZERO;
         this.pedCantidad = BigDecimal.ZERO;
    }

    public Integer getPedCodigo() {
        return pedCodigo;
    }

    public void setPedCodigo(Integer pedCodigo) {
        this.pedCodigo = pedCodigo;
    }

    public String getPedNombreBordado() {
        return pedNombreBordado;
    }

    public void setPedNombreBordado(String pedNombreBordado) {
        this.pedNombreBordado = pedNombreBordado;
    }

    public BigDecimal getPedPuntadas() {
        return pedPuntadas;
    }

    public void setPedPuntadas(BigDecimal pedPuntadas) {
        this.pedPuntadas = pedPuntadas;
    }

    public BigDecimal getPedValor() {
        return pedValor;
    }

    public void setPedValor(BigDecimal pedValor) {
        this.pedValor = pedValor;
    }

    public BigDecimal getPedCantidad() {
        return pedCantidad;
    }

    public void setPedCantidad(BigDecimal pedCantidad) {
        this.pedCantidad = pedCantidad;
    }

    public PedidoBordadoCabecera getPcaCodigo() {
        return pcaCodigo;
    }

    public void setPcaCodigo(PedidoBordadoCabecera pcaCodigo) {
        this.pcaCodigo = pcaCodigo;
    }
    
    public BigDecimal getPed_valor_unitario() {
		return ped_valor_unitario;
	}

	public void setPed_valor_unitario(BigDecimal ped_valor_unitario) {
		this.ped_valor_unitario = ped_valor_unitario;
	}
	
	public BigDecimal getPed_subtotal() {
		return ped_subtotal;
	}

	public void setPed_subtotal(BigDecimal ped_subtotal) {
		this.ped_subtotal = ped_subtotal;
	}
	
	public BigDecimal getPed_ganacia() {
		return ped_ganacia;
	}

	public void setPed_ganacia(BigDecimal ped_ganacia) {
		this.ped_ganacia = ped_ganacia;
	}
	
	public String getPed_observaciones() {
		return ped_observaciones;
	}

	public void setPed_observaciones(String ped_observaciones) {
		this.ped_observaciones = ped_observaciones;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pedCodigo != null ? pedCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoBordado)) {
            return false;
        }
        PedidoBordado other = (PedidoBordado) object;
        if ((this.pedCodigo == null && other.pedCodigo != null) || (this.pedCodigo != null && !this.pedCodigo.equals(other.pedCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bordados.modelo.PedidoBordado[ pedCodigo=" + pedCodigo + " ]";
    }
    
}
