package com.applitools.pages;

import static org.openqa.selenium.By.id;

import com.applitools.utils.DriverUtil;
import com.applitools.utils.ElementUtil;

public class CompareExpensePage extends BasePage {
    public CompareExpensePage (final DriverUtil driverUtil) {
        super (driverUtil);
    }

    public void addNextYearData () {
        showNextYearData ().click ();
    }

    private ElementUtil showNextYearData () {
        return this.driverUtil.find (id ("addDataset"));
    }
}