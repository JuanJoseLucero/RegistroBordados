/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.Bordador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.bordados.modelo.Bordador_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import com.mycompany.bordados.modelo.Persona;
import java.util.List;

/**
 *
 * @author juan
 */
@Stateless
public class BordadorFacade extends AbstractFacade<Bordador> {

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BordadorFacade() {
        super(Bordador.class);
    }

    public boolean isPedidoBordadoCabeceraListEmpty(Bordador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bordador> bordador = cq.from(Bordador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bordador, entity), cb.isNotEmpty(bordador.get(Bordador_.pedidoBordadoCabeceraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PedidoBordadoCabecera> findPedidoBordadoCabeceraList(Bordador entity) {
        Bordador mergedEntity = this.getMergedEntity(entity);
        List<PedidoBordadoCabecera> pedidoBordadoCabeceraList = mergedEntity.getPedidoBordadoCabeceraList();
        pedidoBordadoCabeceraList.size();
        return pedidoBordadoCabeceraList;
    }

    public boolean isPerCodigoEmpty(Bordador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Bordador> bordador = cq.from(Bordador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(bordador, entity), cb.isNotNull(bordador.get(Bordador_.perCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Persona findPerCodigo(Bordador entity) {
        return this.getMergedEntity(entity).getPerCodigo();
    }

    @Override
    public Bordador findWithParents(Bordador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Bordador> cq = cb.createQuery(Bordador.class);
        Root<Bordador> bordador = cq.from(Bordador.class);
        bordador.fetch(Bordador_.perCodigo);
        cq.select(bordador).where(cb.equal(bordador, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
