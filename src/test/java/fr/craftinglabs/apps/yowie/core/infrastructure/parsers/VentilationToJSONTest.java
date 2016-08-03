package fr.craftinglabs.apps.yowie.core.infrastructure.parsers;

import fr.craftinglabs.apps.yowie.core.model.Catégorie;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;
import fr.craftinglabs.apps.yowie.core.model.VentilationId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class VentilationToJSONTest {
    @Test public void
    should_return_a_JSON_representation_of_a_ventilation() {
        Ventilation ventilation = new Ventilation(VentilationId.valueOf(4), 600, Catégorie.ACHATS);

        String json = VentilationToJSON.parse(ventilation);

        assertThat(json, is("{\"id\":4,\"montant\":600,\"catégorie\":\"ACHATS\"}"));
    }
}