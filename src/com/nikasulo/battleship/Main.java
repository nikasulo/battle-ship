package com.nikasulo.battleship;

import com.nikasulo.battleship.models.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Nikasulo");

        game.play();
    }
}