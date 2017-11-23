/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ressources;

import javax.ws.rs.core.*;
import javax.ws.rs.*;


/**
 * REST Web Service
 *
 * @author Babas
 */
@Path("reservation")
public class ReservationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReservationResource
     */
    public ReservationResource() {
    }

    /**
     * Retrieves representation of an instance of ressources.ReservationResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

   /**
     * POST method for updating or creating an instance of CommandeResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(@FormParam("value") String value) {
        return value;
    }
}
