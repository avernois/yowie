package fr.craftinglabs.apps.yowie.httpapi.routing;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;
import fr.craftinglabs.apps.yowie.httpapi.routing.OpérationResource;

@RunWith(MockitoJUnitRunner.class)
public class OpérationResourceTest {

    @Mock OpérationService service;
    @InjectMocks OpérationResource resource = new OpérationResource();
    
    @Test public void 
    should_return_the_JSON_representation_of_an_existing_operation() {
        Opération opération = new Opération(2, LocalDate.parse("2014-12-09"), 1200, "un libellé");
        when(service.get(2)).thenReturn(opération);
        
        assertThat(resource.getOpérationByIdAsJSON("2"), is(OpérationToJSON.parse(opération)));
    }
    
    @Test public void 
    should_create_opération() {
        resource.createOpération("{\"date\":\"2014-11-17\",\"libellé\":\"libellé\",\"montant\":1400}");
        
        verify(service).create(LocalDate.parse("2014-11-17"), 1400, "libellé");
    }
}
