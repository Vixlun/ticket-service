package dev.melnik.ticketservice.service;

import dev.melnik.ticketservice.data.Match;

public interface NotificationService {
    void sendNotification(Match match);
}
