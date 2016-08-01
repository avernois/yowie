package fr.craftinglabs.apps.yowie.httpapi;

import fr.craftinglabs.apps.yowie.core.infrastructure.repositories.FileOpérationsRepository;
import fr.craftinglabs.apps.yowie.core.infrastructure.repositories.InMemoryOpérationsRepository;
import fr.craftinglabs.apps.yowie.core.model.OpérationId;
import fr.craftinglabs.apps.yowie.core.model.OpérationService;
import fr.craftinglabs.apps.yowie.core.model.Opérations;
import fr.craftinglabs.apps.yowie.httpapi.routing.OpérationRoute;
import httpapi.infrastructure.Env;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static spark.Spark.*;

/**
 * Main class.
 *
 */
public class YowieServer {
        
    private static final Integer DEFAULT_PORT = 5000;


    public static void main(String[] args) throws InterruptedException, IOException {
        port(Env.getPort(DEFAULT_PORT));
        Opérations opérations = new FileOpérationsRepository(Paths.get(System.getProperty("user.home") + File.separator + ".yowie" + File.separator));
        OpérationService service = new OpérationService(opérations);

        get("/operations/:operationId", ((request, response) -> OpérationRoute.getOpérationByIdAsJSON(OpérationId.valueOf(request.params(":operationId")), service)));

        post("/operations/", ((request, response) -> OpérationRoute.createOpération(request.body(), service)));

        post("/operations/:operationId/ventilations/", ((request, response) -> OpérationRoute.addVentilationToOpération(OpérationId.valueOf(request.params(":operationId")), request.body(), service)));
        
        addShutdownHook();
        Thread.currentThread().join();
    }

    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Exiting.");
            stop();
        }, "shutdownHook"));
    }
}

