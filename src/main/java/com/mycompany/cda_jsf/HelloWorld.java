package com.mycompany.cda_jsf;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alliese
 */
@Named(value = "yo")//coté jsf je peux acceder a cet objet sans l'instancié nous meme
@RequestScoped
public class HelloWorld implements Serializable{
    
    private String message;

    
    public HelloWorld() {
        System.out.println("Constructeur HelloWorld");
        message = "Hey !";
    }
    
    public String goTo(){
        return "page1";
    }
    
      public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
