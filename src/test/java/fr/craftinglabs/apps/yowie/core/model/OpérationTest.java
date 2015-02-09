package fr.craftinglabs.apps.yowie.core.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import fr.craftinglabs.apps.yowie.core.model.Opération;

public class OpérationTest {
    
    @Test public void 
    should_have_an_id() {
        Opération opération = new Opération(1, LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertThat(opération.id(), is(1));
    }
    
    @Test public void 
    should_have_a_libellé() {
        Opération opération = new Opération(1, LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertEquals("le libellé de l'opération", opération.libellé());
    }
    
    @Test public void 
    should_have_a_montant() {
        Opération opération = new Opération(1, LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertEquals(1200, opération.montant());
    }
    
    @Test public void 
    should_have_a_date() {
        LocalDate date = LocalDate.parse("2014-11-17");
        Opération opération = new Opération(1, date, 1200, "le libellé de l'opération");
        
        assertEquals(date, opération.date());
    }
}
