package fr.craftinglabs.apps.yowie.core.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OpérationTest {
    
    @Test public void 
    should_have_an_id() {
        OpérationId id = OpérationId.valueOf(1);
        Opération opération = new Opération(id, LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertThat(opération.id(), is(id));
    }
    
    @Test public void 
    should_have_a_libellé() {
        Opération opération = new Opération(OpérationId.valueOf(1), LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertEquals("le libellé de l'opération", opération.libellé());
    }
    
    @Test public void 
    should_have_a_montant() {
        Opération opération = new Opération(OpérationId.valueOf(1), LocalDate.parse("2014-11-17"), 1200, "le libellé de l'opération");
        
        assertEquals(1200, opération.montant());
    }
    
    @Test public void 
    should_have_a_date() {
        LocalDate date = LocalDate.parse("2014-11-17");
        Opération opération = new Opération(OpérationId.valueOf(1), date, 1200, "le libellé de l'opération");
        
        assertEquals(date, opération.date());
    }
    
    @Test public void 
    should_set_an_id_when_created_without() {
        LocalDate date = LocalDate.parse("2014-11-17");
        Opération opération = new Opération(date, 1200, "le libellé de l'opération");
        
        assertThat(opération.id(), is(not(0)));
    }
    
    @Test public void 
    should_set_different_id_when_created_without() {
        LocalDate date = LocalDate.parse("2014-11-17");
        Opération opération = new Opération(date, 1200, "le libellé de l'opération");
        Opération anotherOpération = new Opération(date, 1500, "un autre libellé");
        
        assertThat(opération.id(), is(not(anotherOpération.id())));
    }
}
