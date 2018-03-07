package com.mycompany.bordados.modelo;

import com.mycompany.bordados.modelo.Bordador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.PedidoBordado;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T21:56:30")
@StaticMetamodel(PedidoBordadoCabecera.class)
public class PedidoBordadoCabecera_ { 

    public static volatile SingularAttribute<PedidoBordadoCabecera, BigDecimal> pcaTotal;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Date> pcaFecha;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Diseniador> disCodigo;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Bordador> borCodigo;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Integer> pcaCodigo;
    public static volatile ListAttribute<PedidoBordadoCabecera, PedidoBordado> pedidoBordadoList;
    public static volatile SingularAttribute<PedidoBordadoCabecera, String> pca_estado_pago;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Cliente> cliCodigo;
    public static volatile SingularAttribute<PedidoBordadoCabecera, Date> pcaFechaEntrega;

}