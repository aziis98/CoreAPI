package com.aziis98.dare.system;

import java.util.*;
import java.util.stream.*;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    public static void out(String... strings) {
        out(Arrays.stream(strings).collect(Collectors.joining(" ")));
    }

    public static void inline(String string) {
        System.out.print(string);
    }

    public static void out(String string) {
        System.out.println(string);
    }

    public static void out() {
        System.out.println();
    }

    public static String read() {
        return scanner.nextLine();
    }

    public static String read(String message) {
        System.out.print(message);
        return read();
    }

    public static void stop() {
        System.exit(0);
    }
}
