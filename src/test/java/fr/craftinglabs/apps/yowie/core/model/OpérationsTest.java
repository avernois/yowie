package fr.craftinglabs.apps.yowie.core.model;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

abstract public class OpérationsTest {
    
    protected Opérations opérations;
    
    @Before
    public void setup() {
        opérations = constructOpérations();
    }
    
    @Test public void 
    should_store_and_get_by_id_an_opération() {
        OpérationId id = OpérationId.valueOf(2);
        Opération opération = new Opération(id, LocalDate.parse("2014-11-17"), 1200, "libellé");
        opérations.store(opération);
        
        Opération actual = opérations.get(id);
        
        assertThat(actual, is(opération));
    }

    @Test
    public void
    should_store_and_get_by_id_an_opération_with_ventilation() {
        OpérationId id = OpérationId.valueOf(2);
        Opération opération = new Opération(id, LocalDate.parse("2014-11-17"), 1200, "libellé");
        opération.addVentilation(new Ventilation(VentilationId.valueOf(1), 600, Catégorie.ACHATS));
        opération.addVentilation(new Ventilation(VentilationId.valueOf(2), 800, Catégorie.ACHATS));

        opérations.store(opération);

        Opération actual = opérations.get(id);

        assertThat(actual, is(opération));
    }

    abstract protected Opérations constructOpérations();
}
