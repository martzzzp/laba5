package org.example.common.managers;

import org.example.command.CommandInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
     * Возвращает неизменяемый список всех зарегистрированных команд.
     * Нужен, чтобы HelpCommand мог их перечислить.
     */
    public List<CommandInterface> getAllCommands() {
        // возвращаем unmodifiableList, чтобы никто не порушил внутренний список
        return Collections.unmodifiableList(commands);
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

