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

public class VisualAITests extends BaseVisualTest {
    @Test (groups = { APP_V1, APP_V2 }, description = "Canvas verification")
    public void testCanvasChart () {
        print ("In @Test (VisualAITests.testCanvasChart)...");
        // Can't automate canvas charts using traditional approach.
    }

    @Test (groups = { APP_V1_AD, APP_V2_AD }, description = "Ads content verification")
    public void testDynamicContent () {
        print ("In @Test (VisualAITests.testDynamicContent)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifyAds (true);
        this.eyes.check ("Dashboard Ads");
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Login Element verification")
    public void testLoginElements () {
        print ("In @Test (VisualAITests.testLoginElements)...");
        this.eyes.check ("Login page");
    }

    @Test (description = "Login functionality test", dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = {
        APP_V1, APP_V2 })
    public void testLoginFunctionality (final TestCase testCase) {
        print ("In @Test (VisualAITests.testLoginFunctionality)...");
        final LoginPage login = new LoginPage (this.driverUtil);
        login.login (testCase.getUserId (), testCase.getPassword ());
        this.eyes.check ("Login check");
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Table sort verification")
    public void testTableSort () {
        print ("In @Test (VisualAITests.testTableSort)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifySort (true);
        this.eyes.check ("Table sort check");
    }
}