
public class Party implements Minigame{
   
   private Player p;
   private int startHealth;
   private double bac;
   public Party (Player p){
      this.p = p;
      if(p.getAlcohol() ==0){
         System.out.println("You have no alcohol. leave");
      }
      bac = 0;
      this.start();
   }
   @Override
   public void start() {
      while(p.getAlcohol() >0){
         printStats();
      }
   }
   private void printStats(){
      System.out.println("You have " + p.getHealth());
      System.out.println("You have " + bac + "% bac.");
      int bad = (int) (bac*100)/2;
      System.out.print("Chance of having a bad time " + bad + "%");
   }
   
   @Override
   public void exit() {
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      
      System.out.println("--------------------------------------------------");
      System.out.println("----------------leaving the party-----------------");
      System.out.println("--------------------------------------------------");
      
   }

}
