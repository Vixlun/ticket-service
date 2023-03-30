package dev.melnik.ticketservice.utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@UtilityClass
public class PageUtils {
    public final static String LOGIN_BUTTON_NAME = "login";
    public final static String USERNAME_ID = "username";
    public final static String PASSWORD_ID = "password";
    public final static String RETURN_BUTTON = "ctl00_ContentMiddle_SessionTimeoutInfo1_ToEventList";
    public final static String COOKIES_VIEW_ID = "usercentrics-root";
    public final static String COOKIES_BUTTON = ".sc-eDvSVe.knSBZX";

    public final static String CART_BUTTON_ID = "ctl00_ContentMiddle_TicketList1_GridView1_ctl03_LinkButton1";
    public final static String SECOND_CART_BUTTON_ID = "ctl00_ContentMiddle_TicketList1_GridView1_ctl04_LinkButton1";
    public final static String EXPIRED_SESSION_ID = "ctl00_ContentMiddle_SessionTimeoutInfo1_Info";
    public final static String ERROR_CODE = "403";

    public static RemoteWebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        return new ChromeDriver(options);
    }
}
