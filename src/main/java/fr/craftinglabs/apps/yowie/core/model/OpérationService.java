package fr.craftinglabs.apps.yowie.core.model;

import java.time.LocalDate;

import javax.inject.Inject;

public class OpérationService {

    @Inject Opérations opérations;

    public Opération get(int id) {
        return opérations.get(id);
    }

    public Opération create(LocalDate date, int montant, String libellé) {
        Opération opération = new Opération(date, montant, libellé);
        
        opérations.store(opération);
        
        return opération;
    } 
}
