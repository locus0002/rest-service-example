/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import dao.PathDAOImpl;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.RoutePath;
import org.hibernate.Session;

/**
 *
 * @author vlac sauce
 */
@Path("/path")
public class PathResourceService {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/position/{type}")
    public Response trackPosition(@PathParam("type") Integer type, List<RoutePath> newPositions) {
        
        if (newPositions != null && newPositions.size() > 0) {
            if (type != null) {
                CompletableFuture.runAsync(() -> {
                    try {
                        this.massiveUpdate(utils.Constants.TYPE_ROUTE.get(type), newPositions);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Missing parameters").build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Missing parameters").build();
        }
    }
    
    private String massiveUpdate(String type, List<RoutePath> newPositions) {
        Session session = utils.Util.getCurrentSession();
        PathDAOImpl operations = new PathDAOImpl();
        return operations.massivePositions(newPositions, type, session);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{type}/{destinyid}")
    public Response getPath(@PathParam("type") String type, @PathParam("destinyid") Long id){
        
        PathDAOImpl operationsDAO = new PathDAOImpl();
        Session hibernateSession = utils.Util.getCurrentSession();
        List<RoutePath> trackPath = operationsDAO.getPathById(id, type.equals("automatic"), hibernateSession);
        GenericEntity entity = new GenericEntity<List<RoutePath>>(trackPath){};
        if (trackPath != null) {
            return Response.status(Response.Status.OK).entity(entity).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
    }
}
