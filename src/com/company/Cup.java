package com.company;
import java.util.ArrayList;
import java.util.List;

public class Cup {
    List<Die> dice = new ArrayList<>();

    //TODO: Add 5 die per player
    //Use HashMap to store all face up value as keys and number of occurrences as values
    public Cup() {
        while(dice.size() < 5) {
            dice.add(new Die());
        }
    }

    public void roll() {
        for (Die die : dice) {
            die.roll();
        }
    }
    //TODO: Display activePlayer hand and ALL dice in play
    public String displayCup() {
        String output = "";
        for (Die die : dice) {
            output += die.faceUpValue + " ";
        }
        return output.trim();
    }
}
