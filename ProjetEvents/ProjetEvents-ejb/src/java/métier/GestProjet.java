/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métier;

import javax.ejb.Stateless;
import messages.Projet;

/**
 *
 * @author Caro
 */
@Stateless
public class GestProjet implements GestProjetLocal {

    @Override
    public String demanderPrestation(Projet projet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String annulerPrestation(Projet projet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
