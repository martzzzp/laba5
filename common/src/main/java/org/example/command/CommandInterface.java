package org.example.command;

public interface CommandInterface {
    /** Короткое имя команды, например "help", "insert" и т.д. */
    String getName();

    /** Описание команды для вывода в help. */
    String getDescription();

    /**
     * Выполнить команду.
     *
     * @param args строковые аргументы (после имени команды)
     * @throws Exception при ошибках выполнения
     * @param payload  «полезная нагрузка» — объект, если команда его требует (insert, update и т.д.), иначе null
     * @return результат выполнения — либо объект-результат (можно сериализовать в JSON), либо текст (String), либо null
     */

    Object execute(String[] args, Object payload);
}


