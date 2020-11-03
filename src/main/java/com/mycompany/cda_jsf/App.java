/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cda_jsf;

import com.mycompany.cda_jsf.entities.Themes;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alliese
 */
@WebServlet(name = "App", urlPatterns = {"/app"})
public class App extends HttpServlet {

    @PersistenceContext(unitName = "my_persistence_unit_jta")
          EntityManager em2;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("EM 2===> " + em2);
        
        
        //on instancie la fabrique avec la methode createEntityManagerFactory qui attend l'argument my_persistence_unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        
        //instanciation d'un EntityManager
        EntityManager em = emf.createEntityManager();
        
        
        
        try{
            //On démarre nous même les transactions
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            
            System.out.println("1--ALL------------------------------------------");
            
            //liste thème depuis une NamedQuery
            List<Themes> listeThemes;           
            //Requet JPQL
            Query allThemesQuery;
            
            //Avec un NAMEDQUERY
           // allThemesQuery = em.createNamedQuery("allThemes");
          //  listeThemes = allThemesQuery.getResultList();
            
            //One shot
          // listeThemes = em.createNamedQuery("allThemes").getResultList();
            
            //Avec UNE QUERY
          //  allThemesQuery = em.createQuery("SELECT t FROM Themes t");
         //   listeThemes = allThemesQuery.getResultList();
            
            
            // *** AVEC UNE CRITERIAQURY
            Class<Themes> entityClass = Themes.class;
 
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            
            // !!!!!!! CETTE REQUETE SERAIT TOUTE PUISSANTE AVEC UN TYPE GENERIQUE
            //cq.select(cq.from(Class.forName("com.philou.cda_jsf.entities.Themes")));
            cq.select(cq.from(entityClass));
            
            listeThemes = em.createQuery(cq).getResultList();

            listeThemes.forEach( theme -> {System.out.println("Theme ==> " + theme);}   );
            
            // 0 invocation de la BD
            
            
            System.out.println("2---FIND--BY--ID----------------------------------");
            //FIND BY ID
            Themes themeToBeFound = new Themes();
            
         //   Query themeToBeFoundQuery;
          //  Query themeToBeFoundQuery = em.createNamedQuery("aThemeById");
          //  themeToBeFoundQuery.setParameter("idTheme", 57);
          //  themeToBeFound = (Themes) themeToBeFoundQuery.getSingleResult();
          //  System.out.println("Theme to Be Found ==> " + themeToBeFound);
            
            //One shot
          //  themeToBeFound = (Themes) em.createNamedQuery("aThemeById").setParameter("idTheme", 26).getSingleResult();
            
           //Le + direct 
          themeToBeFound = em.find(entityClass, 26);
          System.out.println("Theme to Be Found ==> " + themeToBeFound);
          
          
          
  
          System.out.println("3---CREATE--------------------------------------");
          
          Themes brandNewTheme = new Themes();
          brandNewTheme.setLibelle("BRAND NEW THEMA");
          
          //On ajoute au DEPOT
          em.persist(brandNewTheme);
          
          allThemesQuery = em.createQuery("SELECT t FROM Themes t");
          listeThemes = allThemesQuery.getResultList();
         
          listeThemes.forEach( theme -> {System.out.println("Theme ==> " + theme);}   );
          
          //DES milliard d'autres themes
          //........
          
          transaction.commit();
          //Transaction over !
          
          //On demarre nous meme les transactions
        EntityTransaction transaction2 = em.getTransaction();
        transaction2.begin();
        
        System.out.println("4---Modif--------------------------------------");
        
          brandNewTheme.setLibelle("MOOOOOOOOOOOD");
          
          Themes tt = em.find(entityClass, 55);
          tt.setLibelle("-------------------");
          
          em.merge(brandNewTheme);//Pour la modif si il existe sinon il le creer
    
          //transaction.commit();//Inserer dans la BD
          
          
          System.out.println("5---Delete--------------------------------------");
          
          
          em.remove(em.merge(themeToBeFound));
          
          transaction.commit();
          
          
          
          
          
          
        }catch(Exception ex){
            System.err.println(ex);
        }finally{
            if(em != null){
                em.close();
            }
        }  
        request.getRequestDispatcher("index.xhtml").forward(request, response);
        
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
