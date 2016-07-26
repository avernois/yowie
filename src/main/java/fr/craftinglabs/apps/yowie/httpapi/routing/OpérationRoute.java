package fr.craftinglabs.apps.yowie.httpapi.routing;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.*;
import org.json.JSONObject;

import java.time.LocalDate;

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

    public static String addVentilationToOpération(OpérationId opérationId, String JSONParams, OpérationService service) {
        VentilationParameters params = new VentilationParameters(JSONParams);
        Ventilation ventilation = service.addVentilation(opérationId, params.montant(), params.catégorie());

        return VentilationToJSON.parse(ventilation);
    }
}

class VentilationParameters {

    private final int montant;
    private final String catégorie;

    public VentilationParameters(String JSONParams) {
        JSONObject json = new JSONObject(JSONParams);
        this.montant = json.getInt("montant");
        this.catégorie = json.getString("catégorie");
    }

    public int montant() {
        return montant;
    }
    public String catégorie() {
        return catégorie;
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
