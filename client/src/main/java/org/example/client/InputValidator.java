// client/src/main/java/org/example/client/InputValidator.java
package org.example.client;

import java.util.List;

/**
 * Проверяет синтаксис и семантику введённой команды.
 */
public class InputValidator {
    /**
     * @throws IllegalArgumentException если формат строки неверный.
     */
    public void validate(String[] tokens) {
        if (tokens.length == 0 || tokens[0].isBlank()) {
            throw new IllegalArgumentException("Пустая команда");
        }
        // пример: команда "insert" требует payload, другие — нет
        String cmd = tokens[0];
        if ("insert".equals(cmd) && tokens.length < 2) {
            throw new IllegalArgumentException("Команда insert требует аргумент");
        }
        // тут можно добавить проверку числовых аргументов и т.д.
    }
}
