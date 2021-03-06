/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.SyntheseByEntiteAdministratif;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class SyntheseByEntiteAdministratifFacade extends AbstractFacade<SyntheseByEntiteAdministratif> {
    @PersistenceContext(unitName = "budget_run2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SyntheseByEntiteAdministratifFacade() {
        super(SyntheseByEntiteAdministratif.class);
    }
    
}
