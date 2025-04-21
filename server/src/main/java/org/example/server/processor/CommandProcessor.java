// server/src/main/java/org/example/server/processor/CommandProcessor.java
package org.example.server.processor;

import org.example.common.protocol.Request;
import org.example.common.protocol.Response;
import org.example.command.CommandInterface;
import org.example.managers.CommandManager;

/**
 * Находит нужную команду и выполняет её, упаковывая результат в Response.
 */
public class CommandProcessor {
    private final CommandManager commandManager;

    public CommandProcessor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response process(Request req) {
        try {
            CommandInterface cmd = commandManager.get(req.getCommandName());
            Object result = cmd.execute(req.getArgs(), req.getPayload());
            return new Response(true, result, null);
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }
    }
}
