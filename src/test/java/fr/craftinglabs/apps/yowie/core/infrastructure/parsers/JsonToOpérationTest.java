package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;
import fr.craftinglabs.apps.yowie.core.model.VentilationId;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonToOpérationTest {
    @Test public void 
    should_return_JSON_string_of_an_opération() {
        Opération expected = new Opération(OpérationId.valueOf(1), LocalDate.parse("2016-05-02"), 1200, "exemple");

        Opération actual = JsonToOpération.parse("{\"date\":\"2016-05-02\",\"id\":1,\"libellé\":\"exemple\",\"montant\":1200,\"ventilation\":[]}");

        assertThat(actual, is(expected));
    }
    
    @Test public void 
    should_return_JSON_string_of_an_opération_with_ventilation() {
        Opération expected = new Opération(OpérationId.valueOf(1), LocalDate.parse("2016-05-02"), 1200, "exemple");
        expected.addVentilation(new Ventilation(VentilationId.valueOf(1), 600, "une catégorie"));

        Opération actual = JsonToOpération.parse("{\"date\":\"2016-05-02\",\"id\":1,\"libellé\":\"exemple\",\"montant\":1200,\"ventilation\":[{\"id\":1,\"montant\":600,\"catégorie\":\"une catégorie\"}]}");

        assertThat(actual, is(expected));
    }
}