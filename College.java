/**
* Runs the College game.
*
*@author Richard Ding
*@version program7
*/
import java.util.Scanner;

public class College {
   /**
   *Runs the game.
   *
   *@param args the user input passed into the game.
   */
    public static void main(String[] args){
       //All variable names here
       int degreeChoice;
       String playerName;
       Roommate[] roommates;
       int degreeLength = -1;
       int money = -1;
       ///////////////////////////////
       
        welcomeCaller();
        degreeChoice = characterSelect();
        playerName = nameSelect("\nWhat is your legal name?(Verified via NSA databases)");       
        roommates = roommateCreate();
        
        if(degreeChoice == 1){
           degreeLength = 30;
           money = 500;  
        }else if (degreeChoice == 2) {
            degreeLength = 24;
            money = 400;
        } else if (degreeChoice == 3){
            degreeLength = 18;
            money = 300;
        }
        /////////////////////////////////////
        Player player = new Player(degreeLength, money, playerName, roommates);
        System.out.println("\nBefore you get fucked by an unstable financial future");
        System.out.println("you should blow all your money on booze and cocaine.");
        System.out.println("You have $" +player.getMoney() + " in cash.");
        shop(player);
        gameLoop(player, degreeLength, money, roommates, playerName);
        player.die(true);
    }
    
    private static int characterSelect(){
        displayRoles();
        int choice = readNum(4, "\nWhat is your choice?");
        if(choice == 4){
            roleInfo();
            choice = characterSelect();
        }
        return choice;
    }
    
    private static void displayRoles() {
       System.out.println("\nWHAT DEGREE DO YOU WANT WHEN YOU DIE?");
       System.out.println("   1. Computer Science");
       System.out.println("   2. AgriBusiness");
       System.out.println("   3. English");
       System.out.println("   4. LEARN ABOUT EACH SHITTY ROLE");
   }
    
    private static void displayStopChoices(){
       System.out.println("1. GET FUCKIN' WASTED");
       System.out.println("2. GET HIGH");
       System.out.println("3. PIRATE SOME TEXTBOOKS, FUCK YEAH");
       System.out.println("4. BURN BOOKS AND SNORT THEIR ASHES");
       System.out.println("5. STUDY LIKE A NERD");
       System.out.println("6. GET MONEY");
       System.out.println("7. HIT UP THE LOCAL DRUG DEALER");
       System.out.println("8. Talk to friend");
    }
    
    private static void doDrugs(Player p){
       if(p.getDrugs() == 0){
         System.out.println("\nYOU AIN'T GOT ANY DANK SHIT");
         return;
       }
       p.setDrugs(p.getDrugs()-1);
       Drugs d = new Drugs();
       d.start();
       int overdose = (int)(Math.random() * 10);
       if(overdose < 2){
          System.out.println("OVERDOSED LIKE THE DRUGGIE YOU ARE");
          p.die(false);
       }
       System.out.println("WOOO GOT HIGH. +20 KNOWLEDGE & +20 HEALTH");
       p.setKnowledge(p.getKnowledge() + 20);
       p.setHealth(p.getHealth() + 20);
    }
    
    private static void doParty(Player p){
       Party theParty = new Party(p);
       //theParty.start() called by constructor. no need for secondard call
    }
    
    private static void doPiracy(Player p){
       Piracy pirate = new Piracy(p);
       pirate.start();
    }
    
    private static void doStop(Player p, int choice){
       if(choice == 1){
          doParty(p);
       }else if(choice ==2){
          doDrugs(p);
       }else if(choice ==3){
          doPiracy(p);
       }else if(choice ==4){
          passiveStudy(p);
       }else if(choice ==5){
          doStudying(p);
       }else if(choice == 6){
          playBlackJack(p);
       }else if(choice == 7){
          shop(p);
       } else{
          //talk to someone
          talk(p);
       }
    }
    
    private static void doStudying(Player p){
       Studying s = new Studying(p);
       s.start();
    }
    
    private static void gameInfo(){
        System.out.println("\nDO YOU ENJOY BEING FILTHY RICH?");
        System.out.println("DO YOU ENJOY BEING DEBT FREE?");
        System.out.println("DO YOU ENJOY A LIFE FREE OF STUDYING?");
        System.out.println("DO YOU ENJOY BEING SOBER?");
        System.out.println("TOO FUCKING BAD");
        System.out.println("WELCOME TO COLLEGE, FUCKER");
    }
    
    //returns quarters needed to complete degree
    private static int gameLoop(Player p, int degreeLength, int money, Roommate[] roomates,String name){
       int quarter = 0;
       int quartersCompleted = 0;
       
       int prevYear = 0;
       
       while(quartersCompleted<degreeLength){
          int year = (quarter / 3) + 1;
          int currentQuarter = (quarter %3) + 1;
          if(prevYear != year){
             System.out.println("\n-----------------------------------------------");
             System.out.println("YEAR: " + year + "\nQUARTER: " + currentQuarter);
             prevYear = year;
          }
          for(int i = 0; i<5; i++){
             System.out.println("\n-----------------------------------------------");
             System.out.println("WELCOME TO WEEK " + (i*2));
             if(p.getFood() == 0){
                System.out.println("BITCH, YOU'RE STARVING. -10 Health");
                p.setHealth(p.getHealth()-10);
                if(p.getHealth() < 0){
                   System.out.println("YOU DIED THE NOBLE DEATH OF AN ETHIOPIAN TODDLER.");
                   p.die(false);
                }
             } else {
                p.setFood(p.getFood()-1);
             }
             printPlayerStats(p);
             displayStopChoices();
             int choice = readNum(8, "What do you want to do??");
             doStop(p, choice);
                   
          }
          boolean pass = takeTest(p,quartersCompleted+1);
          if(pass){
             quartersCompleted++;
          }
          quarter++;
       }
       System.out.println("YOU HAVE HIT THE STUDENT LOAN CEILING");
       return quarter-1;
    }
    
     private static String nameSelect(String display){
        Scanner scan = new Scanner(System.in);
        
        String name;
        boolean isCorrect = true;
        
        while(isCorrect) {
            System.out.println(display);
            name = scan.nextLine();
            if(name.length() != 0){
               System.out.println("\nIs this name correct? " +name);
               String response = scan.nextLine();
               if(response.equals("yes") || response.equals("Yes") || response.equals("y") || 
                  response.equals("YES")) {
                      return name;
                  }
            }
        }
        return "";
    }
    
    private static void passiveStudy(Player p){
       if(p.getBooks() == 0){
          System.out.println("\nYOU AIN'T GOT BOOKS TO BURN");
          return;
       }
       PassiveStudy pS = new PassiveStudy(p);
       pS.start();
    }
    
    private static void playBlackJack(Player p){
       Blackjack b = new Blackjack(p);
       b.start();
    }
    
    //Print before every stop to help guide player on what
    //minigame should be done
    private static void printPlayerStats(Player p){
       System.out.println("YOU HAVE:");
       System.out.println("BOOZE: \t\t" + p.getAlcohol());
       System.out.println("BOOKS: \t\t" + p.getBooks());
       System.out.println("DRUGS: \t\t" + p.getDrugs());
       System.out.println("FOODS: \t\t" + p.getFood());
       System.out.println("HEALTH:\t\t" + p.getHealth());
       System.out.println("KNOWLEDGE:\t" + p.getKnowledge());
       System.out.println("MONEY: \t\t$" + p.getMoney() + "\n\n");
    }
    
    /**
    *Reads in user input and verifies that it is a valid number option.
    *
    *@param max an integer that specifies the upper bound on the return value.
    *
    *@param display a String that is printed when an invalid value is entered.
    *
    *@return an integer between zero and max+10
    */
    public static int readNum(int max, String display){
      Scanner myScanner = new Scanner(System.in);
      String input = myScanner.nextLine().trim();
      
      try{
         int val = Integer.parseInt(input);
         if(val<1 || val>max){
            System.out.println(display);
            return readNum(max, display);
         }
         return val;
      }catch(Exception ex){//only occurs when user has garbage input
         System.out.println(display);
         return readNum(max, display);
      }
   }
    
    private static void roleInfo(){
        System.out.println("\nComputer Science: FILTHY RICH BUT COLLEGE TAKES FOREVER");
        System.out.println("Agribusiness: YOU'RE A FUCKING FARMER. MEDIOCRE EVERYTHING");
        System.out.println("English: WHY WOULD YOU DO THIS? BROKE AS FUCK.");
    }
    
    private static Roommate[] roommateCreate(){
        String name1 = "";
        String name2 = "";
        String name3 = "";

        Scanner scan = new Scanner(System.in);
        while(name1.length() == 0){
           System.out.println("\nWhat are the names of your roommates?");
           System.out.println("    1. ");
           System.out.println("    2. ");
           System.out.println("    3. ");
           name1 = scan.nextLine().trim();
        }
        while(name2.length() == 0){
           System.out.println("\nWhat are the names of your roommates?");
           System.out.println("    1. " +name1);
           System.out.println("    2. ");
           System.out.println("    3. ");
           name2 = scan.nextLine().trim();
        }
        while(name3.length() == 0){
           System.out.println("\nWhat are the names of your roommates?");
           System.out.println("    1. " +name1);
           System.out.println("    2. " +name2);
           System.out.println("    3. ");
           name3 = scan.nextLine().trim();
        }
        return validateNames(name1, name2, name3);
    }
    
    private static void shop(Player player){
        int alcohol = 0;
        int books = 0;
        int drugs = 0;
        int food = 0;
        int total = 0;
        boolean buying = true;
        while(buying){
            System.out.println("\nTrying to buy shit?");
            System.out.println("------------------------------");
            System.out.println("    1. Alcohol $" +alcohol);
            System.out.println("    2. Books   $" +books);
            System.out.println("    3. Drugs   $" +drugs);
            System.out.println("    4. Food    $" +food);
            System.out.println("------------------------------");
            System.out.println("   Total bill: $" +total);
            System.out.println();
            System.out.println("Your sad savings: $" +player.getMoney());
            System.out.println("What are you trying to hustle?");
            System.out.println("Type '5' when you're done, filthy capitalist.");
            int response = readNum(5, "Congratulations, you played yourself.");
            if (response == 1){
                System.out.println();
                System.out.println("How many metric booze?($10 each, limit 24)");
                total -= alcohol;
                alcohol = 10 * readNum(24, "PICK A CORRECT NUMBER OF BOOZE, FUCKFACE.");
                total += alcohol;
            } else if(response == 2) {
                System.out.println();
                System.out.println("How many books, nerd?($75 each, limit 5)");
                total -= books;
                books = 75 * readNum(5, "PICK A CORRECT NUMBER OF BOOKS, NERD.");
                total += books;
            } else if(response == 3) {
                System.out.println();
                System.out.println("WOOO TIME TO GET COKED UP.($20 each, limit 20)");
                total -= drugs;
                drugs = 20 * readNum(20, "OH SHIT YOU'RE HIGHER THAN A KITE, ENTER A REAL NUMBER");
                total += drugs;
            } else if(response == 4) {
                System.out.println();
                System.out.println("GO GET WASTED INSTEAD.($5 each, limit 100)");
                total -= food;
                food = 5 * readNum(100, "DOES THIS LOOK LIKE COSTCO TO YOU?");
                total += food;
            } else {
                if(total == 0){
                    System.out.println("\nI BET YOU'RE A COMMUNIST LIKE BERNIE SANDLERS.");
                    return;
                } else {
                    if(total > player.getMoney()){
                        System.out.println("\nYOU'RE POOR AS SHIT.");
                        System.out.println("You have have been thrown out of the University Store.");
                        return;
                    } else {
                        buying = false;
                        break;
                    }
                }
            }
        }
        player.setAlcohol(player.getAlcohol() + alcohol/10);
        player.setBooks(player.getBooks() + books/75);
        player.setDrugs(player.getDrugs() + drugs/20);
        player.setFood(player.getFood() + food/5);
        player.setMoney(player.getMoney() - total);
        System.out.println("\nNice job blowing your savings, dickhead.\n");
    }
    
    //Returns true || false depending on if student passes
    private static boolean takeTest(Player p, int quarter){
       int knowledge = p.getKnowledge();
       int knowledgeNeeded = (int) (Math.pow(1.05, quarter) * quarter *100);
       System.out.println("It'S TIME TO GET FUCKED BY FINALS!");
       System.out.print("You have " + knowledge + " knowledge points");
       System.out.println("\nTo barely pass you need " + knowledgeNeeded + " know");
       int adderallBump = knowledge/10;
       int adderallCost = (int) (10 * (Math.random() + 1));
       System.out.println("TAKE ADDERALL???? ");
       System.out.println("The cost would be $" + adderallCost);
       char [] options = {'Y','N'};
       char choice = Blackjack.getValidChar(options);
       if(choice == 'Y'){
          if(adderallCost>p.getMoney()){
             System.out.println("WHY YOU PLAYING GAMES WITH A DRUG DEALER");
             System.out.println("\tYOU DON'T HAVE ENOUGH MONEY FOR THE DANK SHIT");
             System.out.println("No adderall for you");
             System.out.println("Health - 1");
             p.setHealth(p.getHealth()-1);
             if(p.getHealth()<1){
                System.out.println("what an awful way to die");
                p.die(false);
             }
          }else{
             System.out.println("Adderall Taken...");
             p.setMoney(p.getMoney()-adderallCost);
             int rand = (int) (Math.random() *10);
             if(rand <2){
                System.out.println("\tIt wasn't very effective...");
                adderallBump *=0.6;
             }else if(rand>7){
                System.out.println("\tIt was super effective!!!");
                adderallBump *=2;
             }
             System.out.println("Knowledge + " + adderallBump);
             knowledge += adderallBump;
             System.out.print("New knowledge level: " + knowledge);          
          }
       }
       if(knowledge<knowledgeNeeded){
          System.out.println("\nBRIBE THE PROF????");
          choice = Blackjack.getValidChar(options);
          if(choice == 'Y'){
             System.out.println("CAN'T BUY GRADES, FUCKER");
             if(p.getHealth() == 1){
                System.out.println("THE PROFESSOR BEAT THE SHIT OUT OF YOU");
                p.die(false);
             }else{
                System.out.println("Health -1");
                p.setHealth(p.getHealth()-1);
             }
          }
          System.out.println("YOU'RE DUMB AS FUCK");
          System.out.println("CHEAT???");
          int diff = knowledgeNeeded -knowledge;
          if(diff>100){
             diff = 99;
          }
          System.out.println("Chances of getting caught are " + diff + "%.");
          choice = Blackjack.getValidChar(options);
          if(choice == 'Y'){
             int rand = (int) (Math.random()*100);
             if(rand<diff){
                System.out.println("GOT CAUGHT, BITCH");
                System.out.println("YOU SHOULD BE EXPELLED, BUT THEY GAVE YOU PITY");
                return false;
             }else{
                int cheatingSkill = (int) (Math.random() *10);
                if(cheatingSkill<3){
                   System.out.print("FUCK YEAH, YOU CHEATED");
                   System.out.println("\tOFF THE DUMBEST KID IN THE CLASS");
                   System.out.println("TEST FAILED");
                   return false;
                }
                
                System.out.println("GOOD SHIT, YOU PASSED!!!");
                return true;
             }
          }else{
             System.out.println("WHY THE FUCK DID YOU NOT CHEAT?");
             System.out.println("TEST FAILED");
             return false;
          }
       }
       System.out.println("WOOO YOU PASSED, TIME TO GET WASTED");
       return true;//A given that you will pass tes test given that you are smart enough
       }
    
    private static void talk(Player p){
       System.out.println("You have no friends.");
       System.out.println("Morale boost for loneliness. +1 Health.");
       p.setHealth(p.getHealth() + 1);
    }
    
    private static Roommate [] validateNames(String s1, String s2, String s3){
       System.out.println("\nAre these names correct?");
       System.out.println("    1. " +s1);
       System.out.println("    2. " +s2);
       System.out.println("    3. " +s3);
       Scanner myScanner = new Scanner(System.in);
       String response = myScanner.nextLine().trim().toUpperCase();
       
       while(! (response.equals("Y") ||response.equals("N") || 
             response.equals("YES")||response.equals("NO"))){
          System.out.println("Enter a valid response (YES|NO|Y|N) ");
          response = myScanner.nextLine().trim().toUpperCase();
       }
       
       if(response.equals("YES") || response.equals("Y")) {
          Roommate [] roommates = new Roommate[3];
          roommates[0] = new Roommate(s1);
          roommates[1] = new Roommate(s2);
          roommates[2] = new Roommate(s3);
          return roommates;
       }else {
          System.out.println("Which name is incorrect? ");
          System.out.println("If all names are correct type '4'");
          int incorrect = readNum(4, "Which name is incorrect?");
          if(incorrect == 1) {
              s1 = nameSelect("Enter the new name: ");
          } else if (incorrect == 2) {
              s2 = nameSelect("Enter the new name: ");
          } else if (incorrect == 3){
              s3 = nameSelect("Enter the new name: ");
          } else{
             Roommate [] roommates = new Roommate[3];
             roommates[0] = new Roommate(s1);
             roommates[1] = new Roommate(s2);
             roommates[2] = new Roommate(s3);
             return roommates;
          }
          return validateNames(s1,s2,s3);
       }
    }
    
    private static void welcome(){
        System.out.println("\nCOLLEGE: A VULGAR OREGON TRAIL ADVENTURE\n");
        System.out.println("You may:");
        System.out.println("    1. GET FUCKED BY STUDENT LOANS");
        System.out.println("    2. LEARN ABOUT GETTING FUCKED BY STUDENT LOANS");
        System.out.println("    3. GO FUCK YOURSELF");
        System.out.println("What is your choice?");
    }
    
    private static void welcomeCaller(){
        welcome();
        int choice = readNum(3, "\nWhat is your choice?");
        if (choice == 2) {
            gameInfo();
            welcomeCaller();
        } else if(choice == 3){
            System.out.println("PLAY THE GAME OR GET THE FUCK OUT");
            System.exit(0);//wat
        }
    }
    
}
