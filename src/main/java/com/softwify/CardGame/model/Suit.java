package com.softwify.CardGame.model;

public enum Suit {
    DIAMONDS(1), HEARTS(2), SPADES(3), CLUBS(4);

    public int suit;

    private Suit(int value) {
        suit = value;
    }

    public int value() {
        return suit;
    }
}
