/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nigel_Phan_17983161;

/**
 *
 * @author Nigel
 */
public class DrinkingGame {

    public static void main(String[] args) {

        Deck deck = new Deck(true);

        int player1ShotCounter = 0;
        int player2ShotCounter = 0;

        deck.shuffle();

        for (int i = 52; i >= deck.getDeckSize(); i--) {
            Card player1Card = deck.drawCard();
            Card player2Card = deck.drawCard();

            System.out.println("Player 1 drew: " + player1Card.toString());
            System.out.println("Player 2 drew: " + player2Card.toString());

            if (player1Card.compareTo(player2Card) < 0) {
                System.out.println("> PLAYER 1 TAKES THE SHOT\n");
                player1ShotCounter++;
            } else {
                System.out.println("> PLAYER 2 TAKES THE SHOT\n");
                player2ShotCounter++;
            }
            if (deck.getDeckSize() == 0) {
                break;
            }
        }

        if (player1ShotCounter == 1) {
            System.out.println("Player 1 took " + player1ShotCounter + " shot");
        } else if (player2ShotCounter == 1) {
            System.out.println("Player 2 took " + player2ShotCounter + " shot");
        } else {
            System.out.println("TOTAL SHOTS TAKEN BY BOTH PLAYERS:");
            System.out.println("----------------------------------");
            System.out.println("PLAYER 1 TOOK " + player1ShotCounter + " SHOTS");
            System.out.println("PLAYER 2 TOOK " + player2ShotCounter + " SHOTS");
        }
    }
}