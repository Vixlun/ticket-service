package dev.melnik.ticketservice.data;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TicketNotificationBot extends TelegramLongPollingBot {
    private final String botUserName;

    public TicketNotificationBot(String botUserName, String botToken) {
        super(botToken);
        this.botUserName = botUserName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // don't handle messages
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }
}
