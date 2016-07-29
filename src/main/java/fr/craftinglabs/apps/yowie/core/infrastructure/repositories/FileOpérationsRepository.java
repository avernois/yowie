package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import fr.craftinglabs.apps.yowie.core.model.*;
import fr.craftinglabs.apps.yowie.httpapi.infrastructure.parsers.OpérationToJSON;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FileOpérationsRepository implements Opérations {
    private Path path;

    public FileOpérationsRepository(Path path) throws IOException {
        this.path = path;
        if(Files.notExists(path)) {
            Files.createDirectories(path);
        }
    }

    @Override
    public Opération get(OpérationId id) {
        try {
            String jsonOp = String.join("", Files.readAllLines(opérationFilePath(id)));
            return jsonToOpération(jsonOp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Opération jsonToOpération(String jsonOp) {
        Opération opération;
        JSONObject json = new JSONObject(jsonOp);
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

    private Ventilation jsonToVentilation(JSONObject ventilationJSON) {

        int ventilationMontant = ventilationJSON.getInt("montant");
        VentilationId ventilationId = VentilationId.valueOf(ventilationJSON.getInt("id"));
        String ventilationCatégorie = ventilationJSON.getString("catégorie");

        return new Ventilation(ventilationId, ventilationMontant, ventilationCatégorie);
    }

    @Override
    public void store(Opération opération) {
        try {
            Files.write(opérationFilePath(opération.id()), OpérationToJSON.parse(opération).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file on the system.", e);
        }
    }

    private Path opérationFilePath(OpérationId id) {
        return Paths.get(path.toString(), id.toString() + ".operation");
    }
}
