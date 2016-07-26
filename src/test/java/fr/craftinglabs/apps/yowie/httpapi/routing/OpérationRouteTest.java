package fr.craftinglabs.apps.yowie.httpapi.routing;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpérationRouteTest {

    OpérationService service;

    @Before
    public void before() {
        service = mock(OpérationService.class);
    }

    @Test public void 
    should_return_the_JSON_representation_of_an_existing_operation() {
        Opération opération = new Opération(OpérationId.valueOf(2), LocalDate.parse("2014-12-09"), 1200, "un libellé");

        when(service.get(OpérationId.valueOf(2))).thenReturn(opération);

        assertThat(OpérationRoute.getOpérationByIdAsJSON(OpérationId.valueOf("2"), service), is(OpérationToJSON.parse(opération)));
    }
    
    @Test public void 
    should_create_opération() {
        Opération opération = new Opération(LocalDate.parse("2014-11-17"), 1400, "libellé");
        when(service.create(LocalDate.parse("2014-11-17"), 1400, "libellé")).thenReturn(opération);

        OpérationRoute.createOpération("{\"date\":\"2014-11-17\",\"libellé\":\"libellé\",\"montant\":1400}", service);
        
        verify(service).create(LocalDate.parse("2014-11-17"), 1400, "libellé");
    }
    
    @Test public void 
    should_return_created_opération_as_JSON_on_creation() {
        Opération opération = new Opération(LocalDate.parse("2014-11-17"), 1400, "libellé");
        when(service.create(LocalDate.parse("2014-11-17"), 1400, "libellé")).thenReturn(opération);

        String JSONOpération = OpérationRoute.createOpération("{\"date\":\"2014-11-17\",\"libellé\":\"libellé\",\"montant\":1400}", service);

        assertThat(JSONOpération, containsString("\"id\":"));
        assertThat(JSONOpération, containsString("\"date\":\"2014-11-17\""));
        assertThat(JSONOpération, containsString("\"libellé\":\"libellé\""));
        assertThat(JSONOpération, containsString("\"montant\":1400"));
    }

    @Test public void 
    should_add_a_ventilation_to_an_opération() {
        Opération opération = new Opération(OpérationId.valueOf(2), LocalDate.parse("2014-11-17"), 1400, "libellé");
        when(service.get(opération.id())).thenReturn(opération);
        Ventilation ventilation = new Ventilation(VentilationId.next(), 600, "a_catégorie");
        when(service.addVentilation(opération.id(), ventilation.montant(), ventilation.catégorie())).thenReturn(ventilation);

        OpérationRoute.addVentilationToOpération(OpérationId.valueOf(2), "{\"montant\":600,\"catégorie\":\"a_catégorie\"}", service);

        verify(service).addVentilation(OpérationId.valueOf("2"), 600, "a_catégorie");
    }
}
