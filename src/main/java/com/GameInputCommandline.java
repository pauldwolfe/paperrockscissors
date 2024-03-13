package com;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Class that reads input from the commandline interface.
 */
public class GameInputCommandline extends GameInputAbstract{

    private Scanner scanner = null;

    GameInputCommandline(InputStream sourceInput) {
        super(sourceInput);
    }

    /**
     * Request the game input from the user, this is one of 4 characters 's'/'p'/'r'/'x'.
     * The input is validated, if the user enters any otehr input an error is printed and
     * the user can continue to enter new input.
     * 
     * @return  the character entered, this shall be one of 's'/'p'/'r'/'x'.
     */
    public GameInputChoice requestGameChoiceInput() {
        GameInputChoice input = GameInputChoice.NONE;

        if (scanner == null) {
            scanner = new Scanner(sourceInput);
        }

        if (scanner != null) {
            char inputChar = ' ';    
            boolean inputValid = false;      

            while (!inputValid) {
                System.out.println("Enter a character for your choice (s=scissors, p=paper, r=rock, x=exit game): ");
                String inputChoice = scanner.next();

                if (inputChoice.length() == 1) {
                    inputChar = inputChoice.charAt(0);
                    if ("sprx".indexOf(inputChar) != -1) {
                        inputValid = true;
                        input = GameInputChoice.convertCharToGameInputChoice(inputChar);
                    }
                }
                if (!inputValid) {
                    System.out.println(TextColors.RED + "\nInvalid choice, please try again." + TextColors.WHITE);
                }
            }
        }

        return input;
    }

    /**
     * Requests the user to enter a string. If the user presses 'x' to exit then null is returned.
     * A request string must be provided to request the input.
     * 
     * @param request   the request printed to request the user's input.
     * @return          the string the user entered, or null if the user pressed the 'x' key to end.
     */
    public String requestString(String request) {
        String inputString = "Error";

        if (scanner == null) {
            scanner = new Scanner(sourceInput);
        }

        if (scanner != null) {                
            boolean inputStringValid = false; 
            while (!inputStringValid) {
                System.out.println("\n" + request + " (x=exit): ");
                inputString = scanner.next();
                if (inputString.length() == 1) {
                    if (inputString.charAt(0) == 'x') {
                        return null;
                    }
                }
                if (inputString.length() >= 1) {
                    inputStringValid = true;
                }
                if (!inputStringValid) {
                    System.out.println("Invalid string, please try again.");
                }
            }
        }

        return inputString;
    }

}
