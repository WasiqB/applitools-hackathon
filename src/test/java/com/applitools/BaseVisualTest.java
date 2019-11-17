package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.DebugUtil.print;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.lang.reflect.Method;

import com.applitools.utils.EyeUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseVisualTest extends BaseTest {
    private int      count;
    private EyeUtils eyes;

    @AfterMethod (groups = { APP_V1, APP_V2 })
    public void closeVisualTests () {
        print ("In @AfterMethod (BaseVisualTest.closeVisualTestsV1)...");
        this.eyes.close ();
    }

    @BeforeMethod (alwaysRun = true)
    public void prepareVisualTests (final Method testMethod) {
        final Test testAnnotation = testMethod.getAnnotation (Test.class);
        String description = testAnnotation.description ();
        if (isNotEmpty (testAnnotation.dataProvider ())) {
            this.count++;
            description = format ("{0}-{1}", description, this.count);
        }
        print ("In @BeforeMethod (BaseVisualTest.prepareVisualTests): " + description);
        this.eyes.open (description);
    }

    @BeforeClass (alwaysRun = true)
    public void setupVisualTests () {
        print ("In @BeforeClass (BaseVisualTest.setupVisualTests)...");
        this.eyes = new EyeUtils (this.driverUtil);
        this.count = 0;
    }

    @AfterClass (alwaysRun = true)
    public void teardownVisualTests () {
        print ("In @AfterClass (BaseVisualTest.teardownVisualTests)...");
        this.eyes.quit ();
    }
}
