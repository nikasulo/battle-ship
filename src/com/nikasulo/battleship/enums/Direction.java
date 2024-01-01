package com.nikasulo.battleship.enums;
import java.util.concurrent.ThreadLocalRandom;

public enum Direction {
    VERTICAL,
    HORIZONTAL;

    public static Direction getRandomDirection() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, values().length);

        return values()[randomNum];
    }
}
