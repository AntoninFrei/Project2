package com.engeto.project2;

import java.util.Scanner;

public class ReadInput {

    private static Scanner scanner = null;

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
    public static String safeInput() {
        return getScanner().nextLine();
    }
}
