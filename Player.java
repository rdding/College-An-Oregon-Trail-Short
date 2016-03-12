/**
* Creates a player.
*
*@author Richard Ding
*@version program7
*/

public class Player {
    private int alcohol;
    private int books;
    private int degreeLength;
    private int drugs;
    private int food;
    private int health;
    private int knowledge;
    private int money;
    private String name;
    private int progression;
    private Roommate[] roommates;
    /**
     * The only constructor to create the player that will go through the college game.
     * Note that many of the fields are mutable through set methods while others are not.
     * Player is constructed only once by main and accepts parameters that should not change.
     * 
     * @param degreeLength - int the length of the player's degree
     * @param money - int the amount of money that a player has
     * @param name - String the name that user gives to the player
     * @param roommates - an array of references to roommates
     */
    public Player(int degreeLength, int money, String name, Roommate[] roommates) {
        alcohol = 0;
        books = 0;
        this.degreeLength = degreeLength;
        drugs = 0;
        food = 0;
        health = 100;
        knowledge = 0;
        this.money = money;
        this.name = name;
        progression = 0;
        this.roommates = roommates;
    }
    /**
     * Method to return the amount of alcohol the player is carrying on self
     * Note the value returned should never be negative and does not require
     * any parameters.
     * Given that instance variables are private, only way for other classes and
     * objects to get this value
     * @return alcohol - int for how much carried by player
     */
    public int getAlcohol(){
        return alcohol;
    }
    /**
     * Method to return the amount of books the player is carrying on self
     * Value returned always non-negative.
     * @return books - int for how many carried
     */
    public int getBooks(){
        return books;
    }
    /**
     * Method to return the degree length of player. Varies according to major player
     * selects. Value returned always non-negative
     * @return degreeLength - int length of the player's degree
     */
    public int getDegreeLength(){
        return degreeLength;
    }
    /**
     * Method to return drugs that player has. Value returned always non-negative.
     * @return drugs - int of drugs carried by the player
     */
    public int getDrugs(){
        return drugs;
    }
    /**
     * Method to return the amount of food that the player has stored. value always non-negative.
     * @return food - int of food carried by the player
     */
    public int getFood(){
        return food;
    }
    /**
     * Method to get the health of the player. Value always non-negative.
     * @return health - int the player's health
     */
    public int getHealth(){
        return health;
    }
    /**
     * Method to get the knowledge that the player has. Value always non-negative.
     * @return knowledge - int the plaer's knowledge
     */
    public int getKnowledge(){
        return knowledge;
    }
    /**
     * Method to return the player's money. Non-negative.
     * @return money - int the player's money 
     */
    public int getMoney(){
        return money;
    }
    /**
     * Method to return the player's name (String).
     * @return name - String of the player's name
     */
    public String getPlayerName(){
        return name;
    }
    /**
     * Method to return the progression of the character.
     * Progression is degrees completed by the character
     * @return progression - int degrees that the player has done
     */
    public int getProgression(){
        return progression;
    }
    /**
     * Method to return reference to the roomate player has. 
     * Although player has many roomates, return one specified at index.
     * @param index - the index loc of different roomates in array
     * @return Roomate - reference to the given roomate
     */
    public Roommate getRoommate(int index){
        return roommates[index];
    }
    /**
     * Method to set the amount of alcohol that the player has.
     * @param alcohol - int shoulf be non-negative
     */
    public void setAlcohol(int alcohol){
        this.alcohol = alcohol;
    }
    /**
     * Method to set the number of books that the player has.
     * @param books - int Should be non-negative
     */
    public void setBooks(int books){
        this.books = books;
    }
    /**
     * Method to set the drugs that the player may have
     * @param drugs - int non-negative value of drugs that the player has
     */
    public void setDrugs(int drugs){
        this.drugs = drugs;
    }
    /**
     * Method to set the food that the player has.
     * @param food - int non negative value of food player has now
     */
    public void setFood(int food){
        this.food = food;
    }
    /**
     * Method to set the health of the player.
     * @param health - int should be between 0 and 100
     */
    public void setHealth(int health){
        this.health = health;
    }
    /**
     * Method to set the knowledge of the player
     * @param knowledge - int should be non-negative
     */
    public void setKnowledge(int knowledge){
        this.knowledge = knowledge;
    }
    /**
     * Method to set the money of the player 
     * @param money
     */
    public void setMoney(int money){
        this.money = money;
    }
    /**
     * 
     * @param progression
     */
    public void setProgression(int progression){
        this.progression = progression;
    }
    /**
     * 
     * @param isAlive
     */
    public void die(boolean isAlive){
        System.out.println("CONGRATULATIONS, YOU PLAYED YOURSELF.");
        System.out.println("YOUR TOMBSTONE READS:");
        System.out.println("HERE LIES: " +getPlayerName());
        System.out.println("REMEMBERED BY NO ONE.");
        System.exit(0);
    }
}