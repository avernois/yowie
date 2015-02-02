package fr.craftinglabs.apps.yowie.core.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Opération {
    private int id;
    private LocalDate date;
    private int montant;
    private String libellé;

    private static AtomicInteger ids = new AtomicInteger(1);
    
    public Opération(int id, LocalDate date, int montant, String libellé) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.libellé = libellé;
    }

    public Opération(LocalDate date, int montant, String libellé) {
        this(ids.getAndIncrement(), date, montant, libellé);
    }

    public int id() {
        return id;
    }

    public LocalDate date() {
        return date;
    }

    public int montant() {
        return montant;
    }

    public String libellé() {
        return libellé;
    }

}
