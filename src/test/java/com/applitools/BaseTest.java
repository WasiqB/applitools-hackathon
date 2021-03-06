package com.applitools;

import static com.applitools.utils.ConfigUtil.getConfig;
import static com.applitools.utils.Constants.TO_PAGE;
import static com.applitools.utils.Constants.TO_SCRIPT;
import static com.applitools.utils.Constants.WAIT_IMPLICIT;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.lang.Integer.parseInt;
import static java.text.MessageFormat.format;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.lang.reflect.Method;

import com.applitools.utils.DriverUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

public class BaseTest {
    static               DriverUtil driverUtil;
    private static final String     AD_URL = "?showAd=true";

    @BeforeTest (alwaysRun = true)
    public void setupTest () {
        chromedriver ().setup ();
        final WebDriver driver = new ChromeDriver ();
        setupDriver (driver);
        driverUtil = new DriverUtil (driver);
    }

    @BeforeMethod (alwaysRun = true)
    public void setupTestMethod (final XmlTest test, final Method method) {
        final String group = test.getIncludedGroups ()
            .get (0);
        String url = getConfig (group);
        final String name = method.getName ();
        if (name.equalsIgnoreCase ("testDynamicContent")) {
            url = format ("{0}{1}", url, AD_URL);
        }
        driverUtil.navigate (url);
    }

    @AfterTest (alwaysRun = true)
    public void teardownTest () {
        driverUtil.close ();
        driverUtil.quit ();
    }

    private void setupDriver (final WebDriver driver) {
        final WebDriver.Timeouts timeout = driver.manage ()
            .timeouts ();
        timeout.implicitlyWait (parseInt (getConfig (WAIT_IMPLICIT)), SECONDS);
        timeout.pageLoadTimeout (parseInt (getConfig (TO_PAGE)), SECONDS);
        timeout.setScriptTimeout (parseInt (getConfig (TO_SCRIPT)), SECONDS);
        final WebDriver.Window window = driver.manage ()
            .window ();
        window.setSize (new Dimension (1366, 789));
        window.setPosition (new Point (0, 0));
    }
}