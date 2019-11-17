package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V1_AD;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.Constants.APP_V2_AD;
import static com.applitools.utils.DebugUtil.print;

import com.applitools.pages.DashboardPage;
import com.applitools.pages.LoginPage;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class VisualAITests extends BaseVisualTest {
    @Test (groups = { APP_V1, APP_V2 })
    public void testCanvasChart () {
        print ("In @Test (VisualAITests.testCanvasChart)...");
        // Can't automate canvas charts using traditional approach.
    }

    @Ignore
    @Test (groups = { APP_V1_AD, APP_V2_AD })
    public void testDynamicContent () {
        print ("In @Test (VisualAITests.testDynamicContent)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifyAds (true);
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Login Element verification")
    public void testLoginElements () {
        print ("In @Test (VisualAITests.testLoginElements)...");
        final LoginPage login = new LoginPage (this.driverUtil);
        login.verifyElements (true);
    }

    @Ignore
    @Test (dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = { APP_V1, APP_V2 })
    public void testLoginFunctionality (final String userId, final String password, final String expectedMessage,
        final boolean isValid) {
        print ("In @Test (VisualAITests.testLoginFunctionality)...");
        final LoginPage login = new LoginPage (this.driverUtil);
        login.login (userId, password, expectedMessage, isValid, true);
    }

    @Ignore
    @Test (groups = { APP_V1, APP_V2 })
    public void testTableSort () {
        print ("In @Test (VisualAITests.testTableSort)...");
        final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
        dashboardPage.verifySort (true);
    }
}