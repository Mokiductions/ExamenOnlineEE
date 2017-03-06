/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mokiductions.sessionbeans;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.mokiductions.entities.Questions;
import org.mokiductions.entities.Types;

/**
 *
 * @author Miquel Ginés Borràs
 * @mail ginesborrasm@gmail.com
 */
@PermitAll
@Stateless
public class QuestionsFacade extends AbstractFacade<Questions> {

    @PersistenceContext(unitName = "ExamenOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionsFacade() {
        super(Questions.class);
    }
    
    public List<Questions> findQuestionsAndOptionsByType(Integer idtype) {
        Types type = em.find(Types.class, idtype);
        Query query = em.createQuery("SELECT DISTINCT q FROM Questions q JOIN FETCH q.optionsList WHERE q.questionsIdType=:type ORDER BY q.questionsId")
                .setParameter("type", type);
        return (List<Questions>) query.getResultList();
    }
    
    public List<Questions> findAllText() {
        Types type = em.find(Types.class, 4); // Tipo texto
        Query query = em.createQuery("SELECT DISTINCT q FROM Questions q WHERE q.questionsIdType=:type")
                .setParameter("type", type);
        return (List<Questions>) query.getResultList();
    }
    
    public Questions findById(int id) {
        List results = em.createNamedQuery("Questions.findByQuestionsId")
                .setParameter("questionsId", id).getResultList();
        return (Questions) results.get(0);
    }
}
