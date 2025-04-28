package org.example.client;

import org.example.common.protocol.Request;
import org.example.common.protocol.Response;

import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: ClientMain <host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        ConsoleReader console = new ConsoleReader();
        RequestBuilder builder = new RequestBuilder();
        ResponseHandler handler = new ResponseHandler();
        ServerCommunicator comm;

        try {
            comm = new ServerCommunicator(host, port);
        } catch (IOException e) {
            System.err.println("Cannot connect to server: " + e.getMessage());
            return;
        }

        while (true) {
            String line = console.readLine();
            if ("exit".equalsIgnoreCase(line.trim())) break;

            Request req = builder.build(line);
            try {
                Response res = comm.send(req);
                handler.handle(res);
            } catch (IOException e) {
                System.err.println("Server unavailable, try later: " + e.getMessage());
            }
        }

        try {
            comm.close();
        } catch (IOException ignored) {}

        System.out.println("Client exited.");
    }
}
