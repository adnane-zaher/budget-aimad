/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.EntiteAdministratif;
import bean.Faculte;
import bean.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class EntiteAdministratifFacade extends AbstractFacade<EntiteAdministratif> {
    @PersistenceContext(unitName = "budget_run2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntiteAdministratifFacade() {
        super(EntiteAdministratif.class);
    }
    
    public List<EntiteAdministratif> findByFaculte(Faculte faculte){
        if(faculte !=null && faculte.getId()!= null){
            String requette = "SELECT ea FROM EntiteAdministratif ea WHERE ea.faculte.id ="+faculte.getId();
            return em.createQuery(requette).getResultList();
        }
        return new ArrayList();
    }
    
    public EntiteAdministratif findByUser(User user ){
        if(user !=null && user.getLogin()!=null ){
            String requette = "SELECT ea FROM EntiteAdministratif ea WHERE ea.chef.login = '"+user.getLogin()+"'";
            return (EntiteAdministratif) em.createQuery(requette).getSingleResult();
        }
        return new EntiteAdministratif();
    }
    
}
