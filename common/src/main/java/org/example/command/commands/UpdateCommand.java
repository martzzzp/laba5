package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.entity.Product;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

public class UpdateCommand implements CommandInterface {
    private final CollectionManager coll;
    private final ConsoleOutput out;

    public UpdateCommand(CollectionManager coll, ConsoleOutput out) {
        this.coll = coll;
        this.out = out;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Обновляет элемент с переданным ID (payload: Product)";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (args.length < 1) throw new IllegalArgumentException("update требует ID");
        if (!(payload instanceof Product)) throw new IllegalArgumentException("Payload must be a Product");
        long id = Long.parseLong(args[0]);
        Product p = (Product) payload;
        boolean ok = coll.update(id, p);
        out.println(ok ? "Updated." : "Not found.");
        return payload;
    }
}
