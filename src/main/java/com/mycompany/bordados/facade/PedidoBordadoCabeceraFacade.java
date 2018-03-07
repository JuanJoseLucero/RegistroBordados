/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mycompany.bordados.modelo.PedidoBordadoCabecera_;
import com.mycompany.bordados.utilSQL.DataSourceBordados;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.bordados.modelo.Bordador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.PedidoBordado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author juan
 */
@Stateless
public class PedidoBordadoCabeceraFacade extends AbstractFacade<PedidoBordadoCabecera> {
	
	@Inject
	DataSourceBordados dataSourceBordado;
	
	@Inject
	BordadorFacade bordadorFacade;
	@Inject
	ClienteFacade clienteFacade;
	@Inject
	DiseniadorFacade diseniadorFacade;

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoBordadoCabeceraFacade() {
        super(PedidoBordadoCabecera.class);
    }
    
    public List<PedidoBordadoCabecera> registrosByFechaConnection (Date fecha, Integer nombre){
    	Connection connPostges = null;
    	List<PedidoBordadoCabecera> lst = new ArrayList<>();
    	String consolidadoNombres ="";
    	
		try {
			connPostges = dataSourceBordado.getConnection();
			PreparedStatement pr =null;
			if(nombre!=null){
				pr = connPostges.prepareStatement("select * from pedido_bordado_cabecera where cli_codigo= ?");
				pr.setInt(1,nombre);
			}			
			ResultSet rs = pr.executeQuery();
			
			PreparedStatement psDetalle = connPostges.prepareStatement("select ped_codigo, pca_codigo ,ped_nombre_bordado, ped_puntadas, ped_valor, ped_cantidad , ped_valor_unitario, ped_subtotal, ped_observaciones, ped_ganacia from pedido_bordado where pca_codigo= ?");
			while(rs.next()) {
				PedidoBordadoCabecera cabecera = new PedidoBordadoCabecera();
				cabecera.setPcaCodigo(rs.getInt("pca_codigo"));
				cabecera.setPcaFecha(rs.getDate("pca_fecha"));
				cabecera.setPcaFechaEntrega(rs.getDate("pca_fecha_entrega"));
				cabecera.setPcaTotal(rs.getBigDecimal("pca_total"));
				cabecera.setPca_estado_pago(rs.getString("pca_estado_pago"));
				cabecera.setBorCodigo(bordadorFacade.find(rs.getInt("bor_codigo")));
				cabecera.setCliCodigo(clienteFacade.find(rs.getInt("cli_codigo")));
				cabecera.setDisCodigo(diseniadorFacade.find(rs.getBigDecimal("dis_codigo")));
				psDetalle.setInt(1, cabecera.getPcaCodigo());
				ResultSet rsD = psDetalle.executeQuery();
				List<PedidoBordado> lstD = new ArrayList<>();
				while(rsD.next()) {
					PedidoBordado pb= new PedidoBordado();
					pb.setPedCodigo(rsD.getInt("ped_codigo"));
					pb.setPedNombreBordado(rsD.getString("ped_nombre_bordado"));
					pb.setPedPuntadas(rsD.getBigDecimal("ped_puntadas"));
					pb.setPedValor(rsD.getBigDecimal("ped_valor"));
					pb.setPedCantidad(rsD.getBigDecimal("ped_cantidad"));
					pb.setPed_subtotal(rsD.getBigDecimal("ped_subtotal"));
					pb.setPed_observaciones(rsD.getString("ped_observaciones"));
					pb.setPed_ganacia(rsD.getBigDecimal("ped_ganacia"));
					pb.setPed_valor_unitario(rsD.getBigDecimal("ped_valor_unitario"));
					pb.setPcaCodigo(cabecera);
					consolidadoNombres = consolidadoNombres.concat(" ").concat(rsD.getString("ped_nombre_bordado"));
					lstD.add(pb);
				}
				cabecera.setPedidoBordadoList(lstD);
				lst.add(cabecera);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(!connPostges.isClosed()) {
					connPostges.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lst;
    }
    
    
    public List<PedidoBordadoCabecera> registrosByFecha (Date fecha){
    	TypedQuery<PedidoBordadoCabecera> consultaCabeceras= em.createNamedQuery("PedidoBordadoCabecera.findByPcaFecha", PedidoBordadoCabecera.class);
    	consultaCabeceras.setParameter("pcaFecha", fecha);
    	List<PedidoBordadoCabecera> lista= consultaCabeceras.getResultList();
    	return lista;
    }

    public boolean isBorCodigoEmpty(PedidoBordadoCabecera entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PedidoBordadoCabecera> pedidoBordadoCabecera = cq.from(PedidoBordadoCabecera.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pedidoBordadoCabecera, entity), cb.isNotNull(pedidoBordadoCabecera.get(PedidoBordadoCabecera_.borCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Bordador findBorCodigo(PedidoBordadoCabecera entity) {
        return this.getMergedEntity(entity).getBorCodigo();
    }

    public boolean isCliCodigoEmpty(PedidoBordadoCabecera entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PedidoBordadoCabecera> pedidoBordadoCabecera = cq.from(PedidoBordadoCabecera.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pedidoBordadoCabecera, entity), cb.isNotNull(pedidoBordadoCabecera.get(PedidoBordadoCabecera_.cliCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Cliente findCliCodigo(PedidoBordadoCabecera entity) {
        return this.getMergedEntity(entity).getCliCodigo();
    }

    public boolean isDisCodigoEmpty(PedidoBordadoCabecera entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PedidoBordadoCabecera> pedidoBordadoCabecera = cq.from(PedidoBordadoCabecera.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pedidoBordadoCabecera, entity), cb.isNotNull(pedidoBordadoCabecera.get(PedidoBordadoCabecera_.disCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Diseniador findDisCodigo(PedidoBordadoCabecera entity) {
        return this.getMergedEntity(entity).getDisCodigo();
    }

    public boolean isPedidoBordadoListEmpty(PedidoBordadoCabecera entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PedidoBordadoCabecera> pedidoBordadoCabecera = cq.from(PedidoBordadoCabecera.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pedidoBordadoCabecera, entity), cb.isNotEmpty(pedidoBordadoCabecera.get(PedidoBordadoCabecera_.pedidoBordadoList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PedidoBordado> findPedidoBordadoList(PedidoBordadoCabecera entity) {
        PedidoBordadoCabecera mergedEntity = this.getMergedEntity(entity);
        List<PedidoBordado> pedidoBordadoList = mergedEntity.getPedidoBordadoList();
        pedidoBordadoList.size();
        return pedidoBordadoList;
    }

    @Override
    public PedidoBordadoCabecera findWithParents(PedidoBordadoCabecera entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PedidoBordadoCabecera> cq = cb.createQuery(PedidoBordadoCabecera.class);
        Root<PedidoBordadoCabecera> pedidoBordadoCabecera = cq.from(PedidoBordadoCabecera.class);
        pedidoBordadoCabecera.fetch(PedidoBordadoCabecera_.borCodigo);
        pedidoBordadoCabecera.fetch(PedidoBordadoCabecera_.cliCodigo);
        pedidoBordadoCabecera.fetch(PedidoBordadoCabecera_.disCodigo);
        cq.select(pedidoBordadoCabecera).where(cb.equal(pedidoBordadoCabecera, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
