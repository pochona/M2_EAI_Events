/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import messages.Salle;


/**
 *
 * @author Amaury_PC
 */
@Singleton
@LocalBean
public class SalleSingleton {

    private final ArrayList<Salle> salles = new ArrayList<>();
    
    public Salle creerProjet(String nom) {
        Salle s = new Salle();
        salles.add(s);
        return s;
    }
    
    // setter
    
    // getter
}
