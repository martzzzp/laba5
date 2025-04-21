// server/src/main/java/org/example/server/file/FileScriptsManager.java
package org.example.server.file;

import org.example.command.ConsoleOutput;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Вспомогательный класс для безопасного чтения скриптов.
 * Не допускает рекурсивных include‑команд.
 */
public class FileScriptsManager {
    private final ConsoleOutput consoleOutput;
    private final Set<Path> runningScripts = new HashSet<>();

    public FileScriptsManager(ConsoleOutput consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    /**
     * Считывает строки из скрипта, предотвращая рекурсию.
     * @param scriptPath путь к файлу скрипта
     * @return список строк-команд; пустой при ошибке
     */
    public List<String> readScript(Path scriptPath) {
        if (runningScripts.contains(scriptPath)) {
            consoleOutput.printError("Рекурсивный вызов скрипта: " + scriptPath);
            return Collections.emptyList();
        }
        runningScripts.add(scriptPath);
        try {
            return Files.readAllLines(scriptPath);
        } catch (IOException e) {
            consoleOutput.printError("Не удалось прочитать скрипт: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            runningScripts.remove(scriptPath);
        }
    }
}
