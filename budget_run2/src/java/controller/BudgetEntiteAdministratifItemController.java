package controller;

import bean.BudgetEntiteAdministratifItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.BudgetEntiteAdministratifItemFacade;

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

@Named("budgetEntiteAdministratifItemController")
@SessionScoped
public class BudgetEntiteAdministratifItemController implements Serializable {

    @EJB
    private service.BudgetEntiteAdministratifItemFacade ejbFacade;
    private List<BudgetEntiteAdministratifItem> items = null;
    private BudgetEntiteAdministratifItem selected;

    public BudgetEntiteAdministratifItemController() {
    }

    public BudgetEntiteAdministratifItem getSelected() {
        return selected;
    }

    public void setSelected(BudgetEntiteAdministratifItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BudgetEntiteAdministratifItemFacade getFacade() {
        return ejbFacade;
    }

    public BudgetEntiteAdministratifItem prepareCreate() {
        selected = new BudgetEntiteAdministratifItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BudgetEntiteAdministratifItem> getItems() {
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

    public BudgetEntiteAdministratifItem getBudgetEntiteAdministratifItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<BudgetEntiteAdministratifItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BudgetEntiteAdministratifItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = BudgetEntiteAdministratifItem.class)
    public static class BudgetEntiteAdministratifItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BudgetEntiteAdministratifItemController controller = (BudgetEntiteAdministratifItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "budgetEntiteAdministratifItemController");
            return controller.getBudgetEntiteAdministratifItem(getKey(value));
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
            if (object instanceof BudgetEntiteAdministratifItem) {
                BudgetEntiteAdministratifItem o = (BudgetEntiteAdministratifItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BudgetEntiteAdministratifItem.class.getName()});
                return null;
            }
        }

    }

}
