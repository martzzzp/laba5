// client/src/main/java/org/example/client/RequestBuilder.java
package org.example.client;

import org.example.common.protocol.Request;

import java.util.Arrays;
import java.util.List;

/**
 * Строит Request из токенов командной строки.
 */
public class RequestBuilder { //Он собирает из токенов имя команды, аргументы и, при необходимости, «payload» (объект) для команд, которым нужен объект (например insert).
    /** Строка вида "insert 5" -> new Request("insert", ["5"], null) */
    public Request build(String line) {
        String[] parts = line.trim().split("\\s+");
        String cmd = parts[0];
        String[] args = parts.length > 1
                ? Arrays.copyOfRange(parts, 1, parts.length)
                : new String[0];
        return new Request(cmd, List.of(args), /* payload = */ null);
    }
}