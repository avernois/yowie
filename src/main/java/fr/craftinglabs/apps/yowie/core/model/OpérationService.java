package fr.craftinglabs.apps.yowie.core.model;

import javax.inject.Inject;

public class OpérationService {

    @Inject Opérations opérations;

    public Opération get(int id) {
        return opérations.get(id);
    }
}
