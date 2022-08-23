package com.softwify.CardGame.model;

import java.util.*;

public class Deck {
    private List<PlayingCard> cards;

    public Deck() {
        cards = new ArrayList<PlayingCard>();
        for(Rank rank : Rank.values()) {
            for(Suit suit : Suit.values()) {
                System.out.println("Creating card [" + rank + "][" + suit + "]");
                cards.add(new PlayingCard(rank, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Random random = new Random();
        for(int i = 0; i < cards.size(); i++){
            Collections.swap(cards, i, random.nextInt(cards.size()));
        }
    }

    public PlayingCard removeLopCard() {
        return cards.remove(0);
    }

    public void returnCardToDesk(PlayingCard play) {
        cards.add(play);
    }

    public List<PlayingCard> getCards() {
        return cards;
    }
}
