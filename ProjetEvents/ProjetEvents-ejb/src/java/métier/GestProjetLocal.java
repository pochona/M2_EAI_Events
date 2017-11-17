/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métier;

import javax.ejb.Local;

/**
 *
 * @author Caro
 */
@Local
public interface GestProjetLocal {
    public String demandePrestation();
}
