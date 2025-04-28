// common/src/main/java/org/example/command/ConsoleInput.java
package org.example.command;

import java.util.Scanner;

/** Простейшая оболочка над Scanner для чтения строк с консоли. */
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    /** Читает следующую строку. */
    public String nextLine() {
        return scanner.nextLine();
    }
}
