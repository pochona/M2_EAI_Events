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
public class GestionRestauration extends ClientJMS {

    private MessageConsumer mc1;
    private MessageConsumer mc2;
    private MessageProducer mp;

    public GestionRestauration() {

    }


    private void setProducerConsumer() {

        try {
            
            // recuperation des destinations
            Destination topicProjet = (Destination) namingContext.lookup(Nommage.TOPIC_PROJET);
            System.out.println("Destination lookup done.");

            // creation des consommateurs et du producteur
            //mc1 = session.createConsumer(cmdsEmises);
            
            mp = session.createProducer(topicProjet);

            // TODO. Il faudrait un Listener par consommateur.
            // Je vais au plus rapide là...
            RestaurationListener fl = new RestaurationListener(session, mp);
            //mc1.setMessageListener(fl);
            //mc2.setMessageListener(fl);

        } catch (JMSException | NamingException ex) {
            Logger.getLogger(GestionRestauration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        GestionRestauration monServiceRestauration = new GestionRestauration();
        monServiceRestauration.initJMS();
        monServiceRestauration.setProducerConsumer();
        monServiceRestauration.startJMS();
        System.out.println("*** Service de facturation démarré. ***");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Appuyez sur 'Q' pour quitter.");
        } while (!br.readLine().equalsIgnoreCase("Q"));
        monServiceRestauration.closeJMS();
    }

}
