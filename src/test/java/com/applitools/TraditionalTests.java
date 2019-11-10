package com.applitools;

import static com.applitools.utils.Constants.APP_V1;
import static com.applitools.utils.Constants.APP_V1_AD;
import static com.applitools.utils.Constants.APP_V2;
import static com.applitools.utils.Constants.APP_V2_AD;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.applitools.pages.DashboardPage;
import com.applitools.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TraditionalTests extends BaseTest {
    @DataProvider
    public Iterator<Object[]> loginData () {
        List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { null, null, "Both Username and Password must be present", false });
        data.add (new Object[] { "abc", null, "Password must be present", false });
        data.add (new Object[] { null, "abc", "Username must be present", false });
        data.add (new Object[] { "abc", "abc", null, true });
        return data.iterator ();
    }

    @Test (groups = { APP_V1, APP_V2 })
    public void testLoginElements () {
        LoginPage login = new LoginPage (driverUtil);
        login.verifyElements ();
    }

    @Test (dataProvider = "loginData", groups = { APP_V1, APP_V2 })
    public void testLoginFunctionality (String userId, String password, String expectedMessage, boolean isValid) {
        LoginPage login = new LoginPage (driverUtil);
        login.login (userId, password, expectedMessage, isValid);
    }

    @Test (groups = { APP_V1, APP_V2 })
    public void testTableSort () {
        DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifySort ();
    }

    @Test (groups = { APP_V1, APP_V2 })
    public void testCanvasChart () {
        // Can't automate canvas charts using traditional approach.
    }

    @Test (groups = { APP_V1_AD, APP_V2_AD })
    public void testDynamicContent () {
        DashboardPage dashboardPage = new DashboardPage (driverUtil);
        dashboardPage.verifyAds ();
    }
}