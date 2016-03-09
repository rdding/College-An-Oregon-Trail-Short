
public class MyTestMain {
   public static void main(String [] args){
      Player p = new Player(10, 1000, "Larry", null);
      Blackjack b = new Blackjack(p);
      b.start();
   }
}
