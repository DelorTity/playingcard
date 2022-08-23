package com.softwify.CardGame;

import com.softwify.CardGame.View.View;
import com.softwify.CardGame.controller.GameController;
import com.softwify.CardGame.model.Deck;

public class Game {

    public static void main(String[] args) {
        GameController game = new GameController(new Deck(), new View());
        game.run();
    }
}
