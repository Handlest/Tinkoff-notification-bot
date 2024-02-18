package edu.java.bot.commands;

import lombok.Getter;

@Getter
public enum CommandsEnum {
    START("/start", "Зарегистрировать пользователя"),
    HELP("/help", "Вывести окно с командами"),
    TRACK("/track", "Начать отслеживание ссылки"),
    UNTRACK("/untrack", "Прекратить отслеживание ссылки"),
    LIST("/list", "Показать список отслеживаемых ссылок");

    private final String command;
    private final String description;

    CommandsEnum(String command, String description) {
        this.command = command;
        this.description = description;
    }
}
