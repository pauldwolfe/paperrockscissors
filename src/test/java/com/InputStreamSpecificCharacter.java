package com;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream class to return one specific character for testing purposes
 */
public class InputStreamSpecificCharacter extends InputStream {

    char theInputCharacter = 'x';

    InputStreamSpecificCharacter(char theCharacter) {
        this.theInputCharacter = theCharacter;
    }

    public int read() throws IOException {
        return (byte)theInputCharacter;
    }
}
