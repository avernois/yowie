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

    public Ventilation addVentilation(OpérationId id, int montant, Catégorie catégorie) {
        Opération opération = opérations.get(id);

        Ventilation ventilation = new Ventilation(VentilationId.next(), montant, catégorie);
        opération.addVentilation(ventilation);
        opérations.store(opération);

        return ventilation;
    }
}
