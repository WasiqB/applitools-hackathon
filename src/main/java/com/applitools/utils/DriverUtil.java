package com.applitools.utils;

import static com.applitools.eyes.selenium.StitchMode.CSS;
import static com.applitools.utils.ConfigUtil.getConfig;
import static com.applitools.utils.Constants.EYE_API;
import static com.applitools.utils.Constants.WAIT_EXPLICIT;
import static com.google.common.truth.Truth.assertWithMessage;
import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import com.google.common.truth.StringSubject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtil {
    private final WebDriver     driver;
    private final Eyes          eyes;
    private final WebDriverWait wait;

    public DriverUtil (final WebDriver driver) {
        this.driver = driver;
        final EyesRunner runner = new ClassicRunner ();
        this.eyes = new Eyes (runner);
        setEyeOptions ();
        this.wait = new WebDriverWait (driver, parseInt (getConfig (WAIT_EXPLICIT)));
    }

    public void close () {
        perform (WebDriver::close);
    }

    public TestResults closeEye () {
        if (this.eyes.getIsOpen ()) {
            return this.eyes.close ();
        }
        return null;
    }

    public ElementUtil find (final By locator) {
        return new ElementUtil (this.wait.until (visibilityOfElementLocated (locator)));
    }

    public List<ElementUtil> finds (final By locator) {
        return this.wait.until (visibilityOfAllElementsLocatedBy (locator))
            .stream ()
            .map (ElementUtil::new)
            .collect (Collectors.toList ());
    }

    public void navigate (final String url) {
        perform (d -> d.get (url));
    }

    public void openEye (final String testName, final String pageName) {
        this.eyes.open (this.driver, "Applitools Hackathon", testName);
        this.eyes.checkWindow (pageName);
    }

    public void quit () {
        perform (WebDriver::quit);
        this.eyes.abortIfNotClosed ();
    }

    public StringSubject verifyUrl () {
        return assertWithMessage ("Page URL mismatched.").that (get (WebDriver::getCurrentUrl));
    }

    private <T> T get (final Function<WebDriver, T> action) {
        return action.apply (this.driver);
    }

    private void perform (final Consumer<WebDriver> action) {
        action.accept (this.driver);
    }

    private void setEyeOptions () {
        this.eyes.setApiKey (getConfig (EYE_API));
        this.eyes.setBatch (new BatchInfo ("Hackathon"));
        this.eyes.setForceFullPageScreenshot (true);
        this.eyes.setHideScrollbars (true);
        this.eyes.setStitchMode (CSS);
    }
}