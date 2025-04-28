// client/src/main/java/org/example/client/ConsoleReader.java
package org.example.client;
import java.util.Scanner;
import org.example.command.ConsoleInput;

/**
 * Отвечает за низкоуровневое чтение строк из консоли.
 */
public class ConsoleReader {
    private final Scanner scanner = new Scanner(System.in);

    /** Считывает следующую строку (без валидации). */
    public String readLine() {
        System.out.print("> ");
        return scanner.nextLine();
    }
}
