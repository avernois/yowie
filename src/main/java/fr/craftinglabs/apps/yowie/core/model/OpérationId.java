package fr.craftinglabs.apps.yowie.core.model;

import java.util.concurrent.atomic.AtomicInteger;

public class OpérationId {

    private int id;
    private static AtomicInteger ids = new AtomicInteger(1);

    private OpérationId(int id) {
        this.id = id;
    }
    
    public static OpérationId valueOf(int id) {
        return new OpérationId(id);
    }
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public static OpérationId valueOf(String id) {
        return new OpérationId(Integer.valueOf(id));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OpérationId))
            return false;

        OpérationId other = (OpérationId) obj;
        return other.id == id;
    }
    
    @Override
    public int hashCode() {
        return this.id;
    }

    public static OpérationId next() {
        return OpérationId.valueOf(ids.getAndIncrement());
    }
}
