package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import fr.craftinglabs.apps.yowie.core.model.Opération;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.Opérations;
import fr.craftinglabs.apps.yowie.core.model.Ventilation;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOpérationsRepository implements Opérations {

    static private Map<OpérationId, Opération> opérations = new HashMap<OpérationId, Opération>();

    @Override
    public Opération get(OpérationId id) {
        return opérations.get(id);
    }
    
    @Override
    public void store(Opération opération) {
        Opération stored = new Opération(opération.id(), opération.date(), opération.montant(), opération.libellé());
        opération.ventilations().forEach(stored::addVentilation);
        opérations.put(stored.id(), stored);
    }
    
}
