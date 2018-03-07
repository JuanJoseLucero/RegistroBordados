package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import com.mycompany.bordados.modelo.Persona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile ListAttribute<Cliente, PedidoBordadoCabecera> pedidoBordadoCabeceraList;
    public static volatile SingularAttribute<Cliente, Date> cliFcaducado;
    public static volatile SingularAttribute<Cliente, Persona> perCodigo;
    public static volatile SingularAttribute<Cliente, Integer> cliCodigo;

}