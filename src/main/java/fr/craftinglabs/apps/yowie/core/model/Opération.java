package fr.craftinglabs.apps.yowie.core.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Opération {
    private OpérationId id;
    private LocalDate date;
    private int montant;
    private String libellé;
    private List<Ventilation> ventilations = new LinkedList<>();

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

    public List<Ventilation> ventilations() {
        return ventilations;
    }

    public void addVentilation(Ventilation ventilation) {
        ventilations.add(ventilation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opération opération = (Opération) o;

        if (montant != opération.montant) return false;
        if (!id.equals(opération.id)) return false;
        if (!date.equals(opération.date)) return false;
        if (!libellé.equals(opération.libellé)) return false;
        return ventilations.equals(opération.ventilations);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + montant;
        result = 31 * result + libellé.hashCode();
        result = 31 * result + ventilations.hashCode();
        return result;
    }
}
