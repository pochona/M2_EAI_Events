/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Projet;


/**
 *
 * @author Amaury_PC
 */
@Singleton
@LocalBean
public class ProjetSingleton {

    private final ArrayList<Projet> projets = new ArrayList<>();
    
    public Projet creerProjet(String nom) {
        Projet p = new Projet();
        projets.add(p);
        return p;
    }
    
    // setter
    
    // getter
}
