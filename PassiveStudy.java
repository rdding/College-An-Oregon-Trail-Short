
public class PassiveStudy implements Minigame{
   private Player p;
   public PassiveStudy(Player p){
      this. p = p;
   }
   @Override
   public void start() {
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
            System.out.println("You got distracted by the clouds. no studying happened");
            System.out.println("No change in knowldege.");
         }else if(rand2 == 2){
            System.out.println("You could't study because you had to do landry.");
            System.out.print("No change in knowledge.");
         }else if(rand2 == 3){
            System.out.println("You were drafter into the third world war.");
            System.out.println("Unfortunately you did not survive...");
            p.die(false);
         }else{
            System.out.println("The library caught on fire. You died :(");
            p.die(false);
         }
      }else if(rand ==2){
         System.out.println("You had an incredible study sesison.");
         printKnowledge(25);
      }else{
         printKnowledge(10);
      }
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
   @Override
   public void exit() {//no code here. not required here     
   }
}
