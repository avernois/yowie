package fr.craftinglabs.apps.yowie.core.model;

import fr.craftinglabs.apps.yowie.core.infrastructure.repositories.InMemoryOpérationsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpérationServiceTest {

    Opérations opérations;
    OpérationService service;

    @Before
    public void before() {
        opérations = mock(Opérations.class);
        service = new OpérationService(opérations);
    }
    
    @Test public void 
    should_return_an_opération_given_its_id() {
        OpérationId id = OpérationId.valueOf(2);
        Opération opération = new Opération(id, LocalDate.parse("2014-11-17"), 1200, "libellé");
        when(opérations.get(id)).thenReturn(opération);
        
        assertThat(service.get(id), is(opération));
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
        when(opérations.get(opération.id())).thenReturn(opération);

        Opération createdOpération = service.get(opération.id());

        assertThat(createdOpération, is(opération));
    }
    
    @Test public void
    should_create_new_opération_with_différent_ids() {
        Opération opération = service.create(LocalDate.parse("2015-02-02"), 1200, "libellé");
        Opération anotherOpération = service.create(LocalDate.parse("2015-02-02"), 1500, "another libellé");
        
        assertThat(opération.id(), is(not(anotherOpération.id())));
    }

    @Test public void
    should_add_ventilation_to_an_opération() {
        Opération opération = new Opération(OpérationId.valueOf(2), LocalDate.parse("2015-02-02"), 1200, "libellé");
        when(opérations.get(opération.id())).thenReturn(opération);

        Ventilation addedVentilation = service.addVentilation(opération.id(), 600, "catégorie");

        assertThat(opération.ventilations(), hasItems(addedVentilation));
        verify(opérations).store(opération);
    }

    @Test public void 
    should_return_the_newly_added_ventilation() {
        Opération opération = new Opération(OpérationId.valueOf(2), LocalDate.parse("2015-02-02"), 1200, "libellé");
        when(opérations.get(opération.id())).thenReturn(opération);

        Ventilation addedVentilation = service.addVentilation(opération.id(), 600, "catégorie");

        assertThat(addedVentilation.catégorie(), is("catégorie"));
        assertThat(addedVentilation.montant(), is(600));
    }
}
