/**
* Creates a roommate.
*
*@author Richard Ding
*@version program7
*/

public class Roommate {
    private String name;
    private boolean isAlive;
    
    /**
     * Method to construct a roommate given just a string.
     * Assumes that the roommate is alive and makes call to another constructor.
     *@param name - String name of roommate
     */
    public Roommate(String name){
       this(name,true);
    }
    /**
     * Method to construct a Roommate given name and boolean for alive status
     * @param name - String name of roommate
     * @param isAlive - boolean status of if roommate is alive
     */
    public Roommate(String name, boolean isAlive) {
        this.name = name;
        this.isAlive = isAlive;
    }
    /**
     * Method to get the Name of the roommate
     * @return String - the name of the roommate.
     */
    public String getName(){
        return name;
    }
    
    /**
     * Method to get the living status of the player.
     * @return boolean - true if alive else false
     */
    public boolean getIsAlive(){
        return isAlive;
    }
    
    /**
     * Method to set the living status of the player to false.
     */
    public void isDead(){
        isAlive = false;
    }
}