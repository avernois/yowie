package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import fr.craftinglabs.apps.yowie.core.model.Opérations;
import fr.craftinglabs.apps.yowie.core.model.OpérationsTest;

public class InMemoryOpérationsRepositoryTest extends OpérationsTest {
    
    @Override
    public Opérations constructOpérations() {
        return new InMemoryOpérationsRepository();
    }
}
