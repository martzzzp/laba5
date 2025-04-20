package org.example.command.commands;

import org.example.command.Command;
import org.example.command.ConsoleInput;
import org.example.command.ConsoleOutput;
import org.example.managers.CommandManager;
import org.example.managers.InputManager;
import org.example.managers.FileScriptsManager;
import org.example.managers.RuntimeManager;

import java.io.*;

/**
 * Класс команды execute_script
 */
public class ExecuteScriptCommand extends Command {
    private final ConsoleOutput consoleOutput;
    private final CommandManager commandManager;
    private final ConsoleInput consoleInput;
    private final FileScriptsManager fileScriptsManager;

    public ExecuteScriptCommand(ConsoleOutput consoleOutput, CommandManager commandManager, ConsoleInput consoleInput, FileScriptsManager fileScriptsManager) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.consoleOutput = consoleOutput;
        this.commandManager = commandManager;
        this.consoleInput = consoleInput;
        this.fileScriptsManager = fileScriptsManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            consoleOutput.printError("Команда принимает 1 аргумент");
            return;
        }
        try {
            File scriptFile = new File(args[0]);
            if (!scriptFile.exists()) {
                throw new FileNotFoundException();
            }
            consoleOutput.println("Исполнение файла " + scriptFile.getName());
            if (FileScriptsManager.checkIfLaunchedInStack(scriptFile)) {
                consoleOutput.printError(String.format("Файл \"%s\" вызывается повторно (рекурсивно)", scriptFile.getName()));
                return;
            }
            FileScriptsManager.addFile(scriptFile);
            ConsoleInput.setFileMode(true);

            for (String line = fileScriptsManager.readLine(); line != null; line = fileScriptsManager.readLine()) {
                RuntimeManager.launchCommand(line.split(" "), commandManager, consoleOutput);
            }
            consoleOutput.println("- Завершение исполнения файла " + scriptFile.getName());
            ConsoleInput.setFileMode(false);
            FileScriptsManager.removeFile(scriptFile);


        } catch (FileNotFoundException e) {
            consoleOutput.printError("Файл " + args[0] + " не найден");
        }
    }
}