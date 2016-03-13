
public class Party implements Minigame{
   
   private Player p;
   private int startHealth;
   private double bac;
   private int drinksHad;
   /**
    * A constructor to get this party started with the player.
    * @param p - the player in the party. That is player user controls when called
    */
   public Party (Player p){
      this.p = p;
      if(p.getAlcohol() ==0){
         System.out.println("You have no alcohol. leave");
      }
      startHealth = p.getHealth();
      bac = 0;
      drinksHad = 0;
      this.start();
   }
   /**
    * A method to actually start the party. Note it is called by the constructor and
    * no additional calls are required
    */
   @Override
   public void start() {
      while(p.getAlcohol() >0){
         printStats();
         System.out.println("Would you like to take a shot? (Y|N)" );
         char [] options = {'Y','N'};
         char choice = Blackjack.getValidChar(options);
         if(choice == 'Y'){
            p.setAlcohol(p.getAlcohol()-1);
            drinksHad++;
            boolean success = doTheAlcohol();
            if(success){
               benefitsOfAlcohol();
            }else{
               System.out.println("That didn't go too well...");
               haveConsequence();
               break;
            }
         }else{
            break;
         }
      }
      exit();
   }
   private void benefitsOfAlcohol(){
      System.out.println("you had a great time with that drink...");
      if(p.getHealth()<=90){
         System.out.println("Health +10");
         p.setHealth(p.getHealth()+10);
      }else{
         System.out.println("YOU HAVE MAX HELATH!!!!!!!!!!!");
         p.setHealth(100);
      }
   }
   private void haveConsequence(){
      if(bac>=0.3){
         System.out.println("you died of too much alcohol...");
         p.die(false);
      }else if(bac>=0.2){
         if(p.getHealth()<10){
            System.out.println("You now have 1 health!!!");
            p.setHealth(1);
         }else{
            System.out.println("You now only have 10 health!!!");
            p.setHealth(10);
         }
      }else{
         System.out.println("Unruly disturbance... get fined");
         if(p.getMoney()<25){
            System.out.println("-"+p.getMoney()+ "dollars");
            p.setMoney(0);
         }else{
            System.out.println("-25 dollars");
            p.setMoney(p.getMoney()-25) ;
         }
      }
   }
   private boolean doTheAlcohol(){
      int bad = getChanceBadTime();
      int rand = (int)(Math.random() * 100);
      bac +=0.03;
      return(bad<rand);
   }
   
   private void printStats(){
      System.out.println("You have " + p.getHealth() + " health.");
      System.out.println("You have " + bac + "% bac.");
      System.out.print("Chance of having a bad time " + getChanceBadTime() + "%");
   }
   private int getChanceBadTime(){
      return (int) (1+bac*100)/2;
   }
   /**
    * Public method specified to leave the party. Why would you leave the party. Like
    * seriously
    */
   @Override
   public void exit() {
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      System.out.print("You had a total of " + drinksHad + " drinks.");
      System.out.println("Your health changed by " + (p.getHealth() -startHealth) );
      System.out.println("You ended the night with a BAC of "  + bac + "!!!");
      System.out.println("--------------------------------------------------");
      System.out.println("----------------leaving the party-----------------");
      System.out.println("--------------------------------------------------");      
   }
}