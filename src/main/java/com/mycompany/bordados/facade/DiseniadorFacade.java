/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.Diseniador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.mycompany.bordados.modelo.Diseniador_;
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
public class DiseniadorFacade extends AbstractFacade<Diseniador> {

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiseniadorFacade() {
        super(Diseniador.class);
    }

    public boolean isPerCodigoEmpty(Diseniador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Diseniador> diseniador = cq.from(Diseniador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diseniador, entity), cb.isNotNull(diseniador.get(Diseniador_.perCodigo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Persona findPerCodigo(Diseniador entity) {
        return this.getMergedEntity(entity).getPerCodigo();
    }

    public boolean isPedidoBordadoCabeceraListEmpty(Diseniador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Diseniador> diseniador = cq.from(Diseniador.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diseniador, entity), cb.isNotEmpty(diseniador.get(Diseniador_.pedidoBordadoCabeceraList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PedidoBordadoCabecera> findPedidoBordadoCabeceraList(Diseniador entity) {
        Diseniador mergedEntity = this.getMergedEntity(entity);
        List<PedidoBordadoCabecera> pedidoBordadoCabeceraList = mergedEntity.getPedidoBordadoCabeceraList();
        pedidoBordadoCabeceraList.size();
        return pedidoBordadoCabeceraList;
    }

    @Override
    public Diseniador findWithParents(Diseniador entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Diseniador> cq = cb.createQuery(Diseniador.class);
        Root<Diseniador> diseniador = cq.from(Diseniador.class);
        diseniador.fetch(Diseniador_.perCodigo);
        cq.select(diseniador).where(cb.equal(diseniador, entity));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return entity;
        }
    }
    
}
