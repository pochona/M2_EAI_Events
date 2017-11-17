/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package métier;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author Caro
 */
@LocalBean
@Singleton
public interface GestSalleLocal {
    
    public String réserverSalle();
    
    public String annulerSalle(int numSalle);
}
