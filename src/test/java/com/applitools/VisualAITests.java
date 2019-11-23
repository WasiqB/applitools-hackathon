package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V2;

import com.applitools.pages.CompareExpensePage;
import com.applitools.pages.DashboardPage;
import com.applitools.pages.LoginPage;
import com.applitools.utils.TestCase;
import org.testng.annotations.Test;

public class VisualAITests extends BaseVisualTest {
    @Test (groups = { APP_V1, APP_V2 }, description = "Canvas verification")
    public void testCanvasChart () {
        final DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.goToCompareExpenses ();

        this.eyes.check ("Canvas check for 2017-18");

        final CompareExpensePage expensePage = new CompareExpensePage (driverUtil);
        expensePage.addNextYearData ();

        this.eyes.check ("Canvas check for 2017-18-19");
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Ads content verification")
    public void testDynamicContent () {
        final DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifyAds (true);
        this.eyes.check ("Dashboard Ads check");
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Login Element verification")
    public void testLoginElements () {
        this.eyes.check ("Login page elements check");
    }

    @Test (description = "Login functionality test", dataProvider = "loginData", dataProviderClass = LoginDataProvider.class, groups = {
        APP_V1, APP_V2 })
    public void testLoginFunctionality (final TestCase testCase) {
        final LoginPage login = new LoginPage (driverUtil);
        login.login (testCase.getUserId (), testCase.getPassword ());
        this.eyes.check ("Login functionality check");
    }

    @Test (groups = { APP_V1, APP_V2 }, description = "Table sort verification")
    public void testTableSort () {
        final DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifySort (true);
        this.eyes.check ("Table sort check");
    }
}