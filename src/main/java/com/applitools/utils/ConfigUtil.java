package com.applitools.utils;

import static com.applitools.utils.Constants.CONFIG_PATH;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties CONFIG;

    static {
        final String dir = getProperty ("user.dir");
        final String configPath = format ("{0}/src/test/resources/{1}", dir, CONFIG_PATH);
        CONFIG = new Properties ();
        try (final FileInputStream in = new FileInputStream (new File (configPath))) {
            CONFIG.load (in);
        } catch (final IOException e) {
            e.printStackTrace ();
        }
    }

    public static String getConfig (final String key) {
        return CONFIG.getProperty (key);
    }
}