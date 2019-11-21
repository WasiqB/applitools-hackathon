package com.applitools.utils;

public class TestCase {
    private final int     id;
    private final String  message;
    private final String  password;
    private final String  userId;
    private final boolean valid;

    public TestCase (final int id, final String userId, final String password, final String message,
        final boolean valid) {
        this.id = id;
        this.message = message;
        this.password = password;
        this.userId = userId;
        this.valid = valid;
    }

    public int getId () {
        return this.id;
    }

    public String getMessage () {
        return this.message;
    }

    public String getPassword () {
        return this.password;
    }

    public String getUserId () {
        return this.userId;
    }

    public boolean isValid () {
        return this.valid;
    }
}
