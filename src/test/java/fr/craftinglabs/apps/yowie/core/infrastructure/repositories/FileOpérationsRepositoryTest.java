package fr.craftinglabs.apps.yowie.core.infrastructure.repositories;

import fr.craftinglabs.apps.yowie.core.model.Opérations;
import fr.craftinglabs.apps.yowie.core.model.OpérationsTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.assertTrue;

public class FileOpérationsRepositoryTest extends OpérationsTest {
    Path directory = Paths.get("src/test/resources/operationRepository/");

    @Before
    public void before() {
        if(Files.notExists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                throw new RuntimeException("can't create testing repository directory: " + directory.toString());
            }
        }
    }


    @Override
    public Opérations constructOpérations() {
        try {
            return new FileOpérationsRepository(directory);
        } catch (IOException e) {
            return null;
        }
    }

    @Test public void 
    should_create_repertory_if_it_does_not_exist() throws IOException {
        Path path = Paths.get(directory.toString(), "an_inexisting_directory/");
        new FileOpérationsRepository(path);

        assertTrue(Files.exists(path));

        Files.deleteIfExists(path);
    }

    @After public void
    after() {
        try {
            recursivelyDeletes(directory);
        } catch (IOException e) {
            throw new RuntimeException("Can't delete files in testing repository: " + directory);
        }
    }

    private void recursivelyDeletes(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
