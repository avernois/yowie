package fr.craftinglabs.apps.yowie.httpapi.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Ventilation;
import fr.craftinglabs.apps.yowie.core.model.VentilationId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VentilationToJSONTest {
    @Test public void
    should_return_a_JSON_represenation_of_a_ventilation() {
        Ventilation ventilation = new Ventilation(VentilationId.valueOf(4), 600, "la catégorie");

        String json = VentilationToJSON.parse(ventilation);

        assertThat(json, is("{\"id\":4,\"montant\":600,\"catégorie\":\"la catégorie\"}"));
    }
}