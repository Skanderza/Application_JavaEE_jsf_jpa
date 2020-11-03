
package com.mycompany.cda_jsf.ejbs;

import com.mycompany.cda_jsf.entities.Themes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alliese
 */
@Stateless
public class ThemesFacade extends Abstractfacade<Themes> {
    
    //Injection d'un EntityManager
    @PersistenceContext(unitName = "my_persistence_unit_jta")
    EntityManager em;
    
    //Constructeur
    public ThemesFacade(){
        super(Themes.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
