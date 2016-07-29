package fr.craftinglabs.apps.yowie.httpapi.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;
import fr.craftinglabs.apps.yowie.core.model.VentilationId;
import fr.craftinglabs.apps.yowie.httpapi.infrastructure.parsers.OpérationToJSON;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OpérationToJSONTest {

    @Test public void 
    should_return_JSON_representation_of_an_opération() {
        Opération opération = new Opération(OpérationId.valueOf(1), LocalDate.parse("2014-11-17"), 1200, "libellé");
        
        assertThat(OpérationToJSON.parse(opération), is("{\"date\":\"2014-11-17\",\"id\":1,\"libellé\":\"libellé\",\"montant\":1200}"));
    }
    
    @Test public void 
    should_return_JSON_representation_of_an_opération_with_ventilation() {
        Opération opération = new Opération(OpérationId.valueOf(1), LocalDate.parse("2014-11-17"), 1200, "libellé");
        opération.addVentilation(new Ventilation(VentilationId.valueOf(1), 600, "une catégorie"));
        opération.addVentilation(new Ventilation(VentilationId.valueOf(2), 600, "une autre catégorie"));

        assertThat(OpérationToJSON.parse(opération), is("{\"date\":\"2014-11-17\",\"id\":1,\"libellé\":\"libellé\",\"montant\":1200," +
                "\"ventilation\":[{\"id\":1,\"montant\":600,\"catégorie\":\"une catégorie\"}," +
                                 "{\"id\":2,\"montant\":600,\"catégorie\":\"une autre catégorie\"}]}"));
    }
}
