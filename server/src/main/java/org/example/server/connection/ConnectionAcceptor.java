// server/src/main/java/org/example/server/connection/ConnectionAcceptor.java
package org.example.server.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;

/**
 * Слушает порт и принимает новые подключения в неблокирующем режиме.
 */
public class ConnectionAcceptor {
    private final ServerSocketChannel serverChannel;
    private final Selector selector;

    public ConnectionAcceptor(int port, Selector selector) throws IOException {
        this.selector = selector;
        this.serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(port));
        serverChannel.configureBlocking(false);
    }

    /** Регистрирует серверный канал для приёма OP_ACCEPT */
    public void register() throws ClosedChannelException {
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /** При наступлении OP_ACCEPT принимает клиента и регистрирует его для OP_READ */
    public void acceptConnections() throws IOException {
        SocketChannel client = serverChannel.accept();
        if (client != null) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        }
    }
}
