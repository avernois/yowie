package fr.craftinglabs.apps.yowie.httpapi.routing;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;

@Path("/opérations")
public class OpérationResource {

    @Inject OpérationService service;
    
    @GET @Path("/{operationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOpérationByIdAsJSON(@PathParam("operationId") String operationId) {
        return OpérationToJSON.parse(service.get(OpérationId.valueOf(operationId)));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createOpération(String JSONparams) {
        CreateOpérationParameters params = new CreateOpérationParameters(JSONparams);
        
        Opération opération = service.create(params.date(), params.montant(), params.libellé());
        
        return OpérationToJSON.parse(opération);
    }
    
    private class CreateOpérationParameters {
        LocalDate date;
        int montant;
        String libellé;
        
        public CreateOpérationParameters(String JSONparams) {
            
            JSONObject json = new JSONObject(JSONparams);
            this.date = LocalDate.parse(json.getString("date"));
            this.montant = json.getInt("montant");
            this.libellé = json.getString("libellé");
        }
        
        public LocalDate date() {
            return date;
        }
        
        public int montant() {
            return montant;
        }
        
        public String libellé() {
            return libellé;
        }
    }
}
