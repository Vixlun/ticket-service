package dev.melnik.ticketservice.service;

import dev.melnik.ticketservice.data.Match;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static dev.melnik.ticketservice.utils.PageUtils.*;
import static org.openqa.selenium.By.cssSelector;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    private final String userName;
    private final String userPassword;
    private final String loginPageUrl;


    public TicketServiceImpl(@Value("${login.page.url}") String loginPageUrl,
                             @Value("${login.user}") String userName,
                             @Value("${login.password}") String userPassword)
    {
        this.loginPageUrl = loginPageUrl;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public void passLogin(RemoteWebDriver webDriver) {
        webDriver.get(loginPageUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.findElement(By.id(COOKIES_VIEW_ID)).getShadowRoot()
                .findElement(cssSelector(COOKIES_BUTTON)).click();

        webDriver.findElement(By.id(USERNAME_ID)).sendKeys(userName);
        webDriver.findElement(By.id(PASSWORD_ID)).sendKeys(userPassword);
        webDriver.findElement(By.name(LOGIN_BUTTON_NAME)).click();

        webDriver.findElement(By.id(RETURN_BUTTON)).click();
    }

    @Override
    public void goToTicketsForMatch(RemoteWebDriver webDriver, Match match) {
        webDriver.findElement(By.id(match.id)).click();
    }

    @Override
    public boolean addTicketToCart(RemoteWebDriver webDriver) {
        while (webDriver.findElements(By.id(CART_BUTTON_ID)).isEmpty()) {
            if (!webDriver.findElements(By.id(EXPIRED_SESSION_ID)).isEmpty()) {
                log.warn("Session was expired");
                webDriver.findElement(By.id(RETURN_BUTTON)).click();
                return false;
            }

            webDriver.navigate().refresh();
        }

        webDriver.findElement(By.id(CART_BUTTON_ID)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.id(CART_BUTTON_ID))).click();


        // handle errors
        LogEntries logEntries = webDriver.manage().logs().get(LogType.BROWSER);
        for (LogEntry browserLog : logEntries) {
            if(browserLog.getMessage().contains(ERROR_CODE)) {
                log.warn("Error from browser {}", browserLog);
                webDriver.findElement(By.id(CART_BUTTON_ID)).click();
            }
        }
        return true;
    }

}
