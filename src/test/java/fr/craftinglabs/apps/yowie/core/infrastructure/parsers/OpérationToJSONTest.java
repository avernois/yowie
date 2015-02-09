package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.Opération;

public class OpérationToJSONTest {

    @Test public void 
    should_return_JSON_representation_of_an_opération() {
        Opération opération = new Opération(1, LocalDate.parse("2014-11-17"), 1200, "libellé");
        
        assertThat(OpérationToJSON.parse(opération), is("{\"date\":\"2014-11-17\",\"id\":1,\"libellé\":\"libellé\",\"montant\":1200}"));
    }
}
