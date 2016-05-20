import java.io.File;
import java.io.FileReader;

public class Drugs implements Minigame{

   @Override
   public void start() {
      File f1 = new File("drugs1.txt");
      File f2 = new File("drugs2.txt");
      try{
         FileReader fR = new FileReader(f1);
         int c = fR.read();
         while(c != -1){
            System.out.print((char)c);
            c = fR.read();
         }
         fR = new FileReader(f2);
         c = fR.read();
         while(c != -1){
            System.out.print((char)c);
            c = fR.read();
         }
      }catch(Exception ex){
         System.out.println(ex);
      }     
   }

   @Override
   public void exit() {//no code in play
   }

}