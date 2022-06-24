/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nigel_Phan_17983161;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Nigel
 */
public class SlotMachine {

    private final Random generator;
    private int tokenCredit;
    private int houseCredit;

    public int getHouseCredit() {
        return houseCredit;
    }

    public SlotMachine() {
        generator = new Random();
    }

    public void topupTokens(int tokens) {
        tokenCredit = tokens;
    }

    public int getTokenBalance() {
        return tokenCredit;
    }

    public int cashoutTokens() {
        return tokenCredit;
    }

    public void pullLever() {
        pullLever(1);
    }

    public void pullLever(int tokenInput) {
        int firstNumber = generator.nextInt(10);
        int secondNumber = generator.nextInt(10);
        int thirdNumber = generator.nextInt(10);

        System.out.println("ROLLED>>> :" + firstNumber + " : " + secondNumber + " : " + thirdNumber + ":");

        if (firstNumber == 0 && secondNumber == 0 && thirdNumber == 0) {
            System.out.println("Super Jackpot Winner");
            tokenCredit += tokenInput * 500;
        } else if (firstNumber == secondNumber && firstNumber == thirdNumber) {
            System.out.println("Jackpot Winner");
            tokenCredit += tokenInput * 50;
        } else if (firstNumber == secondNumber || firstNumber == thirdNumber || secondNumber == thirdNumber) {
            System.out.println("Free Spin");
            tokenCredit += tokenInput;
        } else {
            System.out.println("Bad Luck, Try again");
        }
    }

    public static void main(String[] args) {
        SlotMachine slotmachine = new SlotMachine();
        Scanner scan = new Scanner(System.in);

        int machine = 0;

        boolean keepRunning = true;
        while (keepRunning) {
            if (machine == 0) {
                System.out.println("SLOT MACHINE!!! ENTER AMOUNT OF TOKENS");
            } else if (machine < 10) {
                System.out.println("\nSLOT MACHINE " + machine + "!!! ENTER AMOUNT OF TOKENS");
            }
            slotmachine.topupTokens(scan.nextInt());
            System.out.println("YOU HAVE " + slotmachine.getTokenBalance() + " TOKENS REMAINING\n");
            slotmachine.houseCredit += slotmachine.getTokenBalance();
            scan.nextLine();

            boolean notFinished = true;
            while (notFinished) {

                System.out.println("[INPUT AMOUNT TO GAMBLE and ENTER TO PULL LEVER] or [PRESS 'T'/'t' to TOP UP] or ['Q'/'q' to cashout]");
                String input = scan.nextLine();

                if (slotmachine.getTokenBalance() <= 0) {
                    System.out.println("Insufficient tokens to deal 1 BALANCE ONLY 0");
                    notFinished = false;
                } else if (input.charAt(0) == 'q' || input.charAt(0) == 'Q') {

                    slotmachine.houseCredit = slotmachine.houseCredit - slotmachine.getTokenBalance();
                    notFinished = false;
                } else if (input.charAt(0) == 't' || input.charAt(0) == 'T') {
                    System.out.println("\nHOW MANY TOKENS WOULD YOU LIKE TO TOP UP?");
                    int topUp = Integer.parseInt(scan.nextLine());
                    slotmachine.topupTokens(slotmachine.getTokenBalance() + topUp);
                    slotmachine.houseCredit += topUp;
                    System.out.println("YOU HAVE " + slotmachine.getTokenBalance() + " TOKENS REMAINING\n");
                } else {
                    slotmachine.pullLever(Integer.parseInt(input));
                    slotmachine.topupTokens(slotmachine.getTokenBalance() - Integer.parseInt(input));
                    System.out.println("YOU HAVE " + slotmachine.getTokenBalance() + " TOKENS REMAINING\n");
                }
            }

            System.out.println("------------------------------------------------");
            System.out.println("YOU HAVE CASHED OUT: " + slotmachine.getTokenBalance());

            if (slotmachine.getTokenBalance() > 0) {
                System.out.println("CASINO HOUSE BALANCE: " + slotmachine.houseCredit);
            } else {
                System.out.println("CASINO HOUSE BALANCE: " + slotmachine.getHouseCredit());
            }

            System.out.println("[PRESS 'C'/'c' TO CHANGE SLOT MACHINE] or ['Q'/'q' TO QUIT AND LEAVE CASINO]");
            String decision = scan.nextLine();

            if (decision.equalsIgnoreCase("Q")) {
                System.out.println("------------------------------------------------");
                System.out.println("YOU LEFT THE CASINO...");
                keepRunning = false;
            } else if (decision.equalsIgnoreCase("C")) {
                machine++;
                slotmachine.tokenCredit = 0;
            }
        }
    }
}