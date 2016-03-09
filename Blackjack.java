import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
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
   private int startingCapital;
   
   public Blackjack(Player p){
      this.p = p;
      startingCapital = p.getMoney();
      makeDeck();
      System.out.println("Welcome to Blackjack");
   }
   private void printHand(ArrayList<Card> c){
      if(c == null){
         return;
      }
      for(int j = 0; j<6;j++){
         for(int i = 0; i<c.size(); i++){
            System.out.print(c.get(i).toString().substring(j*7+j, (j+1)*7+j)+ "  ");
         }
         System.out.println();
      }

   }
   private void printDealerHidden(ArrayList<Card> c){
      if(c == null){
         return;
      }
      for(int j = 0; j<6;j++){
         for(int i = 0; i<c.size(); i++){
            if(i == 0){
               System.out.print("+++++++  ");
            }else{
               System.out.print(c.get(i).toString().substring(j*7+j, (j+1)*7+j)+ " ");
            }
         }
         System.out.println();
      }

   }
   @Override
   public void start() {
      if(p != null && deck != null){
         this.shuffle();
         System.out.println("--Deck Shuffled--");
         int bet = getBet();
         int cardPtr = 0;
         int outcome = -1; //if
         ArrayList <Card> playerHand = new ArrayList<>();
         ArrayList <Card> dealerHand = new ArrayList<>();
         //initial dealing of cards
         playerHand.add(deck[cardPtr++]);
         playerHand.add(deck[cardPtr++]);
         dealerHand.add(deck[cardPtr++]);
         dealerHand.add(deck[cardPtr++]);
         ///////////////////////////
         boolean endOfGame = false;
         char [] hitStand = {'H','S'};
         char [] yesNo    = {'Y','N'};
         do{
            printFirstHalfHands(dealerHand,playerHand);
            //check for 21 with two cards
            if(this.getHighScore(playerHand) == 21 && playerHand.size() == 2){
               outcome = 3;
               System.out.println("Congratulations - Blackjack");
               break;
            }
            if(this.getHighScore(dealerHand) == 21 && dealerHand.size() == 2){
               outcome = 2;
               System.out.println("Dealer Had Blackjack - Instant Lose");
               break;
            }
            System.out.print("Hit or Stand? (H|S) ");
            char validInput = getValidChar( hitStand);
            if(validInput == 'H'){
               System.out.println("Player - Hit");
               playerHand.add(deck[cardPtr++]);
               if(getLowScore(playerHand)>21){
                  System.out.print("You went over. BUST");
                  outcome = 2;
                  break;
               }
            }else{// player stand
               endOfGame = true;
               printOpenHands(dealerHand,playerHand);
               while(this.getHighScore(dealerHand)<17){
                  dealerHand.add(deck[cardPtr++]);
                  printOpenHands(dealerHand,playerHand);                
               }
               int playerMax = getMaxLegal(playerHand);
               int dealerMax = getMaxLegal(dealerHand);
               if(playerMax >dealerMax){
                  outcome = 1;
               }else if(playerMax == dealerMax){
                  outcome = 0;
               }else{
                  outcome = 2;
               }
               
            }
            
         }while(!endOfGame);
         modifyPlayerWealth(outcome,bet);
         System.out.println("Would You Like to Play Again? (Y|N)");
       
         if(getValidChar(yesNo) == 'Y'){
            this.start();
         }else{
            this.exit();
         } 
      }
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
   private int getLowScore(ArrayList<Card> c){
      int score = 0;
      for(int i =0; i<c.size(); i++){
         String cardVal= c.get(i).val.trim();
         char front = cardVal.charAt(0);
         if(front == '1' || front == 'J' || front == 'Q' || front == 'K'){
            score+=10;
         }else if(front == 'A'){
            score+=1;
         }else{
            score+= Integer.parseInt(cardVal);
         }
      }
      return score;
   }
   private int getHighScore(ArrayList<Card> c){
      int lowScore = getLowScore(c);
      for(int i = 0; i<c.size(); i++){
         if(c.get(i).val.trim().equals("A")){
            return lowScore + 10;
         }
      }
      return lowScore;
   }
   private String showScore(ArrayList<Card> c){
      int low = getLowScore(c);
      int high = getHighScore(c);
      if(high>21 || low == high){
         return low+"";
      }else if(high == 21){
         return "21";
      }
      return low + "/" + high;
   }
   private int getCardVal(Card c){
      String cardVal= c.val.trim();
      char front = cardVal.charAt(0);
      if(front == '1' || front == 'J' || front == 'Q' || front == 'K'){
         return 10;
      }else if(front == 'A'){
         return 1;
      }else{
         return Integer.parseInt(cardVal);
      }
   }
   private String getHiddenScore(Card c){
      int val = getCardVal(c);
      if(val == 1){
         return "1/11";
      }
      return val +"";
   }
   //0 - Tie Push no money changes hands
   //1 - Player Wins
   //2 - Player Wins
   //3 - Blackjack
   private void modifyPlayerWealth(int outcome, int bet){
      int delta = 0;
      if(outcome == 0){
         System.out.println("PUSH - no change in bankroll");
         return;
      }
      else if(outcome == 1){
         delta = bet;
      }else if(outcome ==2){
         delta = bet *-1;
      }else if(outcome ==3){
         delta = bet * 3 /2;
      }
      if(delta<0 && Math.abs(delta) >= p.getMoney()){
         System.out.print("Change in Bankroll: -" + delta);
         p.setMoney(0);
         System.out.println("YOU HAVE BEEN KICKED OUT OF THE CASINO FOR LACK OF FUNDS!!!");
         this.exit();
      }else{
         if(p.getMoney() + delta <0){
            System.out.println("Change in Bankroll: " + (Integer.MAX_VALUE - p.getMoney()));
            p.setMoney(Integer.MAX_VALUE);
            System.out.println("TOO MUCH MONEY. YOU HAVE BEEN KICKED OUT OF THE CASINO!!!");
            System.out.println("what are you doing in college anyways?"); 
            this.exit();
         }
         System.out.println("Change in Bankroll: " + delta);
         p.setMoney(p.getMoney() + delta);
      }
   }
   private void printFirstHalfHands(ArrayList<Card> dealerHand, ArrayList<Card> playerHand){
      System.out.println("Dealer Hand: "+getHiddenScore(dealerHand.get(1)));
      printDealerHidden(dealerHand);
      System.out.println("\n\nPlayer Hand: " + showScore(playerHand));
      printHand(playerHand);
      System.out.println("--------------------------------------------------");
   }
   private void printOpenHands(ArrayList<Card> dealerHand, ArrayList<Card> playerHand){
      System.out.println("Dealer Hand: "+showScore(dealerHand));
      printHand(dealerHand);
      System.out.println("\n\nPlayer Hand: " + showScore(playerHand));
      printHand(playerHand);
      System.out.println("--------------------------------------------------");
   }
   private char getValidChar(char []arr){
      Scanner myScanner = new Scanner(System.in);
      String s = myScanner.nextLine().trim().toUpperCase();
      while(s.length() != 1){
         System.out.println("Please input one of the following chars: " + Arrays.toString(arr));
         s = myScanner.nextLine().trim().toUpperCase();
      }
      char c = s.charAt(0);
      for(int i = 0; i<arr.length;i++){
         if(c == arr[i]){
            return c;
         }
      }
      return getValidChar(arr);
   }
   private int getMaxLegal(ArrayList<Card> c){
      int low = getLowScore(c);
      int high = getHighScore(c);
      if(high>21){
         return low;
      }
      return high;
   }
   
   //CARD INNER CLASS
   
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
