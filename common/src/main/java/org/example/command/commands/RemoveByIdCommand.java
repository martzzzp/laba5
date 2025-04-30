package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

public class RemoveByIdCommand implements CommandInterface {
    private final CollectionManager coll;
    private final ConsoleOutput out;

    public RemoveByIdCommand(CollectionManager coll, ConsoleOutput out) {
        this.coll = coll;
        this.out = out;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент по ID";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (args.length < 1) throw new IllegalArgumentException("remove_by_id требует ID");
        long id = Long.parseLong(args[0]);
        boolean removed = coll.removeById(id);
        out.println(removed ? "Removed." : "Not found.");
        return payload;
    }
}
