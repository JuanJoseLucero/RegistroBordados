package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(PedidoBordado.class)
public class PedidoBordado_ { 

    public static volatile SingularAttribute<PedidoBordado, BigDecimal> pedPuntadas;
    public static volatile SingularAttribute<PedidoBordado, BigDecimal> pedValor;
    public static volatile SingularAttribute<PedidoBordado, BigDecimal> pedCantidad;
    public static volatile SingularAttribute<PedidoBordado, PedidoBordadoCabecera> pcaCodigo;
    public static volatile SingularAttribute<PedidoBordado, BigDecimal> ped_ganacia;
    public static volatile SingularAttribute<PedidoBordado, String> pedNombreBordado;
    public static volatile SingularAttribute<PedidoBordado, Integer> pedCodigo;
    public static volatile SingularAttribute<PedidoBordado, BigDecimal> ped_valor_unitario;
    public static volatile SingularAttribute<PedidoBordado, BigDecimal> ped_subtotal;
    public static volatile SingularAttribute<PedidoBordado, String> ped_observaciones;

}