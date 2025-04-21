package org.example.server;

import org.example.common.managers.CollectionManager;
import org.example.common.managers.CommandManager;
import org.example.common.protocol.Request;
import org.example.common.protocol.Response;
import org.example.command.ConsoleOutput;
import org.example.entity.Product;
import org.example.server.connection.ConnectionAcceptor;
import org.example.server.file.FileManagerServer;
import org.example.server.id.AutoIdGenerator;
import org.example.server.processor.CommandProcessor;
import org.example.server.reader.RequestReader;
import org.example.server.sender.ResponseSender;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: ServerMain <data_file> <port>");
            return;
        }

        String dataFile = args[0];
        int port = Integer.parseInt(args[1]);
        ConsoleOutput consoleOutput = new ConsoleOutput();

        // 1) Работа с файлом
        FileManagerServer fileManager = new FileManagerServer(new File(dataFile), consoleOutput);
        if (!fileManager.validate()) return;
        List<Product> products = fileManager.loadCollection();

        // 2) Генератор ID
        AutoIdGenerator idGen = new AutoIdGenerator();
        long maxId = products.stream().mapToLong(Product::getId).max().orElse(0L);
        idGen.synchronizeWithExisting(maxId);

        // 3) Менеджеры
        //CollectionManager collectionManager = new CollectionManager(products, idGen);
        CommandManager   commandManager    = new CommandManager();
        // TODO: зарегистрируйте ваши команды, например:
        // commandManager.addCommands(Arrays.asList(
        //     new HelpCommand(commandManager, consoleOutput),
        //     new InsertCommand(consoleOutput, new ConsoleInput(), collectionManager),
        //     …
        // ));

        // 4) NIO‑модули
        try {
            Selector selector = Selector.open();
            ConnectionAcceptor acceptor = new ConnectionAcceptor(port, selector);
            acceptor.register();

            // сюда возвращаем processor и reader/sender
            RequestReader    reader    = new RequestReader();
            CommandProcessor processor = new CommandProcessor(commandManager);
            ResponseSender   sender    = new ResponseSender();

            consoleOutput.println("Server started on port " + port);

            // 5) Однопоточный цикл
            while (true) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        acceptor.acceptConnections();
                    } else if (key.isReadable()) {
                        reader.read(key).ifPresent(req -> {
                            Response res = processor.process(req);
                            try {
                                sender.send(key, res);
                            } catch (IOException e) {
                                key.cancel();
                            }
                        });
                    }
                }
                selector.selectedKeys().clear();
            }

        } catch (IOException e) {
            consoleOutput.printError("Server error: " + e.getMessage());
        }
    }
}
