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
@Table(name = "diseniador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diseniador.findAll", query = "SELECT d FROM Diseniador d")
    , @NamedQuery(name = "Diseniador.findByDisCodigo", query = "SELECT d FROM Diseniador d WHERE d.disCodigo = :disCodigo")
    , @NamedQuery(name = "Diseniador.findByDisCaducado", query = "SELECT d FROM Diseniador d WHERE d.disCaducado = :disCaducado")})
public class Diseniador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dis_codigo")
    private BigDecimal disCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dis_caducado")
    @Temporal(TemporalType.DATE)
    private Date disCaducado;
    @JoinColumn(name = "per_codigo", referencedColumnName = "per_codigo")
    @OneToOne(optional = false)
    private Persona perCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disCodigo", fetch = FetchType.LAZY)
    private List<PedidoBordadoCabecera> pedidoBordadoCabeceraList;

    public Diseniador() {
    }

    public Diseniador(BigDecimal disCodigo) {
        this.disCodigo = disCodigo;
    }

    public Diseniador(BigDecimal disCodigo, Date disCaducado) {
        this.disCodigo = disCodigo;
        this.disCaducado = disCaducado;
    }

    public BigDecimal getDisCodigo() {
        return disCodigo;
    }

    public void setDisCodigo(BigDecimal disCodigo) {
        this.disCodigo = disCodigo;
    }

    public Date getDisCaducado() {
        return disCaducado;
    }

    public void setDisCaducado(Date disCaducado) {
        this.disCaducado = disCaducado;
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
        hash += (disCodigo != null ? disCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diseniador)) {
            return false;
        }
        Diseniador other = (Diseniador) object;
        if ((this.disCodigo == null && other.disCodigo != null) || (this.disCodigo != null && !this.disCodigo.equals(other.disCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.bordados.modelo.Diseniador[ disCodigo=" + disCodigo + " ]";
    }
    
}
