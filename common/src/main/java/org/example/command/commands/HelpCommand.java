package org.example.command.commands;

import org.example.common.managers.CommandManager;    // <-- вот сюда
import org.example.command.CommandInterface;
import org.example.command.ConsoleOutput;

import java.util.stream.Collectors;

public class HelpCommand implements CommandInterface {
    private final CommandManager cm; //метод CommandManager, возвращающий список всех зарегистрированных команд
    private final ConsoleOutput out; //выводит строку на консоль сервера (лог).

    public HelpCommand(CommandManager cm, ConsoleOutput out) {
        this.cm = cm;
        this.out = out;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Показывает список команд с описаниями";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        String helpText = cm.getAllCommands().stream()
                .map(cmd -> String.format("%-10s — %s", cmd.getName(), cmd.getDescription()))
                .collect(Collectors.joining("\n"));
        out.println(helpText);
        return payload;
    }
}
