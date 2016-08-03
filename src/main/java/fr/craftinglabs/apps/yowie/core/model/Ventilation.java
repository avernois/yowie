package fr.craftinglabs.apps.yowie.core.model;

public class Ventilation {
    private VentilationId id;
    private int montant;
    private Catégorie catégorie;

    public Ventilation(VentilationId id, int montant, Catégorie catégorie) {

        this.id = id;
        this.montant = montant;
        this.catégorie = catégorie;
    }

    public VentilationId id() {
        return id;
    }

    public Catégorie catégorie() {
        return catégorie;
    }

    public int montant() {
        return montant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ventilation that = (Ventilation) o;

        if (montant != that.montant) return false;
        if (!id.equals(that.id)) return false;
        return catégorie.equals(that.catégorie);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + montant;
        result = 31 * result + catégorie.hashCode();
        return result;
    }
}
