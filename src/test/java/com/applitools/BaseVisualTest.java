package com.applitools;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.lang.reflect.Method;

import com.applitools.utils.EyeUtils;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseVisualTest extends BaseTest {
    EyeUtils eyes;
    private int count;

    @AfterMethod (alwaysRun = true)
    public void closeVisualTests () {
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
        this.eyes.open (description);
    }

    @BeforeTest (alwaysRun = true)
    public void setupVisualTests (final ITestContext context) {
        this.eyes = new EyeUtils (driverUtil);
        this.count = 0;
    }

    @AfterTest (alwaysRun = true)
    public void teardownVisualTests () {
        this.eyes.quit ();
    }
}