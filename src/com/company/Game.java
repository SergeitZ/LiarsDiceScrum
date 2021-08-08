package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Cup cup = new Cup();
    public List<Player> players = new ArrayList<>();
    int [] bidArray = new int[2];
    Scanner scanner = new Scanner(System.in);
    int MAX_DICE_ALLOWED = cup.dice.size();
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;


    public Game () {
        System.out.print("Enter number of players: ");
        int numberOfPlayers;
        do {
            numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
        } while (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS);

        while (players.size() < numberOfPlayers) {
            System.out.print("Enter name: ");
            players.add(new Player(scanner.nextLine().trim()));
        }
    }

    public void play() {
        round();
    }

    //TODO: implement challenge previous bid system
    public void turn(Player activePlayer) {
        System.out.println("\n" + activePlayer.name + "'s turn.");
//        activePlayer.cup.roll();
        makeBid(activePlayer);
    }
    // All players need to roll their cup before the first player takes his turn
    public void round() {
        for (Player activePlayer : players) {
            activePlayer.cup.roll();
            turn(activePlayer);
        }
    }

    public int getValidBid(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min && value <= max)
                break;
            System.out.println("Please enter valid bid");
        }
        return value;
    }


    public void makeBid(Player activePlayer) {

        System.out.println("Hand: " + activePlayer.cup.displayCup());
        bidArray[0] = getValidBid("Enter dice amount: ", 1, MAX_DICE_ALLOWED);
        bidArray[1] = getValidBid("Enter face value: ", 1, 6);
        System.out.println("\nCurrent bid:\nDice:" + bidArray[0] + "\nValue:" + bidArray[1]);

    }


    public void followUpBid(Player activePlayer) {
        System.out.println("\nFollow up bid");
        bidArray[0] = getValidBid("Enter dice amount: ", bidArray[0], MAX_DICE_ALLOWED);
        bidArray[1] = getValidBid("Enter face value: ", bidArray[1], MAX_DICE_ALLOWED);
        System.out.println("\nCurrent bid:\nDice:" + bidArray[0] + "\nValue:" + bidArray[1]);
    }

}
