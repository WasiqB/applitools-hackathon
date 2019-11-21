package com.applitools;

import static com.applitools.utils.DebugUtil.print;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.applitools.utils.TestCase;
import org.testng.annotations.DataProvider;

public final class LoginDataProvider {
    @DataProvider
    public static Iterator<Object[]> loginData () {
        print ("In @DataProvider (LoginDataProvider.loginData)...");
        final List<Object[]> data = new ArrayList<> ();
        data.add (new Object[] { new TestCase (1, null, null, "Both Username and Password must be present", false) });
        data.add (new Object[] { new TestCase (2, "abc", null, "Password must be present", false) });
        data.add (new Object[] { new TestCase (3, null, "abc", "Username must be present", false) });
        data.add (new Object[] { new TestCase (4, "abc", "abc", null, true) });
        return data.iterator ();
    }
}