package fr.craftinglabs.apps.yowie.httpapi.routing;

import java.time.LocalDate;
import org.json.JSONObject;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;

public class OpérationRoute {

    private OpérationRoute() {}


    public static String getOpérationByIdAsJSON(OpérationId operationId, OpérationService service) {
        return OpérationToJSON.parse(service.get(operationId));
    }

    public static String createOpération(String JSONparams, OpérationService service) {
        CreateOpérationParameters params = new CreateOpérationParameters(JSONparams);
        Opération opération = service.create(params.date(), params.montant(), params.libellé());

        return OpérationToJSON.parse(opération);
    }
}

class CreateOpérationParameters {
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
