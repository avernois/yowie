package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;

import java.util.StringJoiner;

public class OpérationToJSON {

    public static String parse(Opération opération) {

        String opérationJSON ="{"
                + "\"date\":\"" + opération.date() + "\","
                + "\"id\":"+ opération.id() + ","
                + "\"libellé\":\"" + opération.libellé() + "\","
                + "\"montant\":" + opération.montant() ;

        opérationJSON += ventilationsJSONList(opération);

        opérationJSON += "}";

        return opérationJSON;
    }

    private static String ventilationsJSONList(Opération opération) {
        StringJoiner joiner = new StringJoiner(",");

        for(Ventilation ventilation: opération.ventilations()) {
            joiner.add(VentilationToJSON.parse(ventilation));
        }

        if(joiner.length() > 0) {
            return ",\"ventilation\":[" + joiner.toString() + "]";
        }
        return "";
    }
}
