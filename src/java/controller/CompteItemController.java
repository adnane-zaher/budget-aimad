package controller;

import bean.BudgetEntiteAdministratif;
import bean.BudgetEntiteAdministratifItem;
import bean.Compte;
import bean.CompteItem;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Message;
import controller.util.MessageManager;
import controller.util.SessionUtil;
import service.CompteItemFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import service.BudgetEntiteAdministratifItemFacade;
import service.CompteFacade;

@Named("compteItemController")
@SessionScoped
public class CompteItemController implements Serializable {

    @EJB
    private service.CompteItemFacade ejbFacade;
    @EJB
    private BudgetEntiteAdministratifFacade budgetEntiteAdministratifFacade;
    @EJB
    private CompteFacade compteFacade;
    @EJB
    private BudgetEntiteAdministratifItemFacade budgetEntiteAdministratifItemFacade;

    private List<CompteItem> items;
    private CompteItem selected;
    private String annee;
    private Double budget;
    private Double budgetEngage;
    private Double budgetPaye;
    private List<String> annees = null;
    private Double montantMax = 0.0;
    private Message message;
    private List<CompteItem> compteItems;
    //private List<BudgetEntiteAdministratifItem> budgetEntiteAdministratifItems;
    private BudgetEntiteAdministratif budgetEntiteAdministratif;
    //private long idMax;

    public CompteItemController() {
    }

    public void findBudgetByAnneAndEntity() {
        budgetEntiteAdministratif = budgetEntiteAdministratifFacade.findByAnneAndEntity(annee, SessionUtil.getConnectedUser().getEntiteAdministratif());
        budget = budgetEntiteAdministratif.getMontantAffecte();
        budgetEngage = budgetEntiteAdministratif.getMontantEngage();
        budgetPaye = budgetEntiteAdministratif.getMontantPaye();
        items = ejbFacade.findAll(budgetEntiteAdministratif);
        System.out.println(budget);
    }

    public void findCompteMontantMax() {
        Compte compte = compteFacade.find(selected.getCompte().getId());
        montantMax = compte.getMontantMax();
    }

    private int validateViewCompteItem() {

        if (selected.getMontantAffecte() > budget) {
            message = MessageManager.createErrorMessage(-1, "Attention affecte>Budget Total");
            MessageManager.showMessage(message);
            return -1;
        }

        if (selected.getMontantEngage() > selected.getMontantAffecte()) {
            message = MessageManager.createErrorMessage(-2, "Attention engage>affecte ");
            MessageManager.showMessage(message);
            return -1;
        }
        if (selected.getMontantEngage() < selected.getMontantPaye()) {
            message = MessageManager.createErrorMessage(-3, "Attention paye>engage ");
            MessageManager.showMessage(message);
            return -1;
        }
        if (selected.getMontantAffecte() > montantMax) {
            message = MessageManager.createErrorMessage(-4, "Attention Affecte>Montant Max du Compte ");
            MessageManager.showMessage(message);
            return -1;
        }
        if (isCompteSelected(clone(selected).getCompte(), compteItems) == 1 || isCompteSelected(clone(selected).getCompte(), items) == 1) {
            message = MessageManager.createErrorMessage(-5, "le Compte est deja selectionner ");
            MessageManager.showMessage(message);
            return -1;
        }
        if (budgetEngage > budget) {
            message = MessageManager.createErrorMessage(-6, "Attetion le BudgetEntiteAdmin est dépassé");
            MessageManager.showMessage(message);
            return -1;
        }
        return 1;
    }

    public void addCompteItem() {
        findCompteMontantMax();
        System.out.println(montantMax);
        int res = validateViewCompteItem();
        if (res == 1) {
            //budget = budget - clone(selected).getMontantAffecte();
            budgetEngage = budgetEngage + clone(selected).getMontantAffecte();
            budgetPaye = budgetPaye + clone(selected).getMontantPaye();
            compteItems.add(clone(selected));
        }

    }

    public void deleteCompteItem() {
        budgetEngage = budgetEngage - clone(selected).getMontantAffecte();
        budgetPaye = budgetPaye - clone(selected).getMontantPaye();
        compteItems.remove(selected);

    }

    private BudgetEntiteAdministratifItem addBudgetEntityAdminItem(CompteItem compteItem) {
        BudgetEntiteAdministratifItem budgetEntiteAdministratifItem = new BudgetEntiteAdministratifItem();
        budgetEntiteAdministratifItem.setCompteItem(compteItem);
        budgetEntiteAdministratifItem.setBudgetEntiteAdministratif(budgetEntiteAdministratif);
        return budgetEntiteAdministratifItem;
    }

    private void createCompteItems() {
        for (CompteItem compteItem : compteItems) {
            ejbFacade.create(compteItem);
            System.out.println("ha lcompte item tcrea ");
            budgetEntiteAdministratifItemFacade.create(addBudgetEntityAdminItem(compteItem));

        }
    }

//    private void createBudgetEntityAdminItems(){
//        for (BudgetEntiteAdministratifItem budgetEntiteAdministratifItem : budgetEntiteAdministratifItems) {
//            budgetEntiteAdministratifItemFacade.create(budgetEntiteAdministratifItem);
//        }
//    }
    private int isCompteSelected(Compte compte, List<CompteItem> compteItems) {
        for (CompteItem compteItem : compteItems) {
            if (compteItem.getCompte().getId() == compte.getId()) {
                return 1;
            }
        }
        return -1;
    }

    public void createAllCompteItemsAndBudgetEntitysItems() {
        message = MessageManager.createInfoMessage(1, "Dakchi howa hadak");
        MessageManager.showMessage(message);
        createCompteItems();
        //createBudgetEntityAdminItems();

        compteItems.clear();
        items = ejbFacade.findAll();
        //budgetEntiteAdministratifItems.clear();
    }

    public CompteItem getSelected() {
        if (selected == null) {
            selected = new CompteItem();
        }
        return selected;
    }

    public void setSelected(CompteItem selected) {
        this.selected = selected;
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

    public List<CompteItem> getCompteItems() {
        if (compteItems == null) {
            compteItems = new ArrayList<>();
        }
        return compteItems;
    }

    public void setCompteItems(List<CompteItem> compteItems) {
        this.compteItems = compteItems;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CompteItemFacade getFacade() {
        return ejbFacade;
    }

    public CompteItem prepareCreate() {
        selected = new CompteItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void createItem() {

    }

    public void create() {
        int res = validateViewCompteItem();
        if (res == 1) {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CompteItemCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CompteItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CompteItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CompteItem> getItems() {
        if (items == null) {
//            items = getFacade().findAll();
            items = new ArrayList();
        }
        return items;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Double getMontantMax() {
        return montantMax;
    }

    public void setMontantMax(Double montantMax) {
        this.montantMax = montantMax;
    }

    public Double getBudget() {
        if (budget == null) {
            budget = new Double(0);
        }
        return budget;
    }

    public Double getBudgetEngage() {
        if (budgetEngage == null) {
            budgetEngage = new Double(0);
        }
        return budgetEngage;
    }

    public void setBudgetEngage(Double budgetEngage) {
        this.budgetEngage = budgetEngage;
    }

    public Double getBudgetPaye() {
        if (budgetPaye == null) {
            budgetPaye = new Double(0);
        }
        return budgetPaye;
    }

    public void setBudgetPaye(Double budgetPaye) {
        this.budgetPaye = budgetPaye;
    }

    public List<String> getAnnees() {
        if (annees == null) {
            annees = new ArrayList<>();
            annees.add("2015");
            annees.add("2016");
            annees.add("2017");
            annees.add("2018");
            annees.add("2019");
        }
        System.out.println("ha les annees " + annees);
        return annees;
    }

    public CompteItem clone(CompteItem compteItem) {
        CompteItem clone = new CompteItem();
        clone.setId(compteItem.getId());
        clone.setDescription(compteItem.getDescription());
        clone.setCompte(compteItem.getCompte());
        clone.setMontantAffecte(compteItem.getMontantAffecte());
        clone.setMontantEngage(compteItem.getMontantEngage());
        clone.setMontantPaye(compteItem.getMontantPaye());
        return clone;
    }

    public void setAnnees(List<String> annees) {
        this.annees = annees;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
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

    public CompteItem getCompteItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CompteItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CompteItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CompteItem.class)
    public static class CompteItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompteItemController controller = (CompteItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compteItemController");
            return controller.getCompteItem(getKey(value));
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
            if (object instanceof CompteItem) {
                CompteItem o = (CompteItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CompteItem.class.getName()});
                return null;
            }
        }

    }

}
