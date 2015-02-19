package fr.craftinglabs.apps.yowie.core.model;

public interface Opérations {
    
    public Opération get(OpérationId id);

    public void store(Opération opération);
}
