/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.modelo;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
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
@Table(name = "bordador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bordador.findAll", query = "SELECT b FROM Bordador b")
    , @NamedQuery(name = "Bordador.findByBorCodigo", query = "SELECT b FROM Bordador b WHERE b.borCodigo = :borCodigo")
    , @NamedQuery(name = "Bordador.findByBorCaducado", query = "SELECT b FROM Bordador b WHERE b.borCaducado = :borCaducado")})
public class Bordador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bor_codigo")
    private Integer borCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bor_caducado")
    @Temporal(TemporalType.DATE)
    private Date borCaducado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "borCodigo", fetch = FetchType.LAZY)
    private List<PedidoBordadoCabecera> pedidoBordadoCabeceraList;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo")
    @OneToOne(optional = false)
    private Persona perCodigo;

    public Bordador() {
    }

    public Bordador(Integer borCodigo) {
        this.borCodigo = borCodigo;
    }

    public Bordador(Integer borCodigo, Date borCaducado) {
        this.borCodigo = borCodigo;
        this.borCaducado = borCaducado;
    }

    public Integer getBorCodigo() {
        return borCodigo;
    }

    public void setBorCodigo(Integer borCodigo) {
        this.borCodigo = borCodigo;
    }

    public Date getBorCaducado() {
        return borCaducado;
    }

    public void setBorCaducado(Date borCaducado) {
        this.borCaducado = borCaducado;
    }

    @XmlTransient
    public List<PedidoBordadoCabecera> getPedidoBordadoCabeceraList() {
        return pedidoBordadoCabeceraList;
    }

    public void setPedidoBordadoCabeceraList(List<PedidoBordadoCabecera> pedidoBordadoCabeceraList) {
        this.pedidoBordadoCabeceraList = pedidoBordadoCabeceraList;
    }

    public Persona getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Persona perCodigo) {
        this.perCodigo = perCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (borCodigo != null ? borCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bordador)) {
            return false;
        }
        Bordador other = (Bordador) object;
        if ((this.borCodigo == null && other.borCodigo != null) || (this.borCodigo != null && !this.borCodigo.equals(other.borCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
    	return "com.mycompany.bordados.modelo.Bordador[ borCodigo=" + borCodigo + " ]";
        //return "com.mycompany.bordados.modelo.Bordador[ borCodigo=" + borCodigo + " ]";
    }
    
}
