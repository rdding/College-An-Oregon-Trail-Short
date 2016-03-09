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
    
    public int getAlcohol(){
        return alcohol;
    }
    
    public int getBooks(){
        return books;
    }
    
    public int getDegreeLength(){
        return degreeLength;
    }
    
    public int getDrugs(){
        return drugs;
    }
    
    public int getFood(){
        return food;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getKnowledge(){
        return knowledge;
    }
    
    public int getMoney(){
        return money;
    }
    
    public String getPlayerName(){
        return name;
    }
    
    public int getProgression(){
        return progression;
    }
    
    public Roommate getRoommate(int index){
        return roommates[index];
    }
    
    public void setAlcohol(int alcohol){
        this.alcohol = alcohol;
    }
    
    public void setBooks(int books){
        this.books = books;
    }
    
    public void setDrugs(int drugs){
        this.drugs = drugs;
    }
    
    public void setFood(int food){
        this.food = food;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public void setKnowledge(int knowledge){
        this.knowledge = knowledge;
    }
    
    public void setMoney(int money){
        this.money = money;
    }
    
    public void setProgression(int progression){
        this.progression = progression;
    }
}