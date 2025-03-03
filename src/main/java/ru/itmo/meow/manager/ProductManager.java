package ru.itmo.meow.manager;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong; //это потокобезопасный счетчик чисел типа long. Он используется, когда нужно автоматически увеличивать число без проблем с многопоточностью
import ru.itmo.meow.model.Product;

public class ProductManager {
    private HashMap<Long, Product> products = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(1); // Счетчик для авто-генерации id, Теперь каждый Product получает уникальный id, даже если программа работает в многопоточном режиме

    public ProductManager() {
        // Конструктор
    }

    // Метод для генерации уникального id
    private long generateId() {
        return idCounter.getAndIncrement();
    }

    // Метод проверки валидности продукта
    private boolean validateProduct(Product product) {
        if (product == null) {
            System.out.println("Ошибка: Продукт не может быть null.");
            return false;
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            System.out.println("Ошибка: Название продукта не может быть пустым.");
            return false;
        }
        if (product.getPrice() <= 0) {
            System.out.println("Ошибка: Цена продукта должна быть больше 0.");
            return false;
        }
        if (products.containsValue(product)) {
            System.out.println("Ошибка: Такой продукт уже есть в коллекции.");
            return false;
        }
        return true;
    }

    // Метод для добавления продукта с авто-генерацией id
    public void addProduct(Product product) {
        if (validateProduct(product)) { // Проверяем продукт перед добавлением
            long newId = generateId(); // Генерируем уникальный id
            product.setId(newId); // Присваиваем id продукту
            products.put(newId, product);
            System.out.println("Продукт успешно добавлен с id: " + newId);
        } else {
            System.out.println("Ошибка: Продукт не добавлен из-за некорректных данных.");
        }
    }

    // Метод для получения всех продуктов
    public HashMap<Long, Product> getProducts() {
        return products;
    }

    // Метод для удаления продукта по id
    public void removeProduct(Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            System.out.println("Продукт с id " + id + " удален.");
        } else {
            System.out.println("Ошибка: Продукта с таким id не существует.");
        }
    }

    // Метод для очистки коллекции
    public void clear() {
        products.clear();
        System.out.println("Коллекция очищена.");
    }

    public void addProductWithKey(Long id, Product product) {
        products.put(id, product);
        System.out.println("Продукт добавлен с id: " + id);
    }



}