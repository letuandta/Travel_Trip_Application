package com.example.traveltripapplication.enumapp;

public enum LoginEnum {
    EMAIL(1),
    PHONE(2);

    private final int value;

    LoginEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
