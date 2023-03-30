package dev.melnik.ticketservice.config;

import dev.melnik.ticketservice.data.TicketNotificationBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class NotificationServiceConfiguration {

    @Bean
    public TelegramLongPollingBot telegramBot(@Value("${bot.username}") String botUserName,
                                              @Value("${bot.token}") String botToken) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            var ticketNotificationBot = new TicketNotificationBot(botUserName, botToken);
            botsApi.registerBot(ticketNotificationBot);

            return ticketNotificationBot;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't create telegram bot");
        }
    }

}
