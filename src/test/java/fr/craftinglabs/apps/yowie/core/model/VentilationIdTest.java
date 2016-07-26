package fr.craftinglabs.apps.yowie.core.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class VentilationIdTest {

    @Test public void 
    should_have_a_string_representation() {
        VentilationId id = VentilationId.valueOf(1);
        
        assertThat(id.toString(), is("1"));
    }

    @Test public void 
    should_be_constructed_from_a_string() {
        VentilationId id = VentilationId.valueOf("2");
        
        assertThat(id.toString(), is("2"));
    }
    
    @Test public void 
    should_return_a_different_id_on_different_call() {
        VentilationId id = VentilationId.next();
        VentilationId id2 = VentilationId.next();
        
        assertThat(id, is(not(id2)));
    }
    
    
    @Test public void 
    should_be_equals_to_itself() {
        VentilationId id = VentilationId.valueOf(2);
        
        assertThat(id.equals(id), is(true));
    }
    
    @Test public void 
    should_be_equals_to_another_id_of_same_value() {
        VentilationId id = VentilationId.valueOf(2);
        VentilationId anId = VentilationId.valueOf("2");
        
        assertThat(id.equals(anId), is(true));
    }
    
    @Test public void 
    should_not_be_equals_to_another_id_of_another_value() {
        VentilationId id = VentilationId.valueOf(2);
        VentilationId anId = VentilationId.valueOf(3);
        
        assertThat(id.equals(anId), is(false));
    }
    
    @Test public void 
    should_not_be_equals_to_another_kind_of_object() {
        VentilationId id = VentilationId.valueOf(2);
        
        Object something = new Object();
        
        assertThat(id.equals(something), is(false));
    }
    
    @Test public void 
    should_have_same_hashcode_when_they_are_equal() {
        VentilationId id = VentilationId.valueOf(2);
        VentilationId anId = VentilationId.valueOf("2");
        
        assertThat(id.hashCode(), is(anId.hashCode()));
    }
    
}