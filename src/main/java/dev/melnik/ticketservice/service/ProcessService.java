package dev.melnik.ticketservice.service;

import dev.melnik.ticketservice.data.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static dev.melnik.ticketservice.utils.PageUtils.createDriver;

@Service
@Async
@Slf4j
@RequiredArgsConstructor
public class ProcessService {
    private final TicketService ticketService;
    private final NotificationService notificationService;

    public void startSearching(Match match) {
        log.debug("Start process for searching tickets for: {}.", match.name());
        var driver = createDriver();
        try {
            ticketService.passLogin(driver);
            ticketService.goToTicketsForMatch(driver, match);
            boolean ticketFound = ticketService.addTicketToCart(driver);
            if (ticketFound) {
                log.debug("Found the TICKET FOR {}!", match.name());
                notificationService.sendNotification(match);
            } else {
                //logic when session expired - close the window and start new process
                driver.quit();
            }
        } catch (Exception ex) {
            log.error("Error:", ex);
            driver.quit();
        }
    }
}
