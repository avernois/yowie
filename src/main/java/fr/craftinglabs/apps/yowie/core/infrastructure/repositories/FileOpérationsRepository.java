package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.JsonToOpération;
import fr.craftinglabs.apps.yowie.core.infrastructure.parsers.OpérationToJSON;
import fr.craftinglabs.apps.yowie.core.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpérationsRepository implements Opérations {
    private Path path;

    public FileOpérationsRepository(Path path) throws IOException {
        this.path = path;
        if(Files.notExists(path)) {
            Files.createDirectories(path);
        }
    }

    @Override
    public Opération get(OpérationId id) {
        try {
            String jsonOp = String.join("", Files.readAllLines(opérationFilePath(id)));
            return JsonToOpération.parse(jsonOp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void store(Opération opération) {
        try {
            Files.write(opérationFilePath(opération.id()), OpérationToJSON.parse(opération).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file on the system.", e);
        }
    }

    private Path opérationFilePath(OpérationId id) {
        return Paths.get(path.toString(), id.toString() + ".operation");
    }
}
