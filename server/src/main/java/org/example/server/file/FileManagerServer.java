// server/src/main/java/org/example/server/file/FileManagerServer.java
package org.example.server.file;

import org.example.command.ConsoleOutput;
import org.example.entity.Product;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.*;

/**
 * Загрузка/сохранение коллекции в XML‑файл.
 */
public class FileManagerServer {
    private final File file;
    private final ConsoleOutput consoleOutput;

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

    /** Десериализует из XML в List<Product> */
    public List<Product> loadCollection() {
        try {
            JAXBContext ctx = JAXBContext.newInstance(ProductsWrapper.class);
            Unmarshaller um = ctx.createUnmarshaller();
            ProductsWrapper wrapper = (ProductsWrapper) um.unmarshal(file);
            return wrapper.getProducts();
        } catch (JAXBException e) {
            consoleOutput.printError("Ошибка чтения XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /** Сериализует коллекцию в XML */
    public void saveCollection(List<Product> products) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(ProductsWrapper.class);
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ProductsWrapper wrapper = new ProductsWrapper();
            wrapper.setProducts(products);
            m.marshal(wrapper, file);

            consoleOutput.println("Коллекция сохранена в " + file.getAbsolutePath());
        } catch (JAXBException e) {
            consoleOutput.printError("Ошибка записи XML: " + e.getMessage());
        }
    }

    /** Внутренний класс‑обёртка для JAXB */
    @XmlRootElement(name = "products")
    private static class ProductsWrapper {
        private List<Product> products;

        @XmlElement(name = "product")
        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
}
