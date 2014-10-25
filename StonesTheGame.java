package stonesthegame;

import java.util.Random;
import java.util.Scanner;

/**

 * The Game of Stones: The game begins with a pile of stones. The more players, the
 * bigger the pile. The person who takes the last stone loses.

 * This game is an adaptation to the simple game we built in 1180 class. It includes
 * objects, more than one player, player naming, and dynamic piles of rocks.

 * @author Logan Rickert

*/
public class StonesTheGame {
    
    // Declares the Scanner object.
    public static final Scanner keyboard = new Scanner(System.in);

    /**
     * Gets the initial size of the pile. The size of the pile will grow
     * as the number of players grow.
     * @param playerCount int - The amount of players playing.
     * @return int - How many stones there will be in the initial pile.
     */
    public static int getInitialPileSize(int playerCount) {
        // Declares the Random object.
        Random random = new Random();
        
        int initalPileSize;
        
        // Continue until the pile is between 25 and 50 rocks.
        do {
            initalPileSize = random.nextInt(50);
        } while(initalPileSize < 24);
        
        // Multiply the amount of rocks by the number of players and return the value.
        return initalPileSize * playerCount;
    }
    
    /**
     * Get how many players are going to be in the game.
     * @return int - How many players will be in the game.
     */
    public static int getPlayerCount() {
        int playerCount = 0;
        
        // Continue until playerCount is greater than 1 and is an integer.
        do {
            System.out.print("How many humans and bots total are playing? ");
            try {
                playerCount = keyboard.nextInt();
            } catch (Exception e) {
                keyboard.nextLine();
            }
            
            if(playerCount < 2) {
                System.out.println("Your number is too small or invalid!");
            }
        } while(playerCount < 2);
        
        // Return the playerCount.
        return playerCount;
    }
    
    /**
     * Get the names of players, if they are a bot, and their player ID and store
     * it in the Player playerArray.
     * @param playerArray The array to put the player information into.
     */
    public static void getPlayers(Player[] playerArray) {
        
        // Continue until all players have been gone through.
        for(int i = 0; i < playerArray.length; i++) {
            // Set to -1 so it is an invalid number.
            int isPlayerHuman = -1;
            String playerName = "";
            
            // Continue until isPlayerHuman is 0 or 1.
            do {
                System.out.print("Is player " + (i + 1) + " a bot or human? (0 bot, 1 human) ");
                try {
                    isPlayerHuman = keyboard.nextInt();
                } catch (Exception e) {
                    keyboard.nextLine();
                }
                
                if(isPlayerHuman < 0 || isPlayerHuman > 1)
                    System.out.println("That number is too high, low, or invalid!");
                
            } while(isPlayerHuman < 0 || isPlayerHuman > 1);
            
            // Continue until the playerName is a valid string.
            do {
                System.out.print("What is the name of player? ");
                try {
                    playerName = keyboard.nextLine();
                } catch (Exception e) {
                    keyboard.nextLine();
                }
                
                if(playerName.equalsIgnoreCase(""))
                    System.out.println("Player name is invalid!");
                
            } while(playerName.equalsIgnoreCase(""));
            
            // Set whether or not the player is human or bot.
            // True for human
            // False for bot
            if(isPlayerHuman == 1)
                playerArray[i].setHumanOrBot(true);
            else
                playerArray[i].setHumanOrBot(false);
            
            // Set the player's information.
            playerArray[i].setName(playerName);
            
            playerArray[i].setPlayerId(i);
        }
    }
    
    /**
     * BOT
     * Decide how many stones to remove. If it is greater than 4, remove a random
     * amount between 1 and 4. If the number is between 2 and 3, remove 1. If the number
     * is 1, remove 1.
     * @param pileOfStones int - The amount of stones left in the pile.
     * @return int - How many stones the bot wants to remove from the pile.
     */
    public static int removeStonesBot(int pileOfStones) {
        int stonesRemoved;
        
        if(pileOfStones > 4) {
            Random random = new Random();
            stonesRemoved = random.nextInt(4) + 1;
        } else if(pileOfStones == 1) {
            stonesRemoved = 1;
        } else {
            // Pile is between 2 and 3.
            stonesRemoved = pileOfStones - 1;
        }
        
        // Returns how many stones the bot wants to remove.
        return stonesRemoved;
    }
    
    /**
     * HUMAN
     * Ask the user how many stones they want to remove.
     * @param pileOfStones int - The amount of stones left in the pile.
     * @return int - How many stones the human wants to remove from the pile.
     */
    public static int removeStonesHuman(int pileOfStones) {
        int stonesRemoved = 0;
        boolean isValidMove;
        
        // Continue until the amount removed is a valid number.
        do {
            System.out.print("How many stones do you wish to remove? (1-4) ");
            
            try {
                stonesRemoved = keyboard.nextInt();
            } catch (Exception e) {
                keyboard.nextLine();
            }
            
            // If the user tries to remove more than 4 or less than 1 or more than the
            // amount in the pile (IE: 2 stones left, tries to remove 4).
            if(stonesRemoved < 1 || stonesRemoved > 4 || stonesRemoved > pileOfStones) {
                System.out.println("That is not a valid amount.");
                isValidMove = false;
            } else {
                isValidMove = true;
            }
            
        } while(!isValidMove);
        
        // Return how many stones the human wants to remove.
        return stonesRemoved;
    }
    
    /**
     * Gets the next player. If the current player is the last player, cycle
     * to player 0, else increment by 1.
     * @param currentPlayer int - Who is the current player playing.
     * @param players Player[] - The list of players playing
     * @return int - Who the current player should be.
     */
    public static int nextPlayer(int currentPlayer, Player[] players) {
        int amountOfPlayers = players.length-1;
        
        // If currentPlayer is the last player, cycle around to the first player.
        if(currentPlayer == amountOfPlayers)
            currentPlayer = 0;
        else
            currentPlayer++;
        
        // Return who the current player should be.
        return currentPlayer;
    }

    public static void main(String[] args) {
        // Sets who the current player is.
        int currentPlayer = 0;
        
        // Gets how many people are playing from the user.
        int playerCount = getPlayerCount();
        
        // Creates an array of Player.
        Player[] players = new Player[playerCount];
        
        // Declare all places in the array as a Player object.
        for(int i = 0; i < players.length; i++) {
            players[i] = new Player();
        }
        
        // Gets the names, if they are a human or bot, and their player ID from
        // the user.
        getPlayers(players);
        
        // Gets the initial amount in the pile of stones.
        int pileOfStones = getInitialPileSize(playerCount);   
        
        // Continue while there are rocks in the pile.
        while(pileOfStones > 0) {
            // Tell the users how many rocks are left.
            System.out.println("\nThere are " + pileOfStones + " left in the pile!");
            
            int stonesRemoved = 0;
            
            // Continue until currentPlayer == Player.playerID:
            //     Check if they are human or bot
            //     If bot, run bot method, tell how many stones were removed, and break to while loop.
            //     If human, ask how many stones they want to remove, tell how many stones were removed, and break to while loop.
            for(int i = 0; i < players.length; i++) {
                if(currentPlayer == players[i].getPlayerId()) {
                    if(players[i].getHumanOrBot() == false) {
                        stonesRemoved = removeStonesBot(pileOfStones);
                        System.out.println("\n" + players[i].getName() + " removed " + stonesRemoved + " stones.");
                    } else {
                        stonesRemoved = removeStonesHuman(pileOfStones);
                        System.out.println("\n" + players[i].getName() + " removed " + stonesRemoved + " stones.");
                    }
                    break;
                }
            }
            
            // Remove the number of stones from the pile.
            pileOfStones -= stonesRemoved;
            
            // Cycle to the next player.
            currentPlayer = nextPlayer(currentPlayer, players);
        }
    }
    
}
