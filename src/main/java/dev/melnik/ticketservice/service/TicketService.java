package dev.melnik.ticketservice.service;

import dev.melnik.ticketservice.data.Match;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface TicketService {
    void passLogin(RemoteWebDriver webDriver);
    void goToTicketsForMatch(RemoteWebDriver webDriver, Match match);
    boolean addTicketToCart(RemoteWebDriver webDriver);
}
