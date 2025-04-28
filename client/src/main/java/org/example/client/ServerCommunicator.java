package org.example.client;

import com.google.gson.Gson;
import org.example.common.protocol.Request;
import org.example.common.protocol.Response;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/** Отправляет JSON-запрос и ждёт JSON-ответ. */
public class ServerCommunicator {
    private final SocketChannel channel;
    private final Gson gson = new Gson();

    public ServerCommunicator(String host, int port) throws IOException {
        this.channel = SocketChannel.open(new InetSocketAddress(host, port));
        this.channel.configureBlocking(true);
    }

    public Response send(Request req) throws IOException {
        String jsonReq = gson.toJson(req) + "\n";
        channel.write(ByteBuffer.wrap(jsonReq.getBytes(StandardCharsets.UTF_8)));

        ByteBuffer in = ByteBuffer.allocate(8 * 1024);
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
