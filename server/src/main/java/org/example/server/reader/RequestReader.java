// server/src/main/java/org/example/server/reader/RequestReader.java
package org.example.server.reader;

import com.google.gson.Gson;
import org.example.common.protocol.Request;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Optional;

/**
 * Читает из канала JSON‑запрос и парсит его в Request.
 */
public class RequestReader {
    private static final int BUF_SIZE = 8192;
    private final Gson gson = new Gson();

    /**
     * @return Optional.empty(), если данных нет; иначе десериализованный Request
     */
    public Optional<Request> read(SelectionKey key) throws IOException {
        SocketChannel ch = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
        int read = ch.read(buf);
        if (read <= 0) return Optional.empty();

        buf.flip();
        byte[] bytes = new byte[buf.remaining()];
        buf.get(bytes);
        String json = new String(bytes);
        Request req = gson.fromJson(json, Request.class);
        return Optional.of(req);
    }
}
