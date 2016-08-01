package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Ventilation;

public class VentilationToJSON {
    public static String parse(Ventilation ventilation) {
        return "{" +
                "\"id\":" + ventilation.id() + ","
                + "\"montant\":" + ventilation.montant() + ","
                + "\"catégorie\":\"" + ventilation.catégorie() + "\""
                + "}";
    }
}
