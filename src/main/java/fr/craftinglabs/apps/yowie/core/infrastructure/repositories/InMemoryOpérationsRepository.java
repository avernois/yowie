package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.Opérations;

public class InMemoryOpérationsRepository implements Opérations {

    Map<Integer, Opération> opérations = new HashMap<Integer, Opération>();
    
    public InMemoryOpérationsRepository() {
        store(new Opération(4, LocalDate.parse("2014-11-17"), 1400, "libellé"));
       
    }
    
    @Override
    public Opération get(int id) {

        return opérations.get(id);
    }

    @Override
    public void store(Opération opération) {
        opérations.put(opération.id(), opération);
    }
    
}
