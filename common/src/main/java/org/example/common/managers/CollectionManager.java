package org.example.common.managers;

import org.example.common.entity.Product;
import org.example.common.id.AutoIdGenerator;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Менеджер коллекции продуктов.
 * Все операции над коллекцией реализованы через Stream API.
 */
public class CollectionManager {
    private final List<Product> products = new ArrayList<>();
    private final AutoIdGenerator idGen;

    /**
     * @param initial начальный список (например, загруженный из файла)
     * @param idGen   генератор уникальных ID
     */
    public CollectionManager(List<Product> initial, AutoIdGenerator idGen) {
        if (initial != null) {
            // копируем, чтобы не захардкодить внешний список
            products.addAll(initial);
        }
        this.idGen = idGen;
    }

    /** Возвращает все продукты, отсортированные по возрастанию id. */
    public List<Product> getAllSortedById() {
        return products.stream()
                .sorted(Comparator.comparingLong(Product::getId))
                .collect(Collectors.toList());
    }

    /** Команда show: возвращает строку со всеми продуктами. */
    public String show() {
        return getAllSortedById().stream()
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
    }

    /** Команда info: возвращает тип коллекции, её размер и дату инициализации. */
    public String info() {
        return String.format("Тип: %s, размер: %d",
                products.getClass().getSimpleName(),
                products.size());
    }

    /** Вставляет новый продукт, присваивая ему уникальный ID. */
    public void insert(Product p) {
        long newId = idGen.nextId();
        p.setId(newId);
        products.add(p);
    }

    /** Удаляет продукт по ID. Возвращает true, если удаление произошло. */
    public boolean removeById(long id) {
        return products.removeIf(prod -> prod.getId() == id);
    }

    /** Обновляет продукт с данным ID. Возвращает true, если найден и обновлён. */
    public boolean update(long id, Product newProduct) {
        Optional<Product> existing = products.stream()
                .filter(prod -> prod.getId() == id)
                .findFirst();
        if (existing.isPresent()) {
            Product old = existing.get();
            newProduct.setId(id);
            products.set(products.indexOf(old), newProduct);
            return true;
        }
        return false;
    }

    /** Очищает коллекцию. */
    public void clear() {
        products.clear();
    }

    /**
     * Добавляет продукт, если его значение (например, price) больше всех остальных.
     * Возвращает true, если добавление произошло.
     */
    public boolean addIfMax(Product p) {
        Optional<Product> max = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice));
        double maxPrice = max.map(Product::getPrice).orElse((long) Double.MIN_VALUE);
        if (p.getPrice() > maxPrice) {
            insert(p);
            return true;
        }
        return false;
    }

    /**
     * Удаляет все продукты, которые "больше" заданного (по price).
     * Возвращает количество удалённых элементов.
     */
    public long removeGreater(Product p) {
        long before = products.size();
        products.removeIf(prod -> prod.getPrice() > p.getPrice());
        return before - products.size();
    }

    /**
     * Возвращает список продуктов с price меньше заданного.
     * Список также отсортирован по ID.
     */
    public List<Product> filterLessThanPrice(double price) {
        return products.stream()
                .filter(prod -> prod.getPrice() < price)
                .sorted(Comparator.comparingLong(Product::getId))
                .collect(Collectors.toList());
    }

    /** Считает, сколько продуктов имеют price больше заданного. */
    public long countGreaterThanPrice(double price) {
        return products.stream()
                .filter(prod -> prod.getPrice() > price)
                .count();
    }

    /** Возвращает список продуктов в порядке убывания price. */
    public List<Product> sortDescendingByPrice() {
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }
}

