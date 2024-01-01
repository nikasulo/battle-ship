package com.nikasulo.battleship.models;

//* Player
//    Attributes:
//        * Score
//        * Name
//        * Board
public class Player {
    private int score = 0;
    private final Board board;

    Player(String name) {
        this.board = new Board(5);

        System.out.println("Welcome, " + name + "!");
    }

    public Board getBoard() {
        return this.board;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getScore() {
        return this.score;
    }
}
