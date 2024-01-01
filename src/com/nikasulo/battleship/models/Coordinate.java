package com.nikasulo.battleship.models;
import java.util.ArrayList;

public class Coordinate extends ArrayList<Integer> {

    public Coordinate(int x, int y) {
        add(x);
        add(y);
    }

    public Coordinate() {
        super();
    }

    public String asKey() {
        return get(0) + "," + get(1);
    }

    public int x() {
        return get(0);
    }

    public int y() {
        return get(1);
    }

    public String asString() {
        return "x: " + x() + ", " + "y: " + y();
    }
}
