package com.nikasulo.battleship.enums;

//        * Aircraft Carrier (size: 5)
//        * Battleship (size: 4)
//        * Cruiser (size: 3)
//        * Submarine (size: 3)
//        * Destroyer (size: 2)
public enum ShipType {
    DESTROYER(2),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    AIRCRAFT_CARRIER(5);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

//    public static ShipType getRandomType() {
//        int randomNum = ThreadLocalRandom.current().nextInt(0, values().length + 1);
//
//        return values()[randomNum];
//    }
}
