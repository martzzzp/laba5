// server/src/main/java/org/example/server/file/FileManagerServer.java
package org.example.server.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.command.ConsoleOutput;
import org.example.common.entity.Product;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Загрузка/сохранение коллекции в JSON‑файл.
 */
public class FileManagerServer {
    private final File file;
    private final ConsoleOutput consoleOutput;
    private final Gson gson = new Gson();

    public FileManagerServer(File file, ConsoleOutput consoleOutput) {
        this.file = file;
        this.consoleOutput = consoleOutput;
    }

    /** Проверяет доступность файла */
    public boolean validate() {
        if (!file.exists()) {
            consoleOutput.printError("Файл не найден: " + file.getAbsolutePath());
            return false;
        }
        if (!file.canRead() || !file.canWrite()) {
            consoleOutput.printError("Недостаточно прав на файл: " + file.getAbsolutePath());
            return false;
        }
        return true;
    }

    /**
     * Десериализует JSON‑массив объектов Product.
     * @return пустой список, если файл пуст или произошла ошибка
     */
    public List<Product> loadCollection() {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            if (bytes.length == 0) {
                return new ArrayList<>();
            }
            String json = new String(bytes);
            Type listType = new TypeToken<List<Product>>() {}.getType();
            return gson.fromJson(json, listType);
        } catch (IOException e) {
            consoleOutput.printError("Ошибка чтения JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Сериализует список Product в JSON‑массив и записывает в файл.
     */
    public void saveCollection(List<Product> products) {
        try {
            String json = gson.toJson(products);
            Files.write(file.toPath(), json.getBytes());
            consoleOutput.println("Коллекция сохранена в " + file.getAbsolutePath());
        } catch (IOException e) {
            consoleOutput.printError("Ошибка записи JSON: " + e.getMessage());
        }
    }
}
