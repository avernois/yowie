package fr.craftinglabs.apps.yowie.core.model;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class VentilationTest {
    
    @Test public void 
    should_have_an_id() {
        VentilationId id = VentilationId.valueOf(1);
        Ventilation ventilation = new Ventilation(id, 1200, Catégorie.ACHATS);
        
        assertThat(ventilation.id(), is(id));
    }
    
    @Test public void 
    should_have_a_catégorie() {
        Ventilation ventilation = new Ventilation(VentilationId.valueOf(1), 1200, Catégorie.ACHATS);
        
        assertEquals(Catégorie.ACHATS, ventilation.catégorie());
    }
    
    @Test public void 
    should_have_a_montant() {
        Ventilation ventilation = new Ventilation(VentilationId.valueOf(1), 1200, Catégorie.ACHATS);
        
        assertEquals(1200, ventilation.montant());
    }
}
