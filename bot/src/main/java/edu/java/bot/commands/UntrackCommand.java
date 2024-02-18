package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class UntrackCommand implements Command {
    public final static String REPLY_TEXT = "Отправьте ответом на данное сообщение ссылку, которую вы хотите удалить"
        + " из отслеживания";

    @Override
    public String command() {
        return CommandsEnum.UNTRACK.getCommand();
    }

    @Override
    public String description() {
        return CommandsEnum.UNTRACK.getDescription();
    }

    @Override
    public boolean supports(Update update) {
        return update.message().text().trim().equals(command()) || isReply(update);
    }

    @Override
    public SendMessage handle(Update update) {
        int chatMessageId = update.message().messageId();
        long chatId = update.message().chat().id();
        String link = update.message().text().trim();

        if (isReply(update)) {
            return new SendMessage(chatId, "Теперь ссылка \"" + link + "\" больше не отслеживается");
        } else {
            return new SendMessage(chatId, REPLY_TEXT).replyMarkup(new ForceReply()).replyToMessageId(chatMessageId);
        }
    }

    private boolean isReply(Update update) {
        return update.message().replyToMessage() != null && update.message().replyToMessage().text().equals(REPLY_TEXT);
    }
}
