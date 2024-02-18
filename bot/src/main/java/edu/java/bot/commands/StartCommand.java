package edu.java.bot.commands;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class StartCommand implements Command {
    @Override
    public String command() {
        return CommandsEnum.START.getCommand();
    }

    @Override
    public String description() {
        return CommandsEnum.START.getDescription();
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        return new SendMessage(
            chatId,
                """
                     Добро пожаловать!
                     Данный бот позволяет отслеживать изменения на интересующих Вас веб-страницах!
                     На данный момент реализована поддержка страниц github и StackOverflow
                     """
        );
    }
}
