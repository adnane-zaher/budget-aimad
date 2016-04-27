package controller;

import bean.User;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Message;
import controller.util.MessageManager;
import controller.util.SessionUtil;
import service.UserFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import service.FaculteFacade;

@Named("userController")
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private service.UserFacade ejbFacade;
    @EJB
    private FaculteFacade faculteFacade ;
    private List<User> items = null;
    private User selected;
    private int role;
    private Message message;
    
    
    public UserController() {
    }
    
    public String seConnnecter() {
        System.out.println("hadaa khedam");
        int res = ejbFacade.seConnnecter(selected);
        if (res == 1) {
            role = selected.getType();
            System.out.println("o daba"+role);
            faculteFacade.initFaculteParams(selected.getFaculte());
            System.out.println(SessionUtil.getCurrentFaculte());
            SessionUtil.attachUserToSession(selected);
            return "/menu/menu?faces-redirect=true";
        }
            validteConnexionForm(res);
            return null;
        
        
    }
    
    private void validteConnexionForm(int res) {
        message = MessageManager.createErrorMessage(res, "");
        if (res == -1) {
            message.setText(" Attention Emty champ login ");
        } else if (res == -2) {
            message.setText("Attention User non trouvé");
        } else if (res == -3) {
            message.setText("Erreur password, Réessayer SVP");
        } 
        MessageManager.showMessage(message);
    }
    
    public String seDeConnnecter() {
        // invalidate Session
        
        SessionUtil.getSession().removeAttribute("user");
        return "/index";
    }

    public User getSelected() {
        if(selected == null){
            selected = new User();
        }
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }
    
    public User clone(User user){
        User myClone = new User();
        myClone.setAdresse(user.getAdresse());
        myClone.setEmail(user.getEmail());
        myClone.setEntiteAdministratif(user.getEntiteAdministratif());
        myClone.setFaculte(user.getFaculte());
        myClone.setLogin(user.getLogin());
        myClone.setNom(user.getNom());
        myClone.setPassword(user.getPassword());
        myClone.setPrenom(user.getPrenom());
        myClone.setTel(user.getTel());
        myClone.setType(user.getType());
        return myClone;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public int getRole() {
       //role = SessionUtil.getConnectedUser().getType();
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    

    private UserFacade getFacade() {
        return ejbFacade;
    }

    public User prepareCreate() {
        selected = new User();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<User> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public User getUser(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<User> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<User> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public FaculteFacade getFaculteFacade() {
        return faculteFacade;
    }

    public void setFaculteFacade(FaculteFacade faculteFacade) {
        this.faculteFacade = faculteFacade;
    }

    
    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getLogin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), User.class.getName()});
                return null;
            }
        }

    }

}
