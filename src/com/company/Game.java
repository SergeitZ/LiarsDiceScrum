package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public Player player;
    public List<Player> players = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;

    public Game () {
//        System.out.print("Enter number of players: ");
//        int numberOfPlayers;
//        do {
//            numberOfPlayers = scanner.nextInt();
//            scanner.nextLine();
//        } while (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS);
//
//        while (players.size() < numberOfPlayers) {
//            System.out.print("Enter name: ");
//            players.add(new Player(scanner.nextLine().trim()));
//        }
    }

    public void getValidBid() {

    }

    public void makeBid() {
        Cup cup = new Cup();
        cup.roll();

        final int DIE_FACE_VALUE_MAX = 6;
        final int DIE_FACE_VALUE_MIN = 1;

        int[] bidArray = new int[2];

        int numberOfDice;
        int faceUpValue;
        int numberOfDice2;
        int faceUpValue2;

        System.out.println("Hand: " + cup.displayCup());

        while (true) {
            System.out.print("Enter bid\nNumber of dice: ");
            numberOfDice = scanner.nextInt();
            bidArray[0] = numberOfDice;
            System.out.print("Face value of dice: ");
            faceUpValue = scanner.nextInt();
            bidArray[1] = faceUpValue;
            if (faceUpValue > DIE_FACE_VALUE_MAX
                    || faceUpValue < DIE_FACE_VALUE_MIN
                    || numberOfDice > cup.dice.size()
                    || numberOfDice < 1) {
                System.out.println("\nPlease enter valid bid\n");
            } else {
                System.out.println("\nCurrent bid: \nDice: " + bidArray[0] + "\nValue: " + bidArray[1] + "\n");
                break;
            }
        }

        while (true) {
            System.out.print("Enter second bid\nNumber of dice: ");
            numberOfDice2 = scanner.nextInt();
            bidArray[0] = numberOfDice2;
            System.out.print("Face value of dice: ");
            faceUpValue2 = scanner.nextInt();
            bidArray[1] = faceUpValue2;
            //TODO: Adjust logic to decline same number of dice and face value
            if (numberOfDice2 < numberOfDice
                    || faceUpValue2 < faceUpValue
                    || numberOfDice2 > cup.dice.size()
                    || faceUpValue2 > DIE_FACE_VALUE_MAX) {
                System.out.println("\nPlease enter valid bid\n");
            } else {
                System.out.println("\nCurrent bid: \nDice: " + bidArray[0] + "\nValue: " + bidArray[1] + "\n");
                break;
            }
        }
    }
}
