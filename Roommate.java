/**
* Creates a roommate.
*
*@author Richard Ding
*@version program7
*/

public class Roommate {
    private String name;
    private boolean isAlive;
    
    public Roommate(String name){
       this(name,true);
    }
    public Roommate(String name, boolean isAlive) {
        this.name = name;
        this.isAlive = isAlive;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean getIsAlive(){
        return isAlive;
    }
    
    public void isDead(){
        isAlive = false;
    }
}