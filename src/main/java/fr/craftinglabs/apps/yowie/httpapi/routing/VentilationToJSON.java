package fr.craftinglabs.apps.yowie.httpapi.routing;

import fr.craftinglabs.apps.yowie.core.model.Ventilation;

public class VentilationToJSON {
    public static String parse(Ventilation ventilation) {
        return ventilation.id().toString();
    }
}
