package com.company;

import java.util.HashMap;
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
        HashMap<Integer, Integer> bidMap = new HashMap<>();

        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();

        die1.roll();
        die2.roll();
        die3.roll();

        System.out.println(die1.faceUpValue + " " + die2.faceUpValue + " " + die3.faceUpValue);

        int numberOfDice = scanner.nextInt();
        int faceUpValue = scanner.nextInt();

        bidMap.put(numberOfDice, faceUpValue);


        System.out.println(bidMap);

    }
}
