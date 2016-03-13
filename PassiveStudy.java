
public class PassiveStudy implements Minigame{
   private Player p;
   
   /**
    * Constructor for the PassiveStudy object to be used in stops.
    * @param p - Player that will be making the stop
    */
   public PassiveStudy(Player p){
      this. p = p;
   }
   /**
    * Method found in minigame interface implemented to do the protocol for the object.
    */
   @Override
   public void start() {
      if(p.getBooks()>0){
         System.out.println("You have " + p.getBooks() + " to study with");
         
         System.out.print("How many books would you like to use to study with?");
         System.out.println("(1 - " + p.getBooks() + ")" );
         int booksUsed = College.readNum(p.getBooks(), "HOW MANY BOOKS?");
         if(booksUsed >0){
            System.out.print("Because of your books you gained + " + booksUsed + " knowledge");
            p.setBooks(p.getBooks()-booksUsed);
            p.setKnowledge(p.getKnowledge() + booksUsed);
            if(p.getBooks()<0){
               System.out.println("Somehow you used too many books. interesting...");
               p.setBooks(0);
            }        
         }
      }
      int rand = (int) (Math.random() * 20);
      if(rand < 1){ //Failed study session
         System.out.println("You had a pretty bad study session...");
         int rand2 = (int) (Math.random() *5);
         if(rand2 == 0){
            System.out.println("You couldn't study because of a hangover. You lost knowledge.");
            int loseKnowledge = 5;
            if(p.getKnowledge()<5){
               loseKnowledge = p.getKnowledge();
            }
            printKnowledge(loseKnowledge);
         }else if(rand2 == 1){
            System.out.println("You got distracted by the clouds. No studying happened");
            System.out.println("No change in knowldege.");
         }else if(rand2 == 2){
            System.out.println("You could't study because you had to do laundry.");
            System.out.print("No change in knowledge.");
         }else if(rand2 == 3){
            System.out.println("You were drafted into the third world war.");
            System.out.println("Unfortunately you did not survive...");
            p.die(false);
         }else{
            System.out.println("The library caught on fire. You died :(");
            p.die(false);
         }
      }else if(rand ==2){
         System.out.println("You had an incredible study session.");
         printKnowledge(25);
      }else{
         printKnowledge(10);
      }
      System.out.println("Studying hurts. Health -20");
      p.setHealth(p.getHealth()-20);
      if(p.getHealth()<0){
         p.setHealth(0);
      }
      if(p.getHealth()==0){
         System.out.println("What a sad way to go. Died while studying...");
         p.die(false);
      }
      System.out.println("New Health: " + p.getHealth());
      System.out.println("--------------------------------------------------");
   }
   private void printKnowledge(int knowledgeChange){
      if(knowledgeChange<0){
         System.out.println("LOSE: " + knowledgeChange + " Knowledge points!");
      }else{
         System.out.println("GAIN: " + knowledgeChange + " Knowledge points!");
      }
      p.setKnowledge(p.getKnowledge()+knowledgeChange);
      System.out.println("You now have " + p.getKnowledge() + " knowledge.");
   }
   /**
    * Method in the interface of Minigame that is not implemented or used
    * in this class
    */
   @Override
   public void exit() {//no code here. not required here     
   }
}