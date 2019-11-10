package com.applitools.pages;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import com.applitools.utils.DriverUtil;
import com.applitools.utils.ElementUtil;

public class DashboardPage extends BasePage {
    private static final String URL = "/hackathonApp";

    public DashboardPage (final DriverUtil driverUtil) {
        super (driverUtil);
    }

    public void verifyAds () {
        login ();
        assertWithMessage ("2 Ads should be displayed.").that (ads ().size ())
            .isEqualTo (2);
    }

    public void verifySort () {
        login ();
        amountHeader ().click ();
        final List<Integer> actualAmounts = expectedAmounts ();
        assertThat (actualAmounts).isInOrder ();
    }

    void atPage () {
        this.driverUtil.verifyUrl ()
            .contains (URL);
        timeHeader ().verifyText ()
            .startsWith ("Your nearest branch closes in");
    }

    private List<ElementUtil> ads () {
        return this.driverUtil.finds (cssSelector ("div[id^='flash'] img"))
            .stream ()
            .filter (ElementUtil::isVisible)
            .collect (toList ());
    }

    private ElementUtil amountHeader () {
        return this.driverUtil.find (id ("amount"));
    }

    private List<ElementUtil> amounts () {
        return this.driverUtil.finds (cssSelector ("table#transactionsTable tbody tr td:last-child"));
    }

    private List<Integer> expectedAmounts () {
        return amounts ().stream ()
            .map (ElementUtil::text)
            .map (s -> {
                try {
                    DecimalFormat format = new DecimalFormat ("+ #,##0.00 USD;- #,##0.00 USD");
                    return format.parse (s)
                        .intValue ();
                } catch (ParseException e) {
                    throw new RuntimeException (e);
                }
            })
            .collect (toList ());
    }

    private void login () {
        final LoginPage login = new LoginPage (this.driverUtil);
        login.login ("abc", "abc", null, true);
    }

    private ElementUtil timeHeader () {
        return this.driverUtil.find (id ("time"));
    }
}