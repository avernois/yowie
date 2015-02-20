package fr.craftinglabs.apps.yowie.core.model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class OpérationIdTest {

    @Test public void 
    should_have_a_string_representation() {
        OpérationId id = OpérationId.valueOf(1);
        
        assertThat(id.toString(), is("1"));
    }

    @Test public void 
    should_be_constructed_from_a_string() {
        OpérationId id = OpérationId.valueOf("2");
        
        assertThat(id.toString(), is("2"));
    }
    
    @Test public void 
    should_return_a_different_id_on_different_call() {
        OpérationId id = OpérationId.next();
        OpérationId id2 = OpérationId.next();
        
        assertThat(id, is(not(id2)));
    }
    
    
    @Test public void 
    should_be_equals_to_itself() {
        OpérationId id = OpérationId.valueOf(2);
        
        assertThat(id.equals(id), is(true));
    }
    
    @Test public void 
    should_be_equals_to_another_id_of_same_value() {
        OpérationId id = OpérationId.valueOf(2);
        OpérationId anId = OpérationId.valueOf("2");
        
        assertThat(id.equals(anId), is(true));
    }
    
    @Test public void 
    should_not_be_equals_to_another_id_of_another_value() {
        OpérationId id = OpérationId.valueOf(2);
        OpérationId anId = OpérationId.valueOf(3);
        
        assertThat(id.equals(anId), is(false));
    }
    
    @Test public void 
    should_not_be_equals_to_another_kind_of_object() {
        OpérationId id = OpérationId.valueOf(2);
        
        Object something = new Object();
        
        assertThat(id.equals(something), is(false));
    }
    
    @Test public void 
    should_have_same_hashcode_when_they_are_equal() {
        OpérationId id = OpérationId.valueOf(2);
        OpérationId anId = OpérationId.valueOf("2");
        
        assertThat(id.hashCode(), is(anId.hashCode()));
    }
    
}