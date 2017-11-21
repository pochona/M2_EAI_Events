package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.naming.NamingException;
import messages.Nommage;

/**
 *
 * @author elavinal
 */
public class GestionProjet extends ClientJMS {

    private MessageConsumer mc1;
    private MessageConsumer mc2;
    private MessageProducer mp;

    public GestionProjet() {

    }


    private void setProducerConsumer() {

        try {
            
            // recuperation des destinations
            Destination topicProjet = (Destination) namingContext.lookup(Nommage.TOPIC_PROJET);
            Destination topicDemande = (Destination) namingContext.lookup(Nommage.TOPIC_DEMANDE);
            System.out.println("Destination lookup done.");

            // creation des consommateurs et du producteur
            mc1 = session.createConsumer(topicDemande);
            
            mp = session.createProducer(topicProjet);

            // TODO. Il faudrait un Listener par consommateur.
            // Je vais au plus rapide là...
            ProjetListener fl = new ProjetListener(session, mp);
            mc1.setMessageListener(fl);
            //mc2.setMessageListener(fl);

        } catch (JMSException | NamingException ex) {
            Logger.getLogger(GestionProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        GestionProjet monServiceRestauration = new GestionProjet();
        monServiceRestauration.initJMS();
        monServiceRestauration.setProducerConsumer();
        monServiceRestauration.startJMS();
        System.out.println("*** Service de Projet démarré. ***");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Appuyez sur 'Q' pour quitter.");
        } while (!br.readLine().equalsIgnoreCase("Q"));
        monServiceRestauration.closeJMS();
    }

}
