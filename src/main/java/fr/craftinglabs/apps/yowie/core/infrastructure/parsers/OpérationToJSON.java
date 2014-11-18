package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;

public class OpérationToJSON {

    public static String parse(Opération opération) {
        return "{"
                + "\"date\":\"" + opération.date() + "\","
                + "\"id\":"+ opération.id() + ","
                + "\"libellé\":\"" + opération.libellé() + "\","
                + "\"montant\":" + opération.montant() + "}";
    }

}
