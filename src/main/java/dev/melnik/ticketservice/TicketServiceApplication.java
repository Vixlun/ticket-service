package dev.melnik.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class TicketServiceApplication {

    public static void main(String[] args) {
        //driver location
        System.setProperty("webdriver.chrome.driver","/Users/lmelnik/workspaces/selenium/chromedriver");
        SpringApplication.run(TicketServiceApplication.class, args);
    }

}
