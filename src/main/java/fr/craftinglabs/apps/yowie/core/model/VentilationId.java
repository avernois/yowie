package fr.craftinglabs.apps.yowie.core.model;

import java.util.concurrent.atomic.AtomicInteger;

public class VentilationId {
    private int id;
    private static AtomicInteger ids = new AtomicInteger(1);

    private VentilationId(int id) {
        this.id = id;
    }

    public static VentilationId valueOf(int id) {
        return new VentilationId(id);
    }

    public static VentilationId valueOf(String id) {
        return new VentilationId(Integer.valueOf(id));
    }

    public static VentilationId next() {
        return new VentilationId(ids.getAndIncrement());
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentilationId that = (VentilationId) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
