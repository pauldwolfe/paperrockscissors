package com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameInputTests {

    InputStreamRandomGameChoiceCharacter randomcharGenerator = new InputStreamRandomGameChoiceCharacter();
    ByteArrayInputStream in;

    @BeforeEach
    public void setUp() {
        int randomCharInt;
        byte randomCharByte = (byte)'x';
        try {
            randomCharInt = randomcharGenerator.read();

            if (randomCharInt != -1) {
                randomCharByte = (byte)randomCharInt;
            }
            byte[] inputBytes = {randomCharByte};
            in = new ByteArrayInputStream(inputBytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            randomcharGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
	void getsUserInput() {
		GameInputCommandline gameInputCli = new GameInputCommandline(in);
        GameInputChoice actualChoice = gameInputCli.requestGameChoiceInput();
        GameInputChoice expectedChoices[] = {GameInputChoice.PAPER, GameInputChoice.ROCK, GameInputChoice.SCISSORS, GameInputChoice.NONE};
        List<GameInputChoice> expectedChoicesList = Arrays.asList(expectedChoices);

		assertTrue(expectedChoicesList.contains(actualChoice));
	}

    @Test
	void getsComputerInput() {
		GameInputComputer gameInputCli = new GameInputComputer(in);
        GameInputChoice actualChoice = gameInputCli.requestGameChoiceInput();
        GameInputChoice expectedChoices[] = {GameInputChoice.PAPER, GameInputChoice.ROCK, GameInputChoice.SCISSORS, GameInputChoice.NONE};
        List<GameInputChoice> expectedChoicesList = Arrays.asList(expectedChoices);

		assertTrue(expectedChoicesList.contains(actualChoice));
	}


}
