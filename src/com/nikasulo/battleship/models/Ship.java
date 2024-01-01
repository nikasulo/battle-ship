package com.nikasulo.battleship.models;

import com.nikasulo.battleship.enums.Direction;
import com.nikasulo.battleship.enums.ShipType;

//* Ship
//Attributes:
//        * Co-ordinates
//        * Size
//        * Type
//Types:
//
public class Ship {
    private final ShipType type;
    private final Direction direction;
    private final Coordinate startCoordinates;

    Ship(ShipType type, Direction direction, Coordinate startCoordinates) {
        this.type = type;
        this.direction = direction;
        this.startCoordinates = startCoordinates;
    }

    public void printMetadata() {
        System.out.println(this.type + " at " + this.startCoordinates.asString() + ", placed " + direction + "LY");
    }
}
