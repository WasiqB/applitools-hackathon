package com.applitools.pages;

import com.applitools.utils.DriverUtil;
import com.applitools.utils.EyeUtils;

class BasePage {
    final DriverUtil driverUtil;
    final EyeUtils   eyeUtils;

    BasePage (final DriverUtil driverUtil) {
        this.driverUtil = driverUtil;
        this.eyeUtils = new EyeUtils (driverUtil);
    }
}