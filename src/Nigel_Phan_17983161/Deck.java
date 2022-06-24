/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nigel_Phan_17983161;

import java.util.Random;

/**
 *
 * @author Nigel
 */
public class Deck {

    private final Card[] deck;
    private int deckSize;
    public final int MAX_SIZE = 52;

    public Deck() {
        deck = new Card[MAX_SIZE];
    }

    public Deck(boolean fullDeck) {
        deck = new Card[52];

        if (fullDeck == true) {
            initialiseFullDeck();
        }
    }

    private void initialiseFullDeck() {

        for (int cardvalue = 1; cardvalue <= 13; cardvalue++) {
            for (int suitvalue = 1; suitvalue <= 4; suitvalue++) {
                deck[(suitvalue - 1) * 13 + cardvalue - 1] = new Card(cardvalue, suitvalue);
            }
        }
        deckSize = deck.length;
    }

    public Card drawCard() {
        Card draw = deck[deckSize - 1];
        Card tempVar = draw;
        deck[deckSize - 1] = null;
        deckSize--;
        return tempVar;
    }

    public void placeCard(Card card) {
        deck[deckSize++] = card;
    }

    public void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < deckSize; i++) {

            int x = i + rand.nextInt(52 - i);

            Card temp = deck[x];
            deck[x] = deck[i];
            deck[i] = temp;
        }
    }

    public int getDeckSize() {
        return deckSize;
    }

    public boolean hasCardsRemaining() {
        return deckSize != 0;
    }

    @Override
    public String toString() {

        String printCards = "";

        for (int i = 0; i < deckSize; i++) {
            printCards += "" + deck[i];
        }

        return printCards;
    }
}
