/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.NamingException;
import messages.Nommage;
import messages.Projet;

/**
 *
 * @author Amaury_PC
 */
public class ProjetListener implements MessageListener {
    private final MessageProducer mp;
    private final Session session;

    public ProjetListener(Session session, MessageProducer mp) {
        this.session = session;
        this.mp = mp;
        System.out.println("creation");
    }

    @Override
    public void onMessage(Message message) {
        Projet projet = null;
        try {
            Topic source = (Topic) message.getJMSDestination();

            // System.out.println("MSG RECU " + source.getTopicName());
            String topicName = source.getTopicName();

            if (topicName.equalsIgnoreCase(Nommage.TOPIC_DEMANDE)) {
                if (message instanceof ObjectMessage) {
                    ObjectMessage om = (ObjectMessage) message;
                    Object obj = om.getObject();
                    if (obj instanceof Projet) {
                        projet = (Projet) obj;
                        System.out.println("Projet " + projet.getRefProjet() + " reçue");
                        // System.out.println("IBAN : " + iban);
                        // TODO: Client SOAP ou REST pour effectuer la vérif
                        
                        /*
                        // Ici, simu par tirage aléatoire
                        double r = Math.random();
                        if (r > 0.3) {
                            // Vérif OK (~ 70%)
                            cmd.setBanqueValide(true);
                            System.out.println("\t --> Coord. bancaires OK");
                        } else {
                            // Vérif KO (~ 30%)
                            cmd.setBanqueValide(false);
                            System.out.println("\t --> Coord. bancaires NOK");
                        }*/
                        // envoi de la réponse de la banque
                        ObjectMessage msg = session.createObjectMessage();
                        msg.setObject(projet);
                        //msg.setJMSType(Nommage.MSG_PROJET);
                        mp.send(msg);
                    }
                }
            }

        } catch (JMSException ex) {
            Logger.getLogger(ProjetListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/*
    private static boolean verifierCoordonnees(java.lang.String iban) {
        clients_ws.ServiceBanque_Service service = new clients_ws.ServiceBanque_Service();
        clients_ws.ServiceBanque port = service.getServiceBanquePort();
        return port.verifierCoordonnees(iban);
    }
*/
}
