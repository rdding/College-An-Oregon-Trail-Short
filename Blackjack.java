//in progress
//to be completed by wed.
import java.io.Console;
import java.util.Scanner;

public class Blackjack implements Minigame{

   /* Note:Fields of Player
    private int money;
    p.setMoney(int n);
    p.getMoney();
    */
   private Player p;
   private Card [] deck;
   final static int deckLength = 52;
   
   public Blackjack(Player p){
      this.p = p;
      makeDeck();
      System.out.println("Welcome to Blackjack");
   }
   @Override
   public void start() {
      if(p != null && deck != null){
         this.shuffle();
         System.out.println("--Deck Shuffled--");
         int bet = getBet();
         //ask if you would like to play another game
            //if affirmative response 
      }
      exit();
      return;
   }

   @Override
   public void exit() {
      // TODO Auto-generated method stub
      
   }
   private int getBet(){
      Scanner myScanner = new Scanner(System.in);
      Scanner verifyInt;
      System.out.println("How much would you like to wager?");
      System.out.print("You have " + p.getMoney() + " dollars.\n");
      String s = myScanner.nextLine().trim();
      verifyInt = new Scanner(s);
      while(!verifyInt.hasNextInt()){
         System.out.println("How much would you like to wager?");
         System.out.print("You have " + p.getMoney() + " dollars.\n");
         s = myScanner.nextLine().trim();
         verifyInt = new Scanner(s);
      }
      int wager = verifyInt.nextInt();
      if(wager<0 || wager>p.getMoney()){
         System.out.println("ILLEGAL WAGER");
         return getBet();
      }
      return wager;
   }
   private void makeDeck(){
      deck = new Card [52];
      for(int i = 0; i<4; i++){ //for every suit
         char theSuit = ' ';
         if(i == 0){
            theSuit = 'S';
         }else if(i ==1){
            theSuit = 'D';
         }else if(i ==2){
            theSuit = 'H';
         }else if(i ==3){
            theSuit = 'C';
         }
         for(int j = 2; j<15; j++){ //for different cards
            if(j<10){
               deck[i*13 +(j-2)] = new Card(j+" ",theSuit);
            }else if(j==10){
               deck[i*13 +(j-2)] = new Card(j+"",theSuit);
            }else if(j == 11){
               deck[i*13 +(j-2)] = new Card("J ",theSuit);
            }else if(j == 12){
               deck[i*13 +(j-2)] = new Card("Q ",theSuit);
            }else if(j==13){
               deck[i*13 +(j-2)] = new Card("K ",theSuit);
            }else if(j==14){
               deck[i*13 +(j-2)] = new Card("A ",theSuit);
            }
         }
      }
   }
   private void shuffle(){
      for(int i = 0; i<deckLength; i++){
         int rand = (int) (Math.random() * deckLength);
         swapCards(i,rand);
      }
   }
   private void swapCards(int ind1, int ind2){
      Card temp = deck[ind1];
      deck[ind1] = deck[ind2];
      deck[ind2] = temp;
   }

   private class Card{
      String val;
      char suit;
      
      public Card(String val, char suit){
         this.val = val;
         this.suit = suit;
      }
      public String toString (){
         String s="";;
         s+="+-----+\n";
         s+="|" + val+"  " + suit + "|\n";
         s+="|     |\n";
         s+="|     |\n";
         if(val.equals("10")){
            s+="|" + suit+"  " + val + "|\n";
         }else{
            s+="|" + suit+"   " + val.trim() + "|\n";
         }
         s+="+-----+\n";
         return s;
         /*
         +-----+
         |10  H|
         |     |
         |     |
         |H  10|
         +-----+
          */
      }
   }
}
