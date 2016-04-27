package controller;

import bean.BudgetFaculteItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.BudgetFaculteItemFacade;

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


@Named("budgetFaculteItemController")
@SessionScoped
public class BudgetFaculteItemController implements Serializable {


    @EJB private service.BudgetFaculteItemFacade ejbFacade;
    private List<BudgetFaculteItem> items = null;
    private BudgetFaculteItem selected;

    public BudgetFaculteItemController() {
    }

    public BudgetFaculteItem getSelected() {
        return selected;
    }

    public void setSelected(BudgetFaculteItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BudgetFaculteItemFacade getFacade() {
        return ejbFacade;
    }

    public BudgetFaculteItem prepareCreate() {
        selected = new BudgetFaculteItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BudgetFaculteItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BudgetFaculteItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BudgetFaculteItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<BudgetFaculteItem> getItems() {
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

    public BudgetFaculteItem getBudgetFaculteItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<BudgetFaculteItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<BudgetFaculteItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=BudgetFaculteItem.class)
    public static class BudgetFaculteItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BudgetFaculteItemController controller = (BudgetFaculteItemController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "budgetFaculteItemController");
            return controller.getBudgetFaculteItem(getKey(value));
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
            if (object instanceof BudgetFaculteItem) {
                BudgetFaculteItem o = (BudgetFaculteItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), BudgetFaculteItem.class.getName()});
                return null;
            }
        }

    }

}
