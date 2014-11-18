package fr.craftinglabs.apps.yowie.core.model;

import static org.hamcrest.CoreMatchers.is; 
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;
import fr.craftinglabs.apps.yowie.core.model.Opérations;

@RunWith(MockitoJUnitRunner.class)
public class OpérationsServiceTest {

    @Mock Opérations opérations;
    @InjectMocks OpérationService service = new OpérationService();
    
    @Test public void 
    should_return_an_opération_given_its_id() {
        Opération opération = new Opération(2, LocalDate.parse("2014-11-17"), 1200, "libellé");
        when(opérations.get(2)).thenReturn(opération);
        
        assertThat(service.get(2), is(opération));
    }
}
