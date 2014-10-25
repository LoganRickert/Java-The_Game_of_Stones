package stonesthegame;

public class Player {
    private String name;
    private int playerId;
    private boolean humanOrBot;
    
    /**
     * Sets the player's name.
     * @param name String - Name of user.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the player's ID.
     * @param playerId int - ID of the user.
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    
    /**
     * Sets if the player is a human or bot.
     * True: Human
     * False: Bot
     * @param humanOrBot boolean - True: Human, False: Bot. 
     */
    public void setHumanOrBot(boolean humanOrBot) {
        this.humanOrBot = humanOrBot;
    }
    
    /**
     * Gets the name of the player.
     * @return String - The name of the player.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the playerID of the player.
     * @return int - The ID of the player.
     */
    public int getPlayerId() {
        return this.playerId;
    }
    
    /**
     * Gets whether or not the player is a human or bot.
     * @return boolean - True: Human, False: Bot.
     */
    public boolean getHumanOrBot() {
        return this.humanOrBot;
    }
}
