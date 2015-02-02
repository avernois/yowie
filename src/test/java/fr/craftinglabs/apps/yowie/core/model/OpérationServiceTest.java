package fr.craftinglabs.apps.yowie.core.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpérationServiceTest {

    @Mock Opérations opérations;
    @InjectMocks OpérationService service = new OpérationService();
    
    @Test public void 
    should_return_an_opération_given_its_id() {
        Opération opération = new Opération(2, LocalDate.parse("2014-11-17"), 1200, "libellé");
        when(opérations.get(2)).thenReturn(opération);
        
        assertThat(service.get(2), is(opération));
    }
    
    @Test public void 
    should_return_a_new_opération() {
        Opération opération = service.create(LocalDate.parse("2015-02-02"), 1200, "libellé");
        
        assertThat(opération.date(), is(LocalDate.parse("2015-02-02")));
        assertThat(opération.montant(), is(1200));
        assertThat(opération.libellé(), is("libellé"));
    }

    @Test public void
    should_store_opération_on_creation() {
        Opération opération = service.create(LocalDate.parse("2015-02-02"), 1200, "libellé");
        
        verify(opérations).store(opération);
    }
    
    @Test public void
    should_create_new_opération_with_différent_ids() {
        Opération opération = service.create(LocalDate.parse("2015-02-02"), 1200, "libellé");
        Opération anotherOpération = service.create(LocalDate.parse("2015-02-02"), 1500, "another libellé");
        
        assertThat(opération.id(), is(not(anotherOpération.id())));
    }
}
