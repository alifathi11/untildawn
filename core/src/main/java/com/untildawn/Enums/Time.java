package com.untildawn.Enums;

public enum Time {
    TIME_2_MINUTES(2),
    TIME_5_MINUTES(5),
    TIME_10_MINUTES(10),
    TIME_20_MINUTES(20),
    ;

    private final int time;

    Time(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
