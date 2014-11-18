package fr.craftinglabs.apps.yowie.core.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.Opérations;

abstract public class OpérationsTest {
    
    public Opérations opérations;
    
    @Before
    public void setup() {
        opérations = constructOpérations();
    }
    
    @Test public void 
    should_store_and_get_by_id_an_opération() {
        Opération opération = new Opération(2, LocalDate.parse("2014-11-17"), 1200, "libellé");
        opérations.store(opération);
        
        Opération actual = opérations.get(2);
        
        assertThat(actual, is(opération));
    }
    
    abstract public Opérations constructOpérations();
}
