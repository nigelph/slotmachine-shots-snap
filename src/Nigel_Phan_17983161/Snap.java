///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package Nigel_Phan_17983161;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Nigel
 */
public class Snap {

    private int playerTurn;
    public static int PLAYER_1 = 1;
    public static int PLAYER_2 = 2;
    private Deck player1Deck;
    private Deck player2Deck;
    private ArrayList<Card> pile;

    public Snap() {

        playerTurn = PLAYER_1;
        setupPlayerDecks();

        pile = new ArrayList<>();
    }

    private void setupPlayerDecks() {

        player1Deck = new Deck(true);
        player2Deck = new Deck(false);

        player1Deck.shuffle();

        for (int i = 0; i < player1Deck.getDeckSize(); i++) {

            Card cards = player1Deck.drawCard();
            player2Deck.placeCard(cards);
        }
    }

    private void pickupPile(int player) {

        if (player == PLAYER_1) {
            for (int i = 0; i < pile.size(); i++) {
                player1Deck.placeCard(pile.get(i));
            }
        } else if (player == PLAYER_2) {
            for (int i = 0; i < pile.size(); i++) {
                player2Deck.placeCard(pile.get(i));
            }
            pile.clear();
        }
    }

    private boolean checkSnap() {
        Card card1 = pile.get(pile.size() - 1);
        Card card2 = pile.get(pile.size() - 2);

        if (card1.getValue() == (card2.getValue())) {
            return true;
        }
        return false;
    }

    public boolean snap(int player) {

        if (player == PLAYER_1) {
            System.out.println("PLAYER 1 SNAP!!!");
            if (checkSnap() == true) {
                pickupPile(PLAYER_1);
                return true;
            } else {
                pickupPile(PLAYER_2);
                return false;
            }
        } else {
            System.out.println("PLAYER 2 SNAP!!!");
            if (checkSnap() == true) {
                pickupPile(PLAYER_2);
                return true;
            } else {
                pickupPile(PLAYER_1);
                return false;
            }
        }
    }

    public Card drawCard(int player) {
        if (player == PLAYER_1) {
            playerTurn = PLAYER_2;

            Card card1 = player1Deck.drawCard();
            pile.add(card1);
            return card1;
        } else {
            playerTurn = PLAYER_1;

            Card card2 = player2Deck.drawCard();
            pile.add(card2);
            return card2;
        }
    }

    public boolean hasGameFinished() {

        if (player1Deck.hasCardsRemaining()) {
            return false;
        } else if (player2Deck.hasCardsRemaining()) {
            return false;
        }

        return true;
    }

    public boolean isWinner(int player) {

        if (player == 1) {
            if (!player2Deck.hasCardsRemaining()) {
                System.out.println("PLAYER 1 WINS!!!");
                return true;
            }
        } else if (player == 2) {
            if (!player1Deck.hasCardsRemaining()) {
                System.out.println("PLAYER 2 WINS!!!");
                return true;
            }
        }
        return false;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getPlayerCardsRemaining(int player) {
        if (player == PLAYER_1) {
            return player1Deck.getDeckSize();
        } else if (player == PLAYER_2) {
            return player2Deck.getDeckSize();
        }
        return 0;
    }

    public static void main(String[] args) {
        Snap snapgame = new Snap();
        Scanner scan = new Scanner(System.in);

        snapgame.setupPlayerDecks();

        System.out.println("------------------SNAP!!!!-----------------");
        System.out.println("KEYS: player1 draw: [o], player1 snap: p");
        System.out.println("KEYS: player2 draw: [w], player2 snap: q");
        System.out.println("------------------GO!!!!-------------------");

        System.out.println("Press ['o'] for PLAYER 1 OR ['w'] for PLAYER 2 to start game");

        boolean notFinished = true;
        while (notFinished) {

            String snapkey = scan.nextLine();
            if (snapkey.equalsIgnoreCase("o") && snapgame.getPlayerCardsRemaining(PLAYER_1) <= 0) {
                System.out.println("GAME OVER!");
                snapgame.isWinner(PLAYER_2);
                notFinished = false;
            } else if (snapkey.equalsIgnoreCase("o")) {
                System.out.println("PLAYER " + snapgame.getPlayerTurn() + " draws " + snapgame.drawCard(PLAYER_1) + " REMAINING P1:" + snapgame.getPlayerCardsRemaining(PLAYER_1) + " P2:" + snapgame.getPlayerCardsRemaining(PLAYER_2));
            } else if (snapkey.equalsIgnoreCase("w") && snapgame.getPlayerCardsRemaining(PLAYER_2) <= 0) {
                System.out.println("GAME OVER!");
                snapgame.isWinner(PLAYER_1);
                notFinished = false;
            } else if (snapkey.equalsIgnoreCase("w")) {
                System.out.println("PLAYER " + snapgame.getPlayerTurn() + " draws " + snapgame.drawCard(PLAYER_2) + " REMAINING P1:" + snapgame.getPlayerCardsRemaining(PLAYER_1) + " P2:" + snapgame.getPlayerCardsRemaining(PLAYER_2));
            }

            if (snapkey.equalsIgnoreCase("p")) {

                if (snapgame.checkSnap() == true) {
                    snapgame.snap(PLAYER_1);

                } else if (snapgame.checkSnap() == false) {
                    System.out.println("FALSE SNAP! PENALTY AGAINST PLAYER 1!!!");
                    snapgame.pickupPile(PLAYER_2);
                }
                System.out.println("REMAINING: P1:" + snapgame.getPlayerCardsRemaining(PLAYER_1) + " P2:" + snapgame.getPlayerCardsRemaining(PLAYER_2));
            } else if (snapkey.equalsIgnoreCase("q")) {
                if (snapgame.checkSnap() == true) {
                    snapgame.snap(PLAYER_2);

                } else if (snapgame.checkSnap() == false) {
                    System.out.println("FALSE SNAP! PENALTY AGAINST PLAYER 2!!!");
                    snapgame.pickupPile(PLAYER_1);
                }
                System.out.println("REMAINING: P1:" + snapgame.getPlayerCardsRemaining(PLAYER_1) + " P2:" + snapgame.getPlayerCardsRemaining(PLAYER_2));
            } else if (snapgame.hasGameFinished() == true) {
                System.out.println("GAME OVER");
                System.out.println("THE GAME ENDED IN A DRAW!");
                notFinished = false;
            }
        }
    }
}