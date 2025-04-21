// server/src/main/java/org/example/server/id/AutoIdGenerator.java
package org.example.server.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Генерация уникальных ID для новых объектов.
 */
public class AutoIdGenerator {
    private final AtomicLong counter = new AtomicLong(1);

    /** Следующий ID */
    public long nextId() {
        return counter.getAndIncrement();
    }

    /**
     * Подстроить счётчик под уже существующие ID:
     * следующий будет = maxExistingId + 1
     */
    public void synchronizeWithExisting(long maxExistingId) {
        counter.updateAndGet(curr -> Math.max(curr, maxExistingId + 1));
    }
}
