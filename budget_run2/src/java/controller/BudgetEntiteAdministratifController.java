package controller;

import bean.BudgetEntiteAdministratif;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.BudgetEntiteAdministratifFacade;

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

@Named("budgetEntiteAdministratifController")
@SessionScoped
public class BudgetEntiteAdministratifController implements Serializable {

    @EJB
    private service.BudgetEntiteAdministratifFacade ejbFacade;
    private List<BudgetEntiteAdministratif> items = null;
    private BudgetEntiteAdministratif selected;

    public BudgetEntiteAdministratifController() {
    }

    public BudgetEntiteAdministratif getSelected() {
        return selected;
    }

    public void setSelected(BudgetEntiteAdministratif selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BudgetEntiteAdministratifFacade getFacade() {
        return ejbFacade;
    }

    public BudgetEntiteAdministratif prepareCreate() {
        selected = new BudgetEntiteAdministratif();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BudgetEntiteAdministratif> getItems() {
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

    public BudgetEntiteAdministratif getBudgetEntiteAdministratif(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<BudgetEntiteAdministratif> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BudgetEntiteAdministratif> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BudgetEntiteAdministratif.class)
    public static class BudgetEntiteAdministratifControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BudgetEntiteAdministratifController controller = (BudgetEntiteAdministratifController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "budgetEntiteAdministratifController");
            return controller.getBudgetEntiteAdministratif(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof BudgetEntiteAdministratif) {
                BudgetEntiteAdministratif o = (BudgetEntiteAdministratif) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BudgetEntiteAdministratif.class.getName()});
                return null;
            }
        }

    }

}
