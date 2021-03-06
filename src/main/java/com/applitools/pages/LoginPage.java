package com.applitools.pages;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import java.util.List;

import com.applitools.utils.DriverUtil;
import com.applitools.utils.ElementUtil;
import com.applitools.utils.TestCase;
import com.google.common.truth.StringSubject;

public class LoginPage extends BasePage {
    public LoginPage (final DriverUtil driverUtil) {
        super (driverUtil);
    }

    public void login (final String userId, final String password) {
        userName ().enterText (userId);
        password ().enterText (password);
        login ().click ();
    }

    public void login (final TestCase testCase) {
        login (testCase.getUserId (), testCase.getPassword ());
        if (!testCase.isValid ()) {
            message ().verifyText ()
                .isEqualTo (testCase.getMessage ());
        } else {
            final DashboardPage dashboardPage = new DashboardPage (this.driverUtil);
            dashboardPage.atPage ();
        }
    }

    public void verifyElements () {
        verifyIcons ();
        verifyHeader ();
        verifyForm ();
    }

    private List<ElementUtil> formIcons () {
        return this.driverUtil.finds (cssSelector ("div.pre-icon"));
    }

    private ElementUtil header () {
        return this.driverUtil.find (cssSelector ("h4.auth-header"));
    }

    private List<ElementUtil> icons () {
        return this.driverUtil.finds (cssSelector ("div > a > img"));
    }

    private ElementUtil login () {
        return this.driverUtil.find (id ("log-in"));
    }

    private ElementUtil message () {
        return this.driverUtil.find (cssSelector ("div.alert.alert-warning"));
    }

    private ElementUtil password () {
        return this.driverUtil.find (id ("password"));
    }

    private ElementUtil passwordLabel () {
        return this.driverUtil.find (cssSelector ("div.form-group:nth-child(2) label"));
    }

    private ElementUtil rememberMe () {
        return this.driverUtil.find (className ("form-check-label"));
    }

    private ElementUtil userName () {
        return this.driverUtil.find (id ("username"));
    }

    private ElementUtil userNameLabel () {
        return this.driverUtil.find (cssSelector ("div.form-group:first-child label"));
    }

    private void verifyForm () {
        final String[] expectedIcons = new String[] { "user-male-circle", "fingerprint" };
        for (int index = 0; index < formIcons ().size (); index++) {
            final ElementUtil icon = formIcons ().get (index);
            icon.verifyAttr ("class")
                .endsWith (expectedIcons[index]);
        }
        userNameLabel ().verifyText ()
            .isEqualTo ("Username");
        userName ().verifyAttr ("placeholder")
            .isEqualTo ("Enter your username");

        passwordLabel ().verifyText ()
            .isEqualTo ("Password");
        password ().verifyAttr ("placeholder")
            .isEqualTo ("Enter your password");

        login ().verifyText ()
            .isEqualTo ("Log In");
        rememberMe ().verifyText ()
            .isEqualTo ("Remember Me");
    }

    private void verifyHeader () {
        header ().verifyText ()
            .isEqualTo ("Login Form");
    }

    private void verifyIcons () {
        for (int index = 0; index < icons ().size (); index++) {
            final ElementUtil icon = icons ().get (index);
            final StringSubject src = icon.verifyAttr ("src");
            switch (index) {
                case 0:
                    src.contains ("logo");
                    break;
                case 1:
                    src.contains ("twitter");
                    break;
                case 2:
                    src.contains ("facebook");
                    break;
                default:
                    src.contains ("linkedin");
                    break;
            }
        }
    }
}