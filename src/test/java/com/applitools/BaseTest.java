package com.applitools;

import static com.applitools.utils.ConfigUtil.getConfig;
import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V1_AD;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.Constants.APP_V2_AD;
import static com.applitools.utils.Constants.TO_PAGE;
import static com.applitools.utils.Constants.TO_SCRIPT;
import static com.applitools.utils.Constants.WAIT_IMPLICIT;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.Integer.parseInt;
import static java.text.MessageFormat.format;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.applitools.utils.DriverUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    private static final String AD_URL = "?showAd=true";
    DriverUtil driverUtil;

    @BeforeTest (alwaysRun = true)
    public void setupTest () {
        chromedriver ().setup ();
        final WebDriver driver = new ChromeDriver ();
        setupDriver (driver);
        this.driverUtil = new DriverUtil (driver);
    }

    @BeforeMethod (groups = APP_V1)
    public void setupTestMethodV1 () {
        this.driverUtil.navigate (getConfig (APP_V1));
    }

    @BeforeMethod (groups = APP_V1_AD)
    public void setupTestMethodV1WithAd () {
        this.driverUtil.navigate (format ("{0}{1}", getConfig (APP_V1), AD_URL));
    }

    @BeforeMethod (groups = APP_V2)
    public void setupTestMethodV2 () {
        this.driverUtil.navigate (getConfig (APP_V2));
    }

    @BeforeMethod (groups = APP_V2_AD)
    public void setupTestMethodV2WithAd () {
        this.driverUtil.navigate (format ("{0}{1}", getConfig (APP_V2), AD_URL));
    }

    @AfterTest (alwaysRun = true)
    public void teardownTest () {
        this.driverUtil.close ();
        this.driverUtil.quit ();
    }

    private void setupDriver (final WebDriver driver) {
        final WebDriver.Timeouts timeout = driver.manage ()
            .timeouts ();
        timeout.implicitlyWait (parseInt (getConfig (WAIT_IMPLICIT)), SECONDS);
        timeout.pageLoadTimeout (parseInt (getConfig (TO_PAGE)), SECONDS);
        timeout.setScriptTimeout (parseInt (getConfig (TO_SCRIPT)), SECONDS);
        final WebDriver.Window window = driver.manage ()
            .window ();
        window.setSize (new Dimension (1366, 1024));
        window.setPosition (new Point (0, 0));
    }
}