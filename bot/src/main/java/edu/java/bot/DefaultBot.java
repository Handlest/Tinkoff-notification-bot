//package edu.java.bot;
//
//import com.pengrad.telegrambot.TelegramBot;
//import com.pengrad.telegrambot.model.Update;
//import com.pengrad.telegrambot.request.BaseRequest;
//import com.pengrad.telegrambot.response.BaseResponse;
//import edu.java.bot.configuration.ApplicationConfig;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import java.util.List;
//
//public class DefaultBot implements Bot {
//    private static final Logger LOGGER = LogManager.getLogger();
//    private final String token;
//    private TelegramBot bot;
//
//
//    public DefaultBot(ApplicationConfig config) {
//        token = config.telegramToken();
//    }
//
//    @Override
//    public int process(List<Update> updates) {
//        return 0;
//    }
//
//    @Override
//    @EventListener(ApplicationReadyEvent.class)
//    public void start() {
//        bot = new TelegramBot(token);
//        bot.setUpdatesListener(this);
//        LOGGER.debug("Bot started");
//    }
//
//    @Override
//    public void close() {
//
//    }
//}
