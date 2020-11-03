/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.mycompany.cda_jsf.ejbs.ThemesFacade;
import com.mycompany.cda_jsf.entities.Themes;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author alliese
 */
@Named(value = "themesController")
@SessionScoped
public class ThemesController implements Serializable{

// ON injicte le bean de session
    @EJB
    private ThemesFacade ejb;
    
    private Themes current;
    
    private DataModel<Themes> items;
    
    
    
    
    public ThemesController() {
        
        System.out.println("ThemesController :: Constructeur");
        items = null;
    }

   public DataModel<Themes> getItems() {
       if(items == null){
           items = new ListDataModel(getEjb().liste());
           
       }
   
        return items;
    }
    
   public String prepareCreate(){
       System.out.println("ThemesController ::Create"+this);
       current = new Themes();
 return "create";
   }
    
   public String create(){
       
       getEjb().create(current);
      //Pour rafraichir
      items = null;
      return "liste";
       
       
   }
   
   
   public String prepareUpdate(){

       current = getItems().getRowData();
       
      
       return "update";
   }
   
   
   public String update(){

       getEjb().update(current);
       
      
       return "liste";
   }
   
   
   public String destroy(){
       current = getItems().getRowData();
       getEjb().destroy(current);
       
       
       items = null;
      return "liste";
   }
   
   
   
  
   
   
  
    /**
     * @return the ejb
     */
    public ThemesFacade getEjb() {
        return ejb;
    }

    /**
     * @return the current
     */
    public Themes getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Themes current) {
        this.current = current;
    }

    /**
     * @return the items
     */
    

    /**
     * @param items the items to set
     */
    public void setItems(DataModel<Themes> items) {
        this.items = items;
    }
    
}
