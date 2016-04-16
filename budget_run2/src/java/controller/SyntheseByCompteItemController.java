package controller;

import bean.SyntheseByCompteItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.SyntheseByCompteItemFacade;

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

@Named("syntheseByCompteItemController")
@SessionScoped
public class SyntheseByCompteItemController implements Serializable {

    @EJB
    private service.SyntheseByCompteItemFacade ejbFacade;
    private List<SyntheseByCompteItem> items = null;
    private SyntheseByCompteItem selected;

    public SyntheseByCompteItemController() {
    }

    public SyntheseByCompteItem getSelected() {
        return selected;
    }

    public void setSelected(SyntheseByCompteItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SyntheseByCompteItemFacade getFacade() {
        return ejbFacade;
    }

    public SyntheseByCompteItem prepareCreate() {
        selected = new SyntheseByCompteItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SyntheseByCompteItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SyntheseByCompteItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SyntheseByCompteItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SyntheseByCompteItem> getItems() {
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

    public SyntheseByCompteItem getSyntheseByCompteItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SyntheseByCompteItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SyntheseByCompteItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SyntheseByCompteItem.class)
    public static class SyntheseByCompteItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SyntheseByCompteItemController controller = (SyntheseByCompteItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "syntheseByCompteItemController");
            return controller.getSyntheseByCompteItem(getKey(value));
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
            if (object instanceof SyntheseByCompteItem) {
                SyntheseByCompteItem o = (SyntheseByCompteItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SyntheseByCompteItem.class.getName()});
                return null;
            }
        }

    }

}
