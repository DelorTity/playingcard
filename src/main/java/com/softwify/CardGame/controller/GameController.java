package com.softwify.CardGame.controller;

import com.softwify.CardGame.View.View;
import com.softwify.CardGame.model.Deck;
import com.softwify.CardGame.model.Player;
import com.softwify.CardGame.model.PlayingCard;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    enum GameState {
        AddingPlayers, CardsDealt, WinnerRevealed
    }
    Deck deck;
    List<Player> players;
    Player winner;
    View view;

    GameState gameState;

    public GameController(Deck deck, View view) {
        super();
        this.deck = deck;
        this.view = view;
        this.players = new ArrayList<>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
    }

    public void run() {
        while(gameState == GameState.AddingPlayers) {
            view.promptForPlayerName();
        }
        switch (gameState) {
            case CardsDealt:
                view.promptForFlip();
            case WinnerRevealed:
                view.promptForNewGame();
                break;
        }

    }

    public void addPlayer(String playerName) {
        if(gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if(gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = 1;
            for (Player player : players) {
                player.addCardToHand(deck.removeLopCard());
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        int playerIndex = 1;
        for(Player player : players) {
            PlayingCard play = player.getCard(0);
            play.flip();
            view.showCardForPlayer(playerIndex++, player.getName(),
                    play.getRank().toString(), play.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        this.run();
    }

    private void evaluateWinner() {
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;

        for(Player player : players) {
            boolean newBestPlayer = false;

            if(bestPlayer == null) {
                newBestPlayer = true;
            } else {
                PlayingCard play = player.getCard(0);
                int thisRank = play.getRank().value();
                if(thisRank >= bestRank) {
                    if(thisRank > bestRank) {
                        newBestPlayer = true;
                    } else {
                        if(play.getSuit().value() > bestSuit) {
                            newBestPlayer = true;
                        }
                    }
                }
            }
            if(newBestPlayer) {
                bestPlayer = player;
                PlayingCard play = player.getCard(0);
                bestRank = play.getRank().value();
                bestSuit = play.getSuit().value();
            }
        }
        winner = bestPlayer;
    }
    void displayWinner() {
        view.showWinner(winner.getName());
    }

    private void rebuildDeck() {
        for(Player player : players) {
            deck.returnCardToDesk(player.removeCard());
        }
    }
}
