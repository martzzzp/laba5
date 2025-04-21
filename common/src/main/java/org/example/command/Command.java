package org.example.command;

/**
 * Базовый класс для команд — хранит имя и описание.
 */
public abstract class Command implements CommandInterface {
    private final String name;
    private final String description;

    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    /** Если у вас есть метод getDescription(), оставьте его на месте */
    public String getDescription() {
        return description;
    }
}
