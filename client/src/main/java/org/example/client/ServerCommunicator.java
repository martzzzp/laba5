package org.example.client;

import com.google.gson.Gson;
import org.example.common.protocol.Request;
import org.example.common.protocol.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerCommunicator { //Отправляет JSON-запрос и ждёт JSON-ответ. Здесь мы используем GSON и блокирующий канал
    private final SocketChannel channel;
    private final Gson gson = new Gson();

    public ServerCommunicator(String host, int port) throws IOException {
        channel = SocketChannel.open(new InetSocketAddress(host, port));
        channel.configureBlocking(true);
    }

    /** Отправляет Request и возвращает распарсенный Response. */
    public Response send(Request req) throws IOException {
        String jsonReq = gson.toJson(req) + "\n";
        ByteBuffer out = ByteBuffer.wrap(jsonReq.getBytes(StandardCharsets.UTF_8));
        channel.write(out);

        // прочитать всю строку-ответ (до \n)
        ByteBuffer in = ByteBuffer.allocate(8192);
        int read = channel.read(in);
        if (read < 0) throw new IOException("Connection closed by server");
        in.flip();
        String jsonRes = StandardCharsets.UTF_8.decode(in).toString().trim();
        return gson.fromJson(jsonRes, Response.class);
    }

    public void close() throws IOException {
        channel.close();
    }
}

