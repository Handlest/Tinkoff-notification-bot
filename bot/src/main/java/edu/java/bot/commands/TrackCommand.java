package edu.java.bot.commands;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class TrackCommand implements Command {
    public final static String REPLY_TEXT = "Отправьте ответом на это сообщение ссылку, которую вы хотите отслеживать" +
        "\nПоддерживаются GitHub и StackOverFlow";

    @Override
    public String command() {
        return CommandsEnum.TRACK.getCommand();
    }

    @Override
    public String description() {
        return CommandsEnum.TRACK.getDescription();
    }

    @Override
    public SendMessage handle(Update update) {
        long chatId = update.message().chat().id();
        String link = update.message().text().trim();
        if (isReply(update)) {
            return new SendMessage(chatId, "Ссылка \"" + link + "\" успешно добавлена в отслеживание");
        }
        return new SendMessage(chatId, REPLY_TEXT).replyMarkup(new ForceReply());
    }

    private boolean isReply(Update update) {
        return update.message().replyToMessage() != null &&
            update.message().replyToMessage().text().equals(REPLY_TEXT);
    }
}
