package org.example.server.sender;

import com.google.gson.Gson;
import org.example.common.protocol.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Сериализует Response в JSON и отправляет обратно клиенту.
 */
public class ResponseSender {
    private final Gson gson = new Gson();

    public void send(SelectionKey key, Response response) throws IOException {
        SocketChannel ch = (SocketChannel) key.channel();
        String json = gson.toJson(response);
        ByteBuffer buf = ByteBuffer.wrap(json.getBytes(StandardCharsets.UTF_8));
        ch.write(buf);
        }
    }
