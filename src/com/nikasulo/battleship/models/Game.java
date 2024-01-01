package com.nikasulo.battleship.models;

import java.util.Scanner;

public class Game {
    private final Player user;
    private final Player computer;

    public Game(String playerName) {
        this.user = new Player(playerName);
        this.computer = new Player("Computer");
    }

    public void play() {
        Scanner userInput = new Scanner(System.in);

        while (shouldContinue()) {
            System.out.println("Your turn!");

            System.out.println("Enter x coordinates between 0 and 7");

            int x = userInput.nextInt();

            System.out.println("Enter y coordinates between 0 and 7");

            int y = userInput.nextInt();

            Coordinate coordinate = new Coordinate(x, y);

            playUserTurn(coordinate);

            playComputerTurn();

            printUserBoard();

            printComputerBoard();
        }

        System.out.println("User Score: " + this.user.getScore());
        System.out.println("====================User Board===================");
        this.user.getBoard().printShipMetadata();
        System.out.println("=======================================");

        System.out.println("CPU Score: " + this.computer.getScore());
        System.out.println("====================CPU Board===================");
        this.computer.getBoard().printShipMetadata();
    }

    private boolean shouldContinue() {
        return this.user.getBoard().hasUnhitShips() && this.computer.getBoard().hasUnhitShips();
    }

    private void playComputerTurn() {
        Board userBoard = this.user.getBoard();

        Coordinate coordinate = this.user.getBoard().getRandomCoordinate();

        boolean shipHit = userBoard.hitShipAt(coordinate);

        if (shipHit) {
            this.computer.incrementScore();
        }
    }

    private void playUserTurn(Coordinate coordinate) {
        boolean shipHit = this.computer.getBoard().hitShipAt(coordinate);

        if (shipHit) {
            this.user.incrementScore();
        }
    }

    private void printUserBoard() {
        System.out.println("====== Player Board =======");
        this.user.getBoard().print();
        System.out.println("=============");
        System.out.println();
    }

    private void printComputerBoard() {
        System.out.println("====== Computer Board =======");

        this.computer.getBoard().print();
        System.out.println("=============");
        System.out.println();
    }
}
