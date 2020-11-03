
package com.mycompany.cda_jsf.ejbs;

import java.util.List;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

//Genericité implentation majeur de l'inversion de controle


 // Genericité la classe parente est responsable du compotement des enfants mais surtout elle connait l'enfant

public abstract class Abstractfacade <T>{
    
   //entityClass = un objet de type classe qui permet d'instancier un objet qui reference une classe 
    //un objet capable de pointer sur une classe
    private Class<T> entityClass;
    
    //Constructeur
    public Abstractfacade(Class<T> entityClass){
        
        //On aggrege l'objet representant une classe
        this.entityClass = entityClass;
    
}
    //Méthode abstraite pour recuperer l'entityManager(gerer par la classe fille)
    //Les enfants doivent donner une def pour cette méthode
    abstract protected EntityManager getEntityManager();
    
    public List<T> liste(){
        
        // Avec un CriteriaQuery
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        
        //methode 1
      //  Query query = getEntityManager().createQuery(cq);
      // return getResultList();
        
      //methode2
        return getEntityManager().createQuery(cq).getResultList();
        
    }
    
    
    
    public void create(T entityClass){
        getEntityManager().persist(entityClass);
        
    }
    
    public T find(Object id){
        
        return getEntityManager().find(entityClass, id);
    }
    
    
    public void update(T entityClass){
        getEntityManager().merge(entityClass);
    }
    
    public void destroy(T entityClass){
        
        try{
            getEntityManager().remove(getEntityManager().merge(entityClass));
        }catch(EJBException e){
            System.out.println("EJBException ===>"+e.getMessage());
        }
         
        
    }
    
}
