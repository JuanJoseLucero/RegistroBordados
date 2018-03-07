package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import com.mycompany.bordados.modelo.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(Bordador.class)
public class Bordador_ { 

    public static volatile ListAttribute<Bordador, PedidoBordadoCabecera> pedidoBordadoCabeceraList;
    public static volatile SingularAttribute<Bordador, Date> borCaducado;
    public static volatile SingularAttribute<Bordador, Integer> borCodigo;
    public static volatile SingularAttribute<Bordador, Persona> perCodigo;

}