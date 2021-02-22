/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.CrudDAO;
import dao.CrudDAOImpl;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Destiny;
import models.User;
import org.hibernate.Session;

/**
 *
 * @author Vladimir Aca
 */
@Path("/destiny")
public class DestinyResourceService {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response createDestiny() {
        
        Session session = utils.Util.getCurrentSession();
        CrudDAO operations = new CrudDAOImpl();
        operations.beginTransaction(session);
        User user = operations.getAnObjectNoTran(1, User.class, session);
        Destiny newDestiny = new Destiny(user, "New destiny " + new Date().toString());
        String error = operations.addObjectNoTran(newDestiny, session);
        operations.commitTransaction();
        if (error.isEmpty()) {
            return Response.status(Response.Status.OK).entity(newDestiny.getId()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }
}
