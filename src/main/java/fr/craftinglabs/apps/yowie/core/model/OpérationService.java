package fr.craftinglabs.apps.yowie.core.model;

import java.time.LocalDate;

public class OpérationService {

    Opérations opérations;

    public OpérationService(Opérations opérations) {
        this.opérations = opérations;
    }

    public Opération get(OpérationId id) {
        return opérations.get(id);
    }

    public Opération create(LocalDate date, int montant, String libellé) {
        Opération opération = new Opération(date, montant, libellé);
        
        opérations.store(opération);
        
        return opération;
    } 
}
