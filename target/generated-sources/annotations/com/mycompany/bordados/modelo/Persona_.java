package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.Bordador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Diseniador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> perNombre;
    public static volatile ListAttribute<Persona, Cliente> clienteList;
    public static volatile SingularAttribute<Persona, String> perDireccion;
    public static volatile SingularAttribute<Persona, Date> perFcaducado;
    public static volatile ListAttribute<Persona, Bordador> bordadorList;
    public static volatile SingularAttribute<Persona, String> perTelefono;
    public static volatile ListAttribute<Persona, Diseniador> diseniadorList;
    public static volatile SingularAttribute<Persona, Integer> perCodigo;
    public static volatile SingularAttribute<Persona, Bordador> bordador;
    public static volatile SingularAttribute<Persona, String> perApellido;
    public static volatile SingularAttribute<Persona, String> perEmail;

}