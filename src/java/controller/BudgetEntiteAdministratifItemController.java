package controller;

import bean.BudgetEntiteAdministratif;
import bean.BudgetEntiteAdministratifItem;
import bean.EntiteAdministratif;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Message;
import controller.util.MessageManager;
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
import service.BudgetEntiteAdministratifFacade;
import service.CompteItemFacade;

@Named("budgetEntiteAdministratifItemController")
@SessionScoped
public class BudgetEntiteAdministratifItemController implements Serializable {

    @EJB
    private service.BudgetEntiteAdministratifItemFacade ejbFacade;
    private List<BudgetEntiteAdministratifItem> items = null;
    private BudgetEntiteAdministratifItem selected;

    private EntiteAdministratif entiteAdministratif;
    private String annee;
    private int valider;

    private BudgetEntiteAdministratif budgetEntiteAdministratif;
    private Double montantAffecterBudget;
    @EJB
    private BudgetEntiteAdministratifFacade budgetEntiteAdministratifFacade;
    @EJB
    private BudgetEntiteAdministratifItemFacade budgetEntiteAdministratifItemFacade;
    @EJB
    private CompteItemFacade compteItemFacade;
    private Message message;
    private int res = 0;

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

    public EntiteAdministratif getEntiteAdministratif() {
        if (entiteAdministratif == null) {
            entiteAdministratif = new EntiteAdministratif();
        }
        return entiteAdministratif;
    }

    public void setEntiteAdministratif(EntiteAdministratif entiteAdministratif) {
        this.entiteAdministratif = entiteAdministratif;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public int getValider() {
        return valider;
    }

    public void setValider(int valider) {
        this.valider = valider;
    }

    public BudgetEntiteAdministratif getBudgetEntiteAdministratif() {
        if (budgetEntiteAdministratif == null) {
            budgetEntiteAdministratif = new BudgetEntiteAdministratif();
        }
        return budgetEntiteAdministratif;
    }

    public void setBudgetEntiteAdministratif(BudgetEntiteAdministratif budgetEntiteAdministratif) {
        this.budgetEntiteAdministratif = budgetEntiteAdministratif;
    }

    public Double getMontantAffecterBudget() {
        if (montantAffecterBudget == null) {
            montantAffecterBudget = new Double(0);
        }
        return montantAffecterBudget;
    }

    public void setMontantAffecterBudget(Double montantAffecterBudget) {
        this.montantAffecterBudget = montantAffecterBudget;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
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

//    public void update() {
//        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifItemUpdated"));
//    }
    public void update() {
        this.res = validateViewCompteItem();
        if (res == 1) {
            compteItemFacade.edit(selected.getCompteItem());

            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BudgetEntiteAdministratifItemUpdated"));

        }

    }

    public void destroy() {
        compteItemFacade.remove(selected.getCompteItem());
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

    ///hamid 
    public void findBudgetByAnneAndEntity() {
        budgetEntiteAdministratif = budgetEntiteAdministratifFacade.findByAnneAndEntity(annee, entiteAdministratif);
        System.out.println(" ha lbudget ----> " + budgetEntiteAdministratif);
        montantAffecterBudget = budgetEntiteAdministratif.getMontantAffecte();
        System.out.println("ha l montant lighayt7et fdik disabled --> " + montantAffecterBudget);
    }

    public void findByCriteres() {
        System.out.println(" sahbna dkhel elmethoda tecontroller ");

        items = ejbFacade.findByCriteres(budgetEntiteAdministratif, valider);
    }

    public void update(BudgetEntiteAdministratifItem budgetEntiteAdministratifItem) {
        selected = budgetEntiteAdministratifItem;
    }

    public int validateViewCompteItem() {

        if (selected.getCompteItem().getMontantAffecte() > montantAffecterBudget) {
            message = MessageManager.createErrorMessage(-1, "Attention affecte>Budget Total");
            MessageManager.showMessage(message);
            return -1;
        }

        if (selected.getCompteItem().getMontantEngage() > selected.getCompteItem().getMontantAffecte()) {
            message = MessageManager.createErrorMessage(-2, "Attention engage>affecte ");
            MessageManager.showMessage(message);
            return -1;
        }
        if (selected.getCompteItem().getMontantEngage() < selected.getCompteItem().getMontantPaye()) {
            message = MessageManager.createErrorMessage(-3, "Attention paye>engage ");
            MessageManager.showMessage(message);
            return -1;
        }
        return 1;
    }

    public void destroy(BudgetEntiteAdministratifItem budgetEntiteAdministratifItem) {
        selected = budgetEntiteAdministratifItem;
        destroy();
    }

    public void validate() {
        Double montantEngageBudgetEntite = 0.0;
        Double montantPayeBudgetEntite = 0.0;
        for (int i = 0; i < items.size(); i++) {
            BudgetEntiteAdministratifItem item = items.get(i);
            montantEngageBudgetEntite += item.getCompteItem().getMontantAffecte();
            montantPayeBudgetEntite += item.getCompteItem().getMontantPaye();
            item.setValider(1);
            ejbFacade.edit(item);

        }
        budgetEntiteAdministratif.setMontantEngage(montantEngageBudgetEntite);
        budgetEntiteAdministratif.setMontantPaye(montantPayeBudgetEntite);

        budgetEntiteAdministratifFacade.edit(budgetEntiteAdministratif);
        this.items.clear();

    }
}
