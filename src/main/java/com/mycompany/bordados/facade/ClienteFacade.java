/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.bordados.modelo.Cliente_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.bordados.modelo.Persona;
import com.mycompany.bordados.modelo.PedidoBordadoCabecera;
import java.util.List;

/**
 *
 * @author juan
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public boolean isPerCodigoEmpty(Cliente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cliente, entity), cb.isNotNull(cliente.get(Cliente_.perCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Persona findPerCodigo(Cliente entity) {
        return this.getMergedEntity(entity).getPerCodigo();
    }

    public boolean isPedidoBordadoCabeceraListEmpty(Cliente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cliente, entity), cb.isNotEmpty(cliente.get(Cliente_.pedidoBordadoCabeceraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PedidoBordadoCabecera> findPedidoBordadoCabeceraList(Cliente entity) {
        Cliente mergedEntity = this.getMergedEntity(entity);
        List<PedidoBordadoCabecera> pedidoBordadoCabeceraList = mergedEntity.getPedidoBordadoCabeceraList();
        pedidoBordadoCabeceraList.size();
        return pedidoBordadoCabeceraList;
    }

    @Override
    public Cliente findWithParents(Cliente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = cq.from(Cliente.class);
        cliente.fetch(Cliente_.perCodigo);
        cq.select(cliente).where(cb.equal(cliente, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
