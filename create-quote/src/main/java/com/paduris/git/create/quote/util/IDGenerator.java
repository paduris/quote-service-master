package com.paduris.git.create.quote.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public final class IDGenerator {

    public String randomPositiveLongAsString() {
        return Long.toString(randomPositiveLong());
    }

    public String randomNegativeLongAsString() {
        return Long.toString(randomNegativeLong());
    }

    public static long randomPositiveLong() {
        long id = new Random().nextLong() * 10000;
        id = (id < 0) ? (-1 * id) : id;
        return id;
    }

    private long randomNegativeLong() {
        long id = new Random().nextLong() * 10000;
        id = (id > 0) ? (-1 * id) : id;
        return id;
    }
}
