package net.gbksoft.chatlibrary.model;

/**
 *
 * Class for badges for users and groups
 */

public class Numerator {
    private int number = 0;

    public int increment() {
        return ++number;
    }

    public int getNumber() {
        return number;
    }

    public int setNumber(int number) {
        this.number = number;
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}