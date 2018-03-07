package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import com.mycompany.bordados.modelo.Persona;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(Diseniador.class)
public class Diseniador_ { 

    public static volatile ListAttribute<Diseniador, PedidoBordadoCabecera> pedidoBordadoCabeceraList;
    public static volatile SingularAttribute<Diseniador, BigDecimal> disCodigo;
    public static volatile SingularAttribute<Diseniador, Date> disCaducado;
    public static volatile SingularAttribute<Diseniador, Persona> perCodigo;

}