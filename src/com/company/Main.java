package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	/*
	1) single player can roll dice,
        look at dice

     2) make a bid, make a second valid bid (increase die value, or increase number of dice of any value),
        ask for another bid if invalid bid given

            * Create a variable to store bid, using HashMap
            * Store a second bid

     3) [Liar System]

	 */

        Scanner scanner = new Scanner(System.in);

        Cup cup = new Cup();
        cup.roll();

        int[] bidArray = new int[2];

        int numberOfDice;
        int faceUpValue;
        int numberOfDice2;
        int faceUpValue2;


        System.out.println("Hand: " + cup.displayCup());
        System.out.print("Please enter number of dice: ");
        numberOfDice = scanner.nextInt();
        bidArray[0] = numberOfDice;
        System.out.print("Please enter face up value of dice: ");
        faceUpValue = scanner.nextInt();
        bidArray[1] = faceUpValue;

        System.out.println("Current bid: \nDice: " + bidArray[0] + " Value: " + bidArray[1]);

        while (true) {
            System.out.print("Please enter second bid\nNumber of dice: ");
            numberOfDice2 = scanner.nextInt();
            bidArray[0] = numberOfDice2;
            System.out.print("Second face up value of dice: ");
            faceUpValue2 = scanner.nextInt();
            bidArray[1] = faceUpValue2;
            if (numberOfDice2 < numberOfDice || faceUpValue2 < faceUpValue) {
                System.out.println("Please enter valid bid");
            } else {
                System.out.println("Current bid: \nDice: " + bidArray[0] + " Value: " + bidArray[1]);
                break;
            }
        }
    }

}
