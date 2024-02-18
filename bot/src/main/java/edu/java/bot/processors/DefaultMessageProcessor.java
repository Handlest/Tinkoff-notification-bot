package edu.java.bot.processors;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.Command;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageProcessor implements UserMessageProcessor {
    private final List<Command> commands;

    public DefaultMessageProcessor(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public List<? extends Command> commands() {
        return commands;
    }

    @Override
    public SendMessage process(Update update) {
        long chatId = update.message().chat().id();
        String messageText = update.message().text().trim();

        for (Command command : commands) {
            if (command.supports(update)) {
                return command.handle(update);
            }
            else if (update.message().replyToMessage() != null) {
                return processReply(update);
            }
        }

        return new SendMessage(chatId, "Команды \"" + messageText + "\" не найдено. Для просмотра списка "
            + "доступных команд воспользуйтесь командой /help");
    }

    private SendMessage processReply(Update update) {
        if (update.message().replyToMessage().from().isBot()) {
            String messageText = update.message().replyToMessage().text();
            switch (messageText) {
                case TrackCommand.REPLY_TEXT -> {
                    Command track = new TrackCommand();
                    return track.handle(update);
                }
                case UntrackCommand.REPLY_TEXT -> {
                    Command untrack = new UntrackCommand();
                    return untrack.handle(update);
                }
                default -> {
                    return new SendMessage(update.message().chat().id(), "Пожалуйста, отправьте сообщение ответом"
                        + " на одно из сообщений бота");
                }
            }
        }
        return new SendMessage(update.message().chat().id(), "Произошла ошибка при обработке ответа");
    }
}
