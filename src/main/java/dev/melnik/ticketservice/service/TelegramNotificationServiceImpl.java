package dev.melnik.ticketservice.service;

import dev.melnik.ticketservice.data.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramNotificationServiceImpl implements NotificationService {
    private final TelegramLongPollingBot telegramBot;

    @Value("${notification.chatid}")
    private String chatId;

    @Override
    public void sendNotification(Match match) {
        try {
            telegramBot.execute(SendMessage.builder()
                    .text("Found the ticket for match: " + match.name())
                    .chatId(chatId)
                    .build());

        } catch (Exception ex) {
            log.error("", ex);
        }
    }
}
