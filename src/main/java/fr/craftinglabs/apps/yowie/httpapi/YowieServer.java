package fr.craftinglabs.apps.yowie.httpapi;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ext.ContextResolver;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

import fr.craftinglabs.apps.yowie.httpapi.infrastructure.InjectionBinder;

/**
 * Main class.
 *
 */
public class YowieServer {
    public static final String BASE_URI = "http://localhost:8080/yowie/";
        
    public static HttpServer startServer() {
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), createApp());
    }

    public static void main(String[] args) {
        try {
            final HttpServer server = startServer();

            System.out.println(String.format("Application started.%nHit enter to stop it..."));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            Logger.getLogger(YowieServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResourceConfig createApp() {
            
            final Map<String, String> namespacePrefixMapper = new HashMap<String, String>();
            namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
             
            final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig()
                        .setNamespacePrefixMapper(namespacePrefixMapper)
                        .setNamespaceSeparator(':');
             
            final ContextResolver<MoxyJsonConfig> jsonConfigResolver = moxyJsonConfig.resolver();
            
            return new ResourceConfig()
                        .packages("fr.craftinglabs.apps.yowie.httpapi")
                        .register(jsonConfigResolver)
                        .register(new InjectionBinder());
    }
}

