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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByPerCodigo", query = "SELECT p FROM Persona p WHERE p.perCodigo = :perCodigo")
    , @NamedQuery(name = "Persona.findByPerNombre", query = "SELECT p FROM Persona p WHERE p.perNombre = :perNombre")
    , @NamedQuery(name = "Persona.findByPerApellido", query = "SELECT p FROM Persona p WHERE p.perApellido = :perApellido")
    , @NamedQuery(name = "Persona.findByPerTelefono", query = "SELECT p FROM Persona p WHERE p.perTelefono = :perTelefono")
    , @NamedQuery(name = "Persona.findByPerDireccion", query = "SELECT p FROM Persona p WHERE p.perDireccion = :perDireccion")
    , @NamedQuery(name = "Persona.findByPerEmail", query = "SELECT p FROM Persona p WHERE p.perEmail = :perEmail")
    , @NamedQuery(name = "Persona.findByPerFcaducado", query = "SELECT p FROM Persona p WHERE p.perFcaducado = :perFcaducado")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "per_codigo")
    private Integer perCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "per_nombre")
    private String perNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "per_apellido")
    private String perApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "per_telefono")
    private String perTelefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "per_direccion")
    private String perDireccion;
    @Size(max = 2147483647)
    @Column(name = "per_email")
    private String perEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "per_fcaducado")
    @Temporal(TemporalType.DATE)
    private Date perFcaducado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private List<Diseniador> diseniadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private List<Bordador> bordadorList;
    
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private <Diseniador> diseniadorList;
        
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "perCodigo", fetch = FetchType.LAZY)
    private Bordador bordador;
    

    public Persona() {
    }

    public Persona(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public Persona(Integer perCodigo, String perNombre, String perApellido, String perTelefono, String perDireccion, Date perFcaducado) {
        this.perCodigo = perCodigo;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perTelefono = perTelefono;
        this.perDireccion = perDireccion;
        this.perFcaducado = perFcaducado;
    }

    public Integer getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerTelefono() {
        return perTelefono;
    }

    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public Date getPerFcaducado() {
        return perFcaducado;
    }

    public void setPerFcaducado(Date perFcaducado) {
        this.perFcaducado = perFcaducado;
    }

    @XmlTransient
    public List<Diseniador> getDiseniadorList() {
        return diseniadorList;
    }

    public void setDiseniadorList(List<Diseniador> diseniadorList) {
        this.diseniadorList = diseniadorList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

//    @XmlTransient
//    public List<Bordador> getBordadorList() {
//        return bordadorList;
//    }
//
//    public void setBordadorList(List<Bordador> bordadorList) {
//        this.bordadorList = bordadorList;
//    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perCodigo != null ? perCodigo.hashCode() : 0);
        return hash;
    }

	public Bordador getBordador() {
		return bordador;
	}

	public void setBordador(Bordador bordador) {
		this.bordador = bordador;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.perCodigo == null && other.perCodigo != null) || (this.perCodigo != null && !this.perCodigo.equals(other.perCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return perNombre.concat(" ").concat(perApellido);
    }
    
}
