package com;

/**
 * Class that represent a player of the game. The user is assigned an input used to request the 
 * input from the user, this input can differ depending on the player kind.
 */
public class Player {
    private String name = "Error - no name";
    GameInputAbstract gameInput = null;

    /**
     * Constructor.
     * 
     * @param name      the name of the player.
     * @param gameInput the input instance that requests the input from the user.
     */
    Player(String name, GameInputAbstract gameInput) {
        this.name = name;
        this.gameInput = gameInput;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public GameInputChoice getNextInput() {
        return gameInput.requestGameChoiceInput();
    }

    /**
     * Override equals opertor to enable equality check of two Player classes.
     */
    @Override
    public boolean equals(Object object) {
        // If the object is compared with itself then return true  
        if (object == this) {
            return true;
        }

        /* Check if object is an instance of Player or not
          "null instanceof [type]" also returns false */
          if (!(object instanceof Player)) {
            return false;
        }

        // typecast object to Player so that we can compare data members. 
        Player player = (Player)object;

        // Compare the players' names and return accordingly. 
        return name.equals(player.name);
    }
}
