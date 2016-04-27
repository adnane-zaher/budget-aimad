/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adnane
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @EJB
    FaculteFacade faculteFacade ;
    @EJB
    EntiteAdministratifFacade entiteAdministratifFacade;
    
    @PersistenceContext(unitName = "budget_en_ligne_last_versionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
     public int seConnnecter(User user) {
        if (user == null || user.getLogin() == null) {
            return -1;
        } else {
            User loadedUser = find(user.getLogin());
            if (loadedUser == null) {
                return -2;
            } else if (!loadedUser.getPassword().equals(user.getPassword())) {
                return -3;
            
            } else {
              user.setFaculte(faculteFacade.findByUser(user));
              int type = loadedUser.getType();
              user.setType(type);
              if(type == 1){
              user.setEntiteAdministratif(entiteAdministratifFacade.findByUser(user));
              
              }
                return 1;
            }
        }
    }
}
