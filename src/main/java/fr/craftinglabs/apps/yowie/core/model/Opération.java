package fr.craftinglabs.apps.yowie.core.model;

import java.time.LocalDate;

public class Opération {
    private OpérationId id;
    private LocalDate date;
    private int montant;
    private String libellé;

    public Opération(OpérationId id, LocalDate date, int montant, String libellé) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.libellé = libellé;
    }

    public Opération(LocalDate date, int montant, String libellé) {
        this(OpérationId.next(), date, montant, libellé);
    }

    public OpérationId id() {
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
