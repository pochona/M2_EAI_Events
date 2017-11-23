/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Amaury_PC
 */
public class AppJMS {
    
    private GestionProjet gestionProjet;
    private GestionRestauration gestionRestauration;
    private SimulationWebService simuWebService;
    
    // Construction d'AppJMS : médiateur des class JMS
    public AppJMS(){
        this.gestionProjet = new GestionProjet(this);
        this.gestionRestauration = new GestionRestauration(this);
    }
    
    public static void main(String[] args){
        new AppJMS();
    }
}
