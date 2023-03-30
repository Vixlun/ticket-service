package dev.melnik.ticketservice;

import dev.melnik.ticketservice.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static dev.melnik.ticketservice.data.Match.DORTMUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final ProcessService processService;

    @Scheduled(fixedDelay = 35, timeUnit = TimeUnit.SECONDS)
    public void dortmundScheduler() {
        log.debug("Invoking dortmundScheduler");
        processService.startSearching(DORTMUND);
        log.debug("Successfully invoked dortmundScheduler.");
    }

//    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
//    public void manchesterScheduler() {
//        log.debug("Invoking manchesterScheduler");
//        processService.startSearching(DORTMUND);
//        log.debug("Successfully invoked manchesterScheduler.");
//    }

}

