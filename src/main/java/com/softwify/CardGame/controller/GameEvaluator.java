package com.softwify.CardGame.controller;

import com.softwify.CardGame.model.Player;
import com.softwify.CardGame.model.PlayingCard;

import java.util.List;

public class GameEvaluator {
    public Player evaluateWinner(List<Player> players) {
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
        Player winner = bestPlayer;
        return bestPlayer;
    }
}
