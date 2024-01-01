package com.nikasulo.battleship.models;

import com.nikasulo.battleship.enums.Direction;
import com.nikasulo.battleship.enums.ShipType;
import java.util.concurrent.ThreadLocalRandom;

import java.util.*;
// Attributes
    // * Length
    // * Breadth
    // * Ships
public class Board {
    private final int size;
    private final List<Ship> ships = new ArrayList<>();

    private final Ship[][] board;

    static class PositionOccupiedException extends  Exception {}

    private final HashMap<String, Boolean> shipCoordinates = new HashMap<>();

    public Board(int size) {
        this.size = size;
        this.board = new Ship[size][size];

        createShips();
    }

    private boolean canPlaceShip(Coordinate coordinate, Direction direction, ShipType type) {
        int x = coordinate.x();
        int y = coordinate.y();

        for (int offset = 0; offset < type.getSize(); offset++) {
            try {
                switch (direction) {
                    case VERTICAL -> x += offset;
                    case HORIZONTAL -> y += offset;
                }

                if (this.board[x][y] != null) {
                    throw new PositionOccupiedException();
                }
            } catch (PositionOccupiedException | ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }

        return true;
    }

    private void createShips() {
        while(this.ships.isEmpty()) {
            for (ShipType type : ShipType.values()) {
                Direction direction = Direction.getRandomDirection();
                Coordinate startCoordinates = getRandomCoordinate();
                Ship ship = new Ship(type, direction, startCoordinates);

                if (canPlaceShip(startCoordinates, direction, type)) {
                    placeShip(type, direction, startCoordinates, ship);
                }
            }
        }
    }

    private void placeShip(ShipType type, Direction direction, Coordinate coordinate, Ship ship) {
        List<Ship> ships = new ArrayList<>();
        int x = coordinate.x();
        int y = coordinate.y();

        for (int offset = 0; offset < type.getSize(); offset++) {
            Coordinate newCoordinate = new Coordinate(x, y);

            placeShipAtCoordinates(newCoordinate, ship);

            shipCoordinates.put(newCoordinate.asKey(), true);

            ships.add(ship);

            switch(direction) {
                case VERTICAL -> x += 1;
                case HORIZONTAL -> y += 1;
            }
        }

        setShips(ships);
    }

    private void placeShipAtCoordinates(Coordinate coordinate, Ship ship) {
        this.board[coordinate.x()][coordinate.y()] = ship;
    }

    private void setShips(List<Ship> ships) {
        this.ships.addAll(ships);
    }

    public boolean hitShipAt(Coordinate coordinate) {
        String coordinateKey = coordinate.asKey();

        if (shipCoordinates.containsKey(coordinateKey) && shipCoordinates.get(coordinateKey)) {
            shipCoordinates.put(coordinateKey, false);

            return true;
        }

        return false;
    }

    public Coordinate getRandomCoordinate() {
        int x = ThreadLocalRandom.current().nextInt(0, this.size);
        int y = ThreadLocalRandom.current().nextInt(0, this.size);

       Coordinate coordinates = new Coordinate();

        coordinates.add(x);
        coordinates.add(y);

        return coordinates;
    }

    public boolean hasUnhitShips() {
        for (boolean shipIsUnhit : shipCoordinates.values()) {
            if (shipIsUnhit) {
                return true;
            }
        }

        return false;
    }

    public void printShipMetadata() {
        for (Ship ship : ships) {
            ship.printMetadata();
        }
    }

    public void print() {
        for(int x = 0; x < this.board.length; x++) {
            StringBuilder rowAsString = new StringBuilder("|");

           for(int y = 0; y < this.board.length; y++) {
               Coordinate coordinate = new Coordinate(x, y);

               String coordinateKey = coordinate.asKey();

               if (this.shipCoordinates.containsKey(coordinateKey) && !this.shipCoordinates.get(coordinateKey)) {
                   rowAsString.append("S");
               } else {
                   rowAsString.append("*");
               }

               rowAsString.append("|");
           }

            System.out.println(rowAsString);
        }
    }
}
