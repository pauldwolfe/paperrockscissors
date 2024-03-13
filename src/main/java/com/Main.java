package com;

public class Main {
    public static void main(String[] args) {
        Game theGame = new Game();

        try {
            theGame.Play();
            
            System.out.println("\n!!!!!!!!! GAME ENDED !!!!!!!!!");
            System.out.println("\nSUMMARY");
            System.out.print("-------");

            if (theGame.getRounds().size() > 0) {
                System.out.print(theGame.toString());
            } else {
                System.out.println("\nNo rounds were played\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}