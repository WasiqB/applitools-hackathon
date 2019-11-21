package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V1_AD;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.Constants.APP_V2_AD;
import static com.applitools.utils.DebugUtil.print;

import com.applitools.pages.DashboardPage;
import com.applitools.pages.LoginPage;
import com.applitools.utils.TestCase;
import org.testng.annotations.Test;

public class TraditionalTests extends BaseTest {
    @Test (groups = { APP_V1, APP_V2 })
    public void testCanvasChart () {
        print ("In @Test (TraditionalTests.testCanvasChart)...");
        // Can't automate canvas charts using traditional approach.
    }

    @Test (groups = { APP_V1_AD, APP_V2_AD })
    public void testDynamicContent () {
        print ("In @Test (TraditionalTests.testDynamicContent)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifyAds (false);
    }

    @Test (groups = { APP_V1, APP_V2 })
    public void testLoginElements () {
        print ("In @Test (TraditionalTests.testLoginElements)...");
        final LoginPage login = new LoginPage (this.driverUtil);
        login.verifyElements ();
    }

    @Test (dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = { APP_V1, APP_V2 })
    public void testLoginFunctionality (final TestCase testCase) {
        print ("In @Test (TraditionalTests.testLoginFunctionality)...");
        final LoginPage login = new LoginPage (this.driverUtil);
        login.login (testCase);
    }

    @Test (groups = { APP_V1, APP_V2 })
    public void testTableSort () {
        print ("In @Test (TraditionalTests.testTableSort)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifySort (false);
    }
}