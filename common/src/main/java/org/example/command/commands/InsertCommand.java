package org.example.command.commands;

import org.example.command.CommandInterface;
import org.example.common.entity.Product;
import org.example.common.managers.CollectionManager;
import org.example.command.ConsoleOutput;

//Команда insert принимает объект Product в payload. На клиенте мы его будем сериализовывать
//в JSON (вы уже написали ServerCommunicator). На сервере просто приводим payload к Product и вызываем coll.insert(...):
public class InsertCommand implements CommandInterface {
    private final CollectionManager coll;
    private final ConsoleOutput out;

    public InsertCommand(CollectionManager coll, ConsoleOutput out) {
        this.coll = coll;
        this.out = out;
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "Добавляет новый элемент в коллекцию (payload: Product)";
    }

    @Override
    public Object execute(String[] args, Object payload) {
        if (!(payload instanceof Product)) {
            throw new IllegalArgumentException("Payload must be a Product");
        }
        Product p = (Product) payload;
        coll.insert(p);
        out.println("Inserted with id=" + p.getId());
        return payload;
    }
}
