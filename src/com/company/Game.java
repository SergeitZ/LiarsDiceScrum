package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Cup cup = new Cup();
    public Player player;
    public List<Player> players = new ArrayList<>();
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

    public int getValidBid(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
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

    public void makeBid() {
        cup.roll();
        int MAX_DICE_ALLOWED = cup.dice.size();
        int [] bidArray = new int[2];
        System.out.println("Hand: " + cup.displayCup());
        bidArray[0] = getValidBid("Enter dice amount: ", 1, MAX_DICE_ALLOWED);
        bidArray[1] = getValidBid("Enter face value: ", 1, 6);
    }
}
