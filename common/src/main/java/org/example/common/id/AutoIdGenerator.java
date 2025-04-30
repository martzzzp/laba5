package org.example.common.id;

/** Генератор сквозных ID. */
public class AutoIdGenerator {
    private long current = 0;
    public long nextId() { return ++current; }
    public void synchronizeWithExisting(long maxId) { current = maxId; }
}
