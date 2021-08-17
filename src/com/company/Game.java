package com.company;
import java.util.*;
import java.util.List;

public class Game {
    Cup cup = new Cup();
    public List<Player> players = new ArrayList<>();
    public HashMap<Integer, Integer> diceMap = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    int MAX_DICE_ALLOWED = cup.dice.size();
    private int previousDiceBid = 0;
    private int previousFaceBid = 0;
    private int currentDiceBid = 0;
    private int currentFaceBid = 0;
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_ORANGE = "\033[38;5;166m";


    public Game () {
        final int MAX_PLAYERS = 6;
        final int MIN_PLAYERS = 1;
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
        System.out.println("Dice frequency: " + diceMap);
    }

    public void play() {
        while (players.size() > 1) {
            round();
        }
        System.out.println("GAME OVER!!!\n" + players.get(0).name + " wins!");
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
            System.out.println("\n" + activePlayer.name + "'s turn.");
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

    public int[] getValidBid(String promptDice, String promptFace, int minDice, int minFace) {
        int[] values = new int[2];
        while (true) {
            System.out.print(promptDice);
            values[0] = scanner.nextInt();
            System.out.print(promptFace);
            values[1] = scanner.nextInt();
            if (values[0] > minDice && // Changes dice amount
                    values[0] <= MAX_DICE_ALLOWED * players.size() &&
                    values[1] >= minFace &&
                    values[1] <= 6) {
                break;
            } else if (values[0] >= 1 && // Changes face amount
                    values[0] < MAX_DICE_ALLOWED * players.size() &&
                    values[1] > minFace &&
                    values[1] <= 6) {
                break;
            }
            System.out.println("Please enter valid bid");
        }
        return values;
    }

    public void makeBid(Player activePlayer) {
        System.out.println("Hand: " + activePlayer.cup.displayCup());
        int[] bidArray = getValidBid("Enter dice amount: ", "Enter face value: ", 0, 0);
        previousDiceBid = bidArray[0];
        previousFaceBid = bidArray[1];
        System.out.println(TEXT_ORANGE + "\nCurrent bid:\nDice:" + previousDiceBid + "\nValue:" + previousFaceBid + TEXT_RESET);
    }

    public void followUpBid(Player activePlayer) {
        System.out.println("\nFollow up bid");
        System.out.println("Hand: " + activePlayer.cup.displayCup());
        int[] bidArray = getValidBid("Enter dice amount: ", "Enter face value: ", previousDiceBid, previousFaceBid);
        currentDiceBid = bidArray[0];
        currentFaceBid = bidArray[1];
        System.out.println(TEXT_ORANGE + "\nCurrent bid:\nDice:" + currentDiceBid + "\nValue:" + currentFaceBid + TEXT_RESET);
        previousDiceBid = currentDiceBid;
        previousFaceBid = currentFaceBid;
    }

    public boolean challengeBid(Player activePlayer, Player previousPlayer) {
        System.out.println(activePlayer.name + "'s hand: " + activePlayer.cup.displayCup());
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
        if (!diceMap.containsKey(previousFaceBid) || diceMap.get(previousFaceBid) < previousDiceBid) {
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
}
