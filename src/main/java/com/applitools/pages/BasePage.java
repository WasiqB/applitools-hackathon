package com.applitools.pages;

import com.applitools.utils.DriverUtil;

class BasePage {
    final DriverUtil driverUtil;

    BasePage (final DriverUtil driverUtil) {
        this.driverUtil = driverUtil;
    }
}