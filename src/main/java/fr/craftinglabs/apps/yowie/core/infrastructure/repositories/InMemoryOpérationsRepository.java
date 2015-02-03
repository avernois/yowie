package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import java.util.HashMap;
import java.util.Map;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.Opérations;

public class InMemoryOpérationsRepository implements Opérations {

    static private Map<Integer, Opération> opérations = new HashMap<Integer, Opération>();
        
    @Override
    public Opération get(int id) {
        return opérations.get(id);
    }

    @Override
    public void store(Opération opération) {
        opérations.put(opération.id(), opération);
    }
    
}
