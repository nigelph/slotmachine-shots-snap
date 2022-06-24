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
public class Card implements Comparable<Card> {

    private final int suit;
    private final int value;

    @Override
    public int compareTo(Card other) {

        int result = Integer.compare(this.value, other.value);
        
        if (result != 0) {
            return result;
        }

        if (result == 0) {
            result = Integer.compare(this.suit, other.suit);
        }
        return result;
    }

    public Card(int value, int suit) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        String valueString = "";
        String suitString = "";

        switch (value) {
            case 1:
                valueString = "A";
                break;
            case 2:
                valueString = "2";
                break;
            case 3:
                valueString = "3";
                break;
            case 4:
                valueString = "4";
                break;
            case 5:
                valueString = "5";
                break;
            case 6:
                valueString = "6";
                break;
            case 7:
                valueString = "7";
                break;
            case 8:
                valueString = "8";
                break;
            case 9:
                valueString = "9";
                break;
            case 10:
                valueString = "10";
                break;
            case 11:
                valueString = "J";
                break;
            case 12:
                valueString = "Q";
                break;
            case 13:
                valueString = "K";
                break;
        }
        switch (suit) {

            case 1:
                suitString = "S";
                break;
            case 2:
                suitString = "C";
                break;
            case 3:
                suitString = "D";
                break;
            case 4:
                suitString = "H";
                break;
            default:
                break;
        }

        return "[" + valueString + "," + suitString + "]";
    }
}