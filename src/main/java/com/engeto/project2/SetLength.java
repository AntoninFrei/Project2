package com.engeto.project2;

public class SetLength {

    public static String setLength(int length, String string) {
        for (int i = string.length(); i < length; i++) string += " ";
        return string;
    }
}
