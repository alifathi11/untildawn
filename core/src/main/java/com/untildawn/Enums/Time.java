package com.untildawn.Enums;

public enum Time {
    TIME_2_MINUTES(120),
    TIME_5_MINUTES(300),
    TIME_10_MINUTES(600),
    TIME_20_MINUTES(1200),
    ;

    private final int time;

    Time(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
