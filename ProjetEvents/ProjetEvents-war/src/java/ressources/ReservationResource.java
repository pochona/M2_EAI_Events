/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ressources;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import messages.Projet;
import singleton.ProjetSingleton;

/**
 * REST Web Service
 *
 * @author Basti
 */
@Path("ws")
public class ReservationResource {
    
    
    ProjetSingleton projetSingleton = lookupProjetSingletonBean();
    
    @Context
    private UriInfo context;

    /**
     * POST method for updating or creating an instance of CommandeResource
     * @param content representation for the resource
     */
    @POST
    @Path("reservation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String reservation(@FormParam("nom") String nom) {
        return nom;
    }
    
    
     
    private ProjetSingleton lookupProjetSingletonBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProjetSingleton) c.lookup("java:global/ProjetEvents/ProjetEvents-ejb/ProjetSingleton!webserver.ProjetSingleton");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
