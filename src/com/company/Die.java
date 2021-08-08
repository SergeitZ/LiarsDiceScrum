package com.company;

import java.util.Random;

public class Die {

    public int numberOfSides;
    public int faceUpValue;

    public Die() {

        numberOfSides = 6;
    }

    public void roll() {
        Random random = new Random();

        faceUpValue = random.nextInt(numberOfSides) + 1;
    }
}
