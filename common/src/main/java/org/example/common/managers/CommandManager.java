package org.example.common.managers;

import org.example.command.CommandInterface;
import java.util.*;
import java.util.stream.*;

/**
 * Хранит список команд и умеет их отдавать по имени.
 */
public class CommandManager {
    private final List<CommandInterface> commands = new ArrayList<>();

    /** Зарегистрировать сразу несколько команд */
    public void addCommands(List<CommandInterface> cmds) {
        commands.addAll(cmds);
    }

    /**
     * Найти команду по строковому имени.
     * @throws NoSuchElementException если команда не найдена
     */
    public CommandInterface get(String name) {
        return commands.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Unknown command: " + name));
    }
}
