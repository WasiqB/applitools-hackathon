package com.applitools.utils;

import static com.applitools.eyes.selenium.StitchMode.CSS;
import static com.applitools.utils.ConfigUtil.getConfig;
import static com.applitools.utils.Constants.EYE_API;
import static com.applitools.utils.DebugUtil.print;
import static java.util.Objects.requireNonNull;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.Dimension;

public class EyeUtils {
    private static final BatchInfo BATCH_DP = new BatchInfo ("Hackathon");

    private final DriverUtil driverUtil;
    private final Eyes       eyes;
    private final EyesRunner runner;

    public EyeUtils (final DriverUtil driverUtil) {
        this.driverUtil = requireNonNull (driverUtil, "DriverUtil can't be null");
        this.runner = new ClassicRunner ();
        this.eyes = new Eyes (this.runner);
        setEyeOptions ();
    }

    public void check (final String tag) {
        this.eyes.checkWindow (tag);
    }

    public void close () {
        this.eyes.closeAsync ();
    }

    public void open (final String description) {
        this.eyes.open (requireNonNull (this.driverUtil.driver (), "Driver can't be null"), "Applitools Hackathon",
            description);
    }

    public void quit () {
        this.eyes.abortIfNotClosed ();
        final TestResultsSummary allTestResults = this.runner.getAllTestResults ();
        print (allTestResults.toString ());
    }

    private void setEyeOptions () {
        this.eyes.setApiKey (getConfig (EYE_API));
        this.eyes.setBatch (BATCH_DP);
        this.eyes.setForceFullPageScreenshot (true);
        this.eyes.setHideScrollbars (true);
        this.eyes.setStitchMode (CSS);
        final Dimension size = this.driverUtil.driver ()
            .manage ()
            .window ()
            .getSize ();
        this.eyes.setExplicitViewportSize (new RectangleSize (size.getWidth (), size.getHeight ()));
        this.eyes.setBaselineEnvName ("Applitools Hackathon");
    }
}