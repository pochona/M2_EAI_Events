package app;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.naming.NamingException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import messages.Nommage;
import messages.Projet;

/**
 *
 * @author elavinal
 */
public class GestionRestauration extends ClientJMS {

    private MessageConsumer mc;
    private MessageProducer mp;
    private AppJMS appJms;
    
    private HashMap<Integer, Projet> projetATraiter;
    

    private LivraisonRessource_JerseyClient client;
    

    public GestionRestauration(AppJMS appJms) {
        projetATraiter = new HashMap();
        client = new LivraisonRessource_JerseyClient();
        appJms = appJms;
        boolean theEnd = false;
        
        try {
            this.initJMS();
            this.setProducerConsumer();
            this.startJMS();
            System.out.println("*** Service Restauration démarré. ***");
            
            do {
                Message msg = this.mc.receive();
                System.out.println("----------");
                
                this.processMessage(msg);
            } while (!theEnd);
            this.closeJMS();
        } catch (JMSException ex) {
            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setProducerConsumer() {

        try {

            // recuperation des destinations
            //Destination cmdsTraitees = (Destination) namingContext.lookup(Nommage.TOPIC_CMDS_TRAITEES);
            Destination projetEnCours = (Destination) namingContext.lookup(Nommage.TOPIC_PROJET);
            System.out.println("Destination lookup done.");

            // creation des consommateurs et du producteur
            mc = session.createConsumer(projetEnCours);
            
        } catch (JMSException | NamingException ex) {
            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    private boolean stocksOK(Commande c) {
        // on simplifie, regle du tout ou rien...
        for (LigneCommande lc : c.getLignes()) {
            if (lc.getQuantite() > lc.getStock()) {
                System.out.println("\t stock NOK sur " + c.getNumCommande());
                return false;
            }
        }
        return true;
    }

    private boolean facturationOK(Commande c) {
        return c.getBanqueValide();
    }
*/
    private void processMessage(Message msg) {        
        try {
            Projet projet;
            if (msg instanceof ObjectMessage) {
                System.out.println("object message");
                ObjectMessage om = (ObjectMessage) msg;
                Object obj = om.getObject();
                if (obj instanceof Projet) {
                    System.out.println("projet objet");
                    projet = (Projet) obj;
                    System.out.println(projet.getRefProjet());
                } else {
                    return;
                }
            } else {
                return;
            }
/*
            if (projetATraiter.containsKey(projet.getRefProjet())) {
                // commande deja recue
                System.out.println("--> Commande " + projet.getRefProjet() + " recue (et deja en attente).");
                Commande cmdF;
                Commande cmdS;
                if (msg.getJMSType().equalsIgnoreCase(Nommage.MSG_FACTURATION)) {
                    cmdF = cmd;
                    cmdS = cmdsEnAttente.remove(cmd.getNumCommande());
                } else {
                    cmdS = cmd;
                    cmdF = cmdsEnAttente.remove(cmd.getNumCommande());
                }

                if (facturationOK(cmdF) && stocksOK(cmdS)) {
                    

                    // TODO: Client REST pour activer la livraison
                    System.out.println("\t Commande " + cmd.getNumCommande() + " valide, on active la livraison.");
                    String retour = client.putJson("{\"test\":\"test\"}");
                    System.out.println("Retour livraison "+retour);
                    
                    if (retour.startsWith(retour))
                    // tout valide
                    cmd.setEtat(EtatCommande.EN_COURS_DE_LIVRAISON);
                    ObjectMessage om = session.createObjectMessage(cmd);
                    om.setJMSType(Nommage.MSG_CMD_VALIDE);
                    mp.send(om);
                } else {
                    // non valide, on annule
                    cmd.setEtat(EtatCommande.ANNULEE);
                    ObjectMessage om = session.createObjectMessage(cmd);
                    om.setJMSType(Nommage.MSG_CMD_ANNULEE);
                    mp.send(om);
                    System.out.println("\t Commande " + cmd.getNumCommande() + " non valide, on l'annule.");
                }
            } else {
                // nouvelle commande, on l'ajoute dans le dict
                cmdsEnAttente.put(cmd.getNumCommande(), cmd);
                System.out.println("--> Commande " + cmd.getNumCommande() + " mise en attente.");
            }
            */
        } catch (Exception ex) {
            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class LivraisonRessource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/LivraisonWeb/webresources";

        public LivraisonRessource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("livraison");
        }

        public String putJson(Object requestEntity) throws ClientErrorException {
            return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
        }

        public String getJson() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        }

        public void close() {
            client.close();
        }
    }

}
