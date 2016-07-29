package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;
import fr.craftinglabs.apps.yowie.core.model.VentilationId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;

public class JsonToOpération {

    public static Opération parse(String jsonOpération) {
        Opération opération;
        JSONObject json = new JSONObject(jsonOpération);
        int opérationId = json.getInt("id");
        LocalDate date = LocalDate.parse(json.getString("date"));
        int montant = json.getInt("montant");
        String libellé = json.getString("libellé");
        opération = new Opération(OpérationId.valueOf(opérationId), date, montant, libellé);

        if (json.has("ventilation")) {
            JSONArray ventilations = json.getJSONArray("ventilation");
            for (int i = 0; i < ventilations.length(); i++) {
                opération.addVentilation(jsonToVentilation(ventilations.getJSONObject(i)));
            }
        }

        return opération;
    }

    private static Ventilation jsonToVentilation(JSONObject ventilationJSON) {

        int ventilationMontant = ventilationJSON.getInt("montant");
        VentilationId ventilationId = VentilationId.valueOf(ventilationJSON.getInt("id"));
        String ventilationCatégorie = ventilationJSON.getString("catégorie");

        return new Ventilation(ventilationId, ventilationMontant, ventilationCatégorie);
    }
}

