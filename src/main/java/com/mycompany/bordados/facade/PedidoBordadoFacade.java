/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.PedidoBordado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.bordados.modelo.PedidoBordado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.bordados.modelo.PedidoBordadoCabecera;

/**
 *
 * @author juan
 */
@Stateless
public class PedidoBordadoFacade extends AbstractFacade<PedidoBordado> {

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoBordadoFacade() {
        super(PedidoBordado.class);
    }

    public boolean isPcaCodigoEmpty(PedidoBordado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PedidoBordado> pedidoBordado = cq.from(PedidoBordado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(pedidoBordado, entity), cb.isNotNull(pedidoBordado.get(PedidoBordado_.pcaCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public PedidoBordadoCabecera findPcaCodigo(PedidoBordado entity) {
        return this.getMergedEntity(entity).getPcaCodigo();
    }

    @Override
    public PedidoBordado findWithParents(PedidoBordado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PedidoBordado> cq = cb.createQuery(PedidoBordado.class);
        Root<PedidoBordado> pedidoBordado = cq.from(PedidoBordado.class);
        pedidoBordado.fetch(PedidoBordado_.pcaCodigo);
        cq.select(pedidoBordado).where(cb.equal(pedidoBordado, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
