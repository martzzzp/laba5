// server/src/main/java/org/example/server/processor/CommandProcessor.java
package org.example.server.processor;

import org.example.common.protocol.Request;
import org.example.common.protocol.Response;
import org.example.command.CommandInterface;
import org.example.common.managers.CommandManager;

/**
 * Находит нужную команду, выполняет её и возвращает статус выполнения.
 */
public class CommandProcessor {
    private final CommandManager commandManager;

    public CommandProcessor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response process(Request req) {
        try {
            // получаем реализацию команды по её имени
            CommandInterface cmd = commandManager.get(req.getCommandName());
            // готовим аргументы
            String[] args = req.getArgs().toArray(new String[0]);
            // исполняем команду (void execute)
            cmd.execute(args);
            // возвращаем успешный ответ без payload
            return new Response(true, null, null);
        } catch (Exception e) {
            // в случае ошибки – возвращаем текст ошибки
            return new Response(false, null, e.getMessage());
        }
    }
}


