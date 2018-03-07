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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByCliCodigo", query = "SELECT c FROM Cliente c WHERE c.cliCodigo = :cliCodigo")
    , @NamedQuery(name = "Cliente.findByCliFcaducado", query = "SELECT c FROM Cliente c WHERE c.cliFcaducado = :cliFcaducado")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cli_codigo")
    private Integer cliCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cli_fcaducado")
    @Temporal(TemporalType.DATE)
    private Date cliFcaducado;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo")
    @OneToOne(optional = false)
    private Persona perCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliCodigo", fetch = FetchType.LAZY)
    private List<PedidoBordadoCabecera> pedidoBordadoCabeceraList;

    public Cliente() {
    }

    public Cliente(Integer cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    public Cliente(Integer cliCodigo, Date cliFcaducado) {
        this.cliCodigo = cliCodigo;
        this.cliFcaducado = cliFcaducado;
    }

    public Integer getCliCodigo() {
        return cliCodigo;
    }

    public void setCliCodigo(Integer cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    public Date getCliFcaducado() {
        return cliFcaducado;
    }

    public void setCliFcaducado(Date cliFcaducado) {
        this.cliFcaducado = cliFcaducado;
    }

    public Persona getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Persona perCodigo) {
        this.perCodigo = perCodigo;
    }

    @XmlTransient
    public List<PedidoBordadoCabecera> getPedidoBordadoCabeceraList() {
        return pedidoBordadoCabeceraList;
    }

    public void setPedidoBordadoCabeceraList(List<PedidoBordadoCabecera> pedidoBordadoCabeceraList) {
        this.pedidoBordadoCabeceraList = pedidoBordadoCabeceraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliCodigo != null ? cliCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cliCodigo == null && other.cliCodigo != null) || (this.cliCodigo != null && !this.cliCodigo.equals(other.cliCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bordados.modelo.Cliente[ cliCodigo=" + cliCodigo + " ]";
    }
    
}
