package com.aziis98.dare.util;

import java.util.function.*;

public class Listing {

    public static BinaryOperator<String> joiner(String separator) {
        return (a, b) -> a + separator + b;
    }

}
