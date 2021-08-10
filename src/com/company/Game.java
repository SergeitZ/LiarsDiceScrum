package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {
    Cup cup = new Cup();
    public List<Player> players = new ArrayList<>();
    public HashMap<Integer, Integer> diceMap = new HashMap<>();
    int [] bidArray = new int[2];
    Scanner scanner = new Scanner(System.in);
    int MAX_DICE_ALLOWED = cup.dice.size();
    private final int MAX_PLAYERS = 6;
    private final int MIN_PLAYERS = 1;
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";


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

    public void revealTable() {
        System.out.println("\nAll dice in table: ");
        for (Player player : players) {
            System.out.println(player.name + " " + player.cup.displayCup());
        }
        System.out.println("Dice frequency: Face value=Occurrences");
        System.out.println(diceMap);
    }

    public void play() {
        while (players.size() > 1) {
            round();
        }
        System.out.println("GAME OVER!!!\n" + players.get(0).name + " wins!");
    }


    public void turn(Player activePlayer) {
        System.out.println("\n" + activePlayer.name + "'s turn.");
    }

    public void round() {
        diceMap.clear();
        for (Player player : players) {
            player.cup.roll();
            for (Die die : player.cup.dice) {
                if (diceMap.get(die.faceUpValue) == null) {
                    diceMap.put(die.faceUpValue, 1);
                } else {
                    diceMap.put(die.faceUpValue, diceMap.get(die.faceUpValue) + 1);
                }
            }
        }

        //TODO: for debugging purposes
//        revealTable();

        int i = 0;
        int turn = 0;
        Player previousPlayer = null;

        Boolean roundContinues = true;

        while (roundContinues) {
            Player activePlayer = players.get(i);
            turn(activePlayer);
            if (turn == 0) {
                makeBid(activePlayer);
            } else {
                roundContinues = challengeBid(activePlayer, previousPlayer);
            }

            previousPlayer = activePlayer;
            turn++;

            if (i == players.size() - 1) {
               i = 0;
            } else {
                i++;
            }
        }
    }
    //TODO: Do not check face up value if die amount goes up
    //TODO: if any input is invalid ask both questions again
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
        bidArray[0] = getValidBid("Enter dice amount: ", 1, MAX_DICE_ALLOWED * players.size());
        bidArray[1] = getValidBid("Enter face value: ", 1, 6);
        System.out.println("\nCurrent bid:\nDice:" + bidArray[0] + "\nValue:" + bidArray[1]);
    }

    public boolean challengeBid(Player activePlayer, Player previousPlayer) {
        System.out.println("Would you like to challenge " + previousPlayer.name + "'s bid? Y/N");
        scanner.nextLine();
        String input = scanner.nextLine();
        switch (input.toUpperCase(Locale.ROOT)) {
            case "Y":
                callLiar(activePlayer, previousPlayer);
                return false;
            case "N":
                followUpBid(activePlayer);
        }
        return true;
    }

    public void callLiar(Player activePlayer, Player previousPlayer) {
        revealTable();
        if (diceMap.get(bidArray[1]) < bidArray[0]) {
            System.out.println(TEXT_RED + "Busted! " + previousPlayer.name + " loses a die!" + TEXT_RESET);
            previousPlayer.cup.dice.remove(0);
            if (previousPlayer.cup.dice.size() == 0) {
                players.remove(previousPlayer);
            }
        } else {
            System.out.println(TEXT_GREEN + "Truth! " + activePlayer.name + " losses a die!" + TEXT_RESET);
            activePlayer.cup.dice.remove(0);
            if (activePlayer.cup.dice.size() == 0) {
                players.remove(activePlayer);
            }
            System.out.println(activePlayer.cup.displayCup());
        }
    }


    public void followUpBid(Player activePlayer) {
        System.out.println("\nFollow up bid");
        System.out.println("Hand: " + activePlayer.cup.displayCup());
        bidArray[0] = getValidBid("Enter dice amount: ", bidArray[0], MAX_DICE_ALLOWED * players.size());
        bidArray[1] = getValidBid("Enter face value: ", bidArray[1], 6);
        System.out.println("\nCurrent bid:\nDice:" + bidArray[0] + "\nValue:" + bidArray[1]);
    }
}
