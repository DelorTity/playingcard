package com.softwify.CardGame.model;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        super();
        this.name = name;
        hand = new Hand();
    }

    public void addCardToHand(PlayingCard play) {
        hand.addCard(play);
    }

    public PlayingCard getCard(int index) {
        return hand.getCard(index);
    }

    public PlayingCard removeCard() {
        return hand.removeCard();
    }
    
    public String getName() {
        return name;
    }
}
