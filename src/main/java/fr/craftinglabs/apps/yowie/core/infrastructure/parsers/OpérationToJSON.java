package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;

public class OpérationToJSON {

    public static String parse(Opération opération) {

        String opérationJSON ="{"
                + "\"date\":\"" + opération.date() + "\","
                + "\"id\":"+ opération.id() + ","
                + "\"libellé\":\"" + opération.libellé() + "\","
                + "\"montant\":" + opération.montant() ;

        String ventilationJSON = "";
        boolean first = true;
        for(Ventilation ventilation: opération.ventilations()) {
            if(first) {
                first = false;
            } else {
                ventilationJSON += ",";
            }

            ventilationJSON += "{" +
                    "\"id\":" + ventilation.id() + ","
                    + "\"montant\":" + ventilation.montant() + ","
                    + "\"catégorie\":\"" + ventilation.catégorie() + "\""
                    + "}";
        }

        if(!ventilationJSON.equals("")) {
            opérationJSON += ",\"ventilation\":[" + ventilationJSON + "]";
        }

        opérationJSON += "}";

        return opérationJSON;
    }

}
