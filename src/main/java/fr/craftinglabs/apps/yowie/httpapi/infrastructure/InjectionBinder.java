package fr.craftinglabs.apps.yowie.httpapi.infrastructure;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import fr.craftinglabs.apps.yowie.core.infrastructure.repositories.InMemoryOpérationsRepository;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;
import fr.craftinglabs.apps.yowie.core.model.Opérations;

public class InjectionBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(InMemoryOpérationsRepository.class).to(Opérations.class);
        bind(OpérationService.class).to(OpérationService.class);
    }
}