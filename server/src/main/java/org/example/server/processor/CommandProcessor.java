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
            // 1) найдём объект-команду по её имени
            CommandInterface cmd = commandManager.get(req.getCommandName());
            // 2) подготовим аргументы и payload
            String[] args = req.getArgs().toArray(new String[0]);
            Object payload = req.getPayload(); // может быть null
            // 3) выполним команду и получим результат
            Object result = cmd.execute(args, payload);
            // 4) вернём Response с result
            return new Response(/*ok=*/true, /*result=*/result, /*error=*/null);
        } catch (Exception e) {
            // при любой ошибке – вернём её текст
            return new Response(false, null, e.getMessage());
        }
    }
}


