package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    private String text;

    private void setHelpText() {
        StringBuilder sbText = new StringBuilder();
        for (CommandsEnum command : CommandsEnum.values()) {
            sbText.append(command.getCommand()).append(" - ").append(command.getDescription()).append("\n");
        }
        text = sbText.toString();
    }

    private String getOutputText() {
        return text;
    }

    @Override
    public String command() {
        return CommandsEnum.HELP.getCommand();
    }

    @Override
    public String description() {
        return CommandsEnum.HELP.getDescription();
    }

    @Override
    public SendMessage handle(Update update) {
        setHelpText();
        return new SendMessage(update.message().chat().id(), getOutputText());
    }
}
