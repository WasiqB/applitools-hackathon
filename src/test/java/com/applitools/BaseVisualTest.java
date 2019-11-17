package com.applitools;

import static com.applitools.eyes.selenium.StitchMode.CSS;
import static com.applitools.utils.ConfigUtil.getConfig;
import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.Constants.EYE_API;
import static com.applitools.utils.DebugUtil.print;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.lang.reflect.Method;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseVisualTest extends BaseTest {
    private static final BatchInfo  BATCH_DP = new BatchInfo ("Hackathon");
    private              int        count;
    private              Eyes       eyes;
    //    private     EyeUtils   eyes;
    private              EyesRunner runner;

    @AfterMethod (groups = APP_V1)
    public void closeVisualTestsV1 () {
        print ("In @AfterMethod (BaseVisualTest.closeVisualTestsV1)...");
        this.eyes.closeAsync ();
    }

    @AfterMethod (groups = APP_V2)
    public void closeVisualTestsV2 () {
        print ("In @AfterMethod (BaseVisualTest.closeVisualTestsV2)...");
        this.eyes.closeAsync ();
    }

    @BeforeMethod (groups = APP_V1)
    public void prepareV1VisualTests () {
        print ("In @BeforeMethod (BaseVisualTest.prepareV1VisualTests)...");
        //        this.eyes.setSaveFailedTests (true);
    }

    @BeforeMethod (groups = APP_V2)
    public void prepareV2VisualTests () {
        print ("In @BeforeMethod (BaseVisualTest.prepareV2VisualTests)...");
        //        this.eyes.setSaveFailedTests (false);
    }

    @BeforeMethod (alwaysRun = true)
    public void prepareVisualTests (final Method testMethod) {
        final Test testAnnotation = testMethod.getAnnotation (Test.class);
        String description = testAnnotation.description ();
        final boolean withBatch = isNotEmpty (testAnnotation.dataProvider ());
        if (withBatch) {
            this.count++;
            this.eyes.setBatch (BATCH_DP);
            description = format ("{0}-{1}", description, this.count);
        }
        print ("In @BeforeMethod (BaseVisualTest.prepareVisualTests): " + description);
        //        this.eyes.open (description, withBatch);
        this.eyes.open (this.driverUtil.driver (), "Applitools Hackathon", description);
    }

    @BeforeClass (alwaysRun = true)
    public void setupVisualTests () {
        print ("In @BeforeClass (BaseVisualTest.setupVisualTests)...");
        this.runner = new ClassicRunner ();
        this.eyes = new Eyes (this.runner);
        setEyeOptions ();
        //        this.eyes = new EyeUtils (this.driverUtil);
        this.count = 0;
    }

    @AfterClass (alwaysRun = true)
    public void teardownVisualTests () {
        print ("In @AfterClass (BaseVisualTest.teardownVisualTests)...");
        this.eyes.abortIfNotClosed ();

        final TestResultsSummary allTestResults = this.runner.getAllTestResults ();
        print (allTestResults.toString ());
        //        this.eyes.quit ();
    }

    private void setEyeOptions () {
        this.eyes.setApiKey (getConfig (EYE_API));
        this.eyes.setForceFullPageScreenshot (true);
        this.eyes.setHideScrollbars (true);
        this.eyes.setStitchMode (CSS);
        this.eyes.setExplicitViewportSize (new RectangleSize (1366, 589));
        this.eyes.setBaselineEnvName ("Applitools Hackathon");
    }
}
