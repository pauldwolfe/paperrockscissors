package com;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * An input stream that generates a random character of one of the 3 game input characters s=scissors, r=rock, p=paper
 */
public class InputStreamRandomGameChoiceCharacter extends InputStream {

    public int read() throws IOException {
        Random rand = new Random(); 
        
        /*
         * We want a random number from 1 to 3, exclude 0, to get this get a random number from 0 to 2 (upper bound 3 which is excluded),
         * then add 1 which gives a number from 1 to 3.
         */
        int intRandom = rand.nextInt(3) + 1;
        char inputChar = 'x';
        switch (intRandom) {
            case 1: inputChar = 'r'; break;
            case 2: inputChar = 'p'; break;
            case 3: inputChar = 's'; break;
        }

        return (byte)inputChar;
    }

}
