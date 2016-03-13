public class Piracy implements Minigame{

   private Player p;
   private int startingTextbooks;
   private int moneyStarting;
   
   /**
    * The constructor for the piracy minigame. Used in the stops and takes
    * player in.
    * @param p - Player that user controls and stored as instance variable to be used.
    */
   public Piracy(Player p){
      this.p =p;
      startingTextbooks = p.getBooks();
      moneyStarting = p.getMoney();
      System.out.println("so you want to pirate some textbooks do you?");
   }
   
   /**
    * Method found in the minigame interface to launch the piracy minigame.
    */
   @Override
   public void start() {
      System.out.println("--------------------------------------------------");
      System.out.println("So you want to pirate a textbook? (Y|N)");
      printStats();
      char [] options = {'Y','N'};
      char choice = Blackjack.getValidChar( options);
      if(choice == 'Y'){
         tryPirating();
      }else{
         exit();
      }
      System.out.println("Do you want to do that again?");
      if(choice == 'Y'){
         System.out.println("edgy");
         start();
      }else{
         exit();
      }
   }
   /**
    *Method defined in the Minigame interface and called by the start method
    *when the player is done running the code for start. 
    */
   @Override
   public void exit() {
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      System.out.print("You pirated " +(p.getBooks()-startingTextbooks) + " books" );
      if ( (p.getMoney()-moneyStarting) != 0){
         System.out.println("!!!!You lost " +(moneyStarting - p.getMoney() ) + " dollars!!!!");
      }
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
   }
   private void badThingsHappen(){
      int outcome = (int) (Math.random() *10);
      if(outcome<3){
         System.out.println("You were able to get out of trouble by blaming it on a pop up");
      }else if(outcome <=6){
         if(p.getMoney()>=1){
            System.out.println("YOU ARE FINED 1 dollar");
            p.setMoney(p.getMoney()-1);
         }else{
            System.out.println("You die of dysentry :(");
         }
      }else{
         System.out.println("Your dorm was raided by the FBI");
         System.out.println("... while your roomates are making drugs");
         System.out.println("--a firefight ensues--");
         System.out.println("Unfortunately you were shot and killed...\n RIP in peace");
         p.die(false);
      }
      
   }
   private void printStats(){
      System.out.println("Books: " + p.getBooks());
      System.out.println("Risk of getting caught: " + (p.getBooks() +"%") );
      System.out.println("Money: " + p.getMoney());
   }
   private void tryPirating(){
      int rand = (int) (Math.random()*100);
      if(rand>p.getBooks()){
         System.out.println("Success\t Books++");
         p.setBooks(p.getBooks()+1);
      }else{
         System.out.println("You got caught");
         badThingsHappen();
      }
   }
}