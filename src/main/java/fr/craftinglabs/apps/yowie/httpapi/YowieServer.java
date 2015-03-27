package fr.craftinglabs.apps.yowie.httpapi;

import fr.craftinglabs.apps.yowie.httpapi.infrastructure.InjectionBinder;
import httpapi.infrastructure.Env;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Main class.
 *
 */
public class YowieServer {
        
    private static final Integer DEFAULT_PORT = 5000;

    public static HttpServer startServer(String hostname, Integer port, String appname) {
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(hostname + ":" + port + "/" + appname), createApp());
    }

    public static void main(String[] args) throws InterruptedException {
        
        HttpServer server = startServer("http://0.0.0.0", Env.getPort(DEFAULT_PORT), "yowie");
        
        addShutdownHook(server);
        Thread.currentThread().join();
    }

    private static void addShutdownHook(HttpServer server) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.shutdown();
            }
        }, "shutdownHook"));
    }

    public static ResourceConfig createApp() {
            
            final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
            namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
             
            return new ResourceConfig()
                        .packages("fr.craftinglabs.apps.yowie.httpapi")
                        .register(new InjectionBinder());
    }
}

