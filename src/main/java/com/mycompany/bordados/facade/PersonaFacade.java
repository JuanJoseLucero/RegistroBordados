/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bordados.facade;

import com.mycompany.bordados.modelo.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.bordados.modelo.Diseniador;
import com.mycompany.bordados.modelo.Cliente;
import com.mycompany.bordados.modelo.Bordador;
import java.util.List;

/**
 *
 * @author juan
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "BORDADOS")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

//    public boolean isDiseniadorListEmpty(Persona entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Persona> persona = cq.from(Persona.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotEmpty(persona.get(Persona_.diseniadorList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }

    public List<Diseniador> findDiseniadorList(Persona entity) {
        Persona mergedEntity = this.getMergedEntity(entity);
        List<Diseniador> diseniadorList = mergedEntity.getDiseniadorList();
        diseniadorList.size();
        return diseniadorList;
    }

//    public boolean isClienteListEmpty(Persona entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Persona> persona = cq.from(Persona.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotEmpty(persona.get(Persona_.clienteList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }

//    public List<Cliente> findClienteList(Persona entity) {
//        Persona mergedEntity = this.getMergedEntity(entity);
//        List<Cliente> clienteList = mergedEntity.getClienteList();
//        clienteList.size();
//        return clienteList;
//    }

//    public boolean isBordadorListEmpty(Persona entity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Persona> persona = cq.from(Persona.class);
//        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(persona, entity), cb.isNotEmpty(persona.get(Persona_.bordadorList)));
//        return em.createQuery(cq).getResultList().isEmpty();
//    }
//
//    public List<Bordador> findBordadorList(Persona entity) {
//        Persona mergedEntity = this.getMergedEntity(entity);
//        List<Bordador> bordadorList = mergedEntity.getBordadorList();
//        bordadorList.size();
//        return bordadorList;
//    }
    
}
