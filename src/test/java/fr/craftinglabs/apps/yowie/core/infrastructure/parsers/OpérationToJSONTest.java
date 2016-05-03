package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
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
}
