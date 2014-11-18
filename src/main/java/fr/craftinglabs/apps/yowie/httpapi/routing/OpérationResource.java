package fr.craftinglabs.apps.yowie.httpapi.routing;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;

@Path("/opérations")
public class OpérationResource {

    @Inject OpérationService service;
    
    @GET @Path("/{operationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOpérationByIdAsJSON(@PathParam("operationId") String operationId) {
        return OpérationToJSON.parse(service.get(Integer.parseInt(operationId)));
    }
}
