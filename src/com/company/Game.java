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
        System.out.print("Enter number of players: ");
        int numberOfPlayers;
        do {
            numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
        } while (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS);

        while (players.size() < numberOfPlayers) {
            System.out.print("Enter name: ");
            players.add(new Player(scanner.nextLine().trim()));
        }

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
