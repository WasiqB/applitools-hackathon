package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V2;

import com.applitools.pages.DashboardPage;
import com.applitools.pages.LoginPage;
import com.applitools.utils.TestCase;
import org.testng.annotations.Test;

public class TraditionalTests extends BaseTest {
    @Test (groups = { APP_V1, APP_V2 }, description = "Canvas verification")
    public void testCanvasChart () {
        // Can't automate canvas charts using traditional approach.
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Ads content verification")
    public void testDynamicContent () {
        final DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifyAds (false);
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Login Element verification")
    public void testLoginElements () {
        final LoginPage login = new LoginPage (driverUtil);
        login.verifyElements ();
    }

    @Test (description = "Login functionality test", dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = {
        APP_V1, APP_V2 })
    public void testLoginFunctionality (final TestCase testCase) {
        final LoginPage login = new LoginPage (driverUtil);
        login.login (testCase);
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Table sort verification")
    public void testTableSort () {
        final DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifySort (false);
    }
}