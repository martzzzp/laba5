// server/src/main/java/org/example/server/reader/RequestReader.java
package org.example.server.reader;

import com.google.gson.Gson;
import org.example.common.protocol.Request;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Читает из канала JSON‑запрос и парсит его в Request.
 */
public class RequestReader {
    private final Gson gson = new Gson();
    /** Читает одну строку из канала и парсит в Request */
    public Optional<Request> read(SelectionKey key) throws IOException {
        SocketChannel ch = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(8192);
        int read = ch.read(buf);
        if (read < 0) {
            key.cancel();
            ch.close();
            return Optional.empty();
        }

        buf.flip();
        String json = StandardCharsets.UTF_8.decode(buf).toString().trim();
        Request req = gson.fromJson(json, Request.class);
        return Optional.of(req);
    }
}
