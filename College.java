/**
* Runs the College game.
*
*@author Richard Ding
*@version program7
*/
import java.util.Scanner;

public class College {
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
        playerName = nameSelect();       
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
        System.out.println("Congratulations you did it!!!!!!!!!!!!!!!!!!");
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
             System.out.println("it is year " + year + " quarter " + currentQuarter);
             prevYear = year;
          }else{
             System.out.println("quarter++, that was quick!!!" + currentQuarter);
          }
          for(int i = 0; i<5; i++){
             System.out.println("WELCOME TO WEEK " + (i*2));
             printPlayerStats(p);
             displayStopChoices();
             int choice = readNum(6, "What do you want to do??");
             doStop(p, choice);
                   
          }
          boolean pass = takeTest(p,quartersCompleted+1);
          if(pass){
             quartersCompleted++;
          }
          quarter++;
       }
       System.out.println("$&^*(&^%&*(&^ You finished school &^(**&^%^&*()*&");
       return quarter-1;
    }
    private static void displayStopChoices(){
       System.out.println("1. Study");
       System.out.println("2. Drugs");
       System.out.println("3. Piracy");
       System.out.println("4. PassiveStudy");
       System.out.println("5. Gamble");
       System.out.println("6. Talk to someone");
    }
    private static void doStop(Player p, int choice){
       if(choice == 1){
          doStudying(p);
       }else if(choice ==2){
          doDrugs(p);
       }else if(choice ==3){
          doPiracy(p);
       }else if(choice ==4){
          passiveStudy(p);
       }else if(choice ==5){
          playBlackJack(p);
       }else{//talk to someone
          System.out.println("hello! nice to meet you");
       }
       System.out.println("What an exhausting stop!!!");
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
    
    private static void gameInfo(){
        System.out.println("\nDO YOU ENJOY BEING FILTHY RICH?");
        System.out.println("DO YOU ENJOY BEING DEBT FREE?");
        System.out.println("DO YOU ENJOY A LIFE FREE OF STUDYING?");
        System.out.println("DO YOU ENJOY BEING SOBER?");
        System.out.println("TOO FUCKING BAD");
        System.out.println("WELCOME TO COLLEGE, FUCKER");
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

   private static String nameSelect(){
        Scanner scan = new Scanner(System.in);
        
        String name;
        boolean isCorrect = true;
        
        while(isCorrect) {
            System.out.println("\nWhat is your legal name?(Verified via NSA databases)");
            name = scan.nextLine();
            System.out.println("\nIs this name correct? " +name+ "\n");
            String response = scan.nextLine();
            if(response.equals("yes") || response.equals("Yes") || response.equals("y") || 
               response.equals("YES")) {
                   return name;
               }
        }
        return "";
    }
  
    private static void roleInfo(){
        System.out.println("\nComputer Science: FILTHY RICH BUT COLLEGE TAKES FOREVER");
        System.out.println("Agribusiness: YOU'RE A FUCKING FARMER. MEDIOCRE EVERYTHING");
        System.out.println("English: WHY WOULD YOU DO THIS? BROKE AS FUCK.");
    }
    
    private static Roommate[] roommateCreate(){
        String name1, name2, name3;

        Scanner scan = new Scanner(System.in);
        System.out.println("\nWhat are the names of your roommates?");
        System.out.println("    1. ");
        System.out.println("    2. ");
        System.out.println("    3. ");
        name1 = scan.nextLine().trim();
        System.out.println("\nWhat are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. ");
        System.out.println("    3. ");
        name2 = scan.nextLine().trim();
        System.out.println("\nWhat are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. " +name2);
        System.out.println("    3. ");
        name3 = scan.nextLine().trim();

        return validateNames(name1, name2, name3);
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
          System.out.println("If all names are correct->(4)");
          int incorrect = readNum(4, "Which name is incorrect?");
          System.out.println("Enter the new name: ");
          String newName = myScanner.nextLine().trim();
          if(incorrect == 1) {
              s1 = newName;
          } else if (incorrect == 2) {
              s2 = newName;
          } else if (incorrect == 3){
              s3 = newName;
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
        System.out.println("\nNice job blowing your savings, dickhead.");
    }
    
    public static int readNum(int limit, String display){
        Scanner scan = new Scanner(System.in);
        while(true) {
            if(scan.hasNextInt()){
                int test = scan.nextInt();
                if(test > 0 && test <= limit){
                    return test;
                }
            }
            String badInput = scan.nextLine();
            System.out.println(display);
            
        }
    }
    private static void playBlackJack(Player p){
       Blackjack b = new Blackjack(p);
       b.start();
    }
    private static void doDrugs(Player p){
       Drugs d = new Drugs();
       d.start();
    }
    private static void doParty(Player p){
       Party theParty = new Party(p);
       //theParty.start() called by constructor. no need for secondard call
    }
    private static void passiveStudy(Player p){
       PassiveStudy pS = new PassiveStudy(p);
       pS.start();
    }
    private static void doPiracy(Player p){
       Piracy pirate = new Piracy(p);
       pirate.start();
    }
    private static void doStudying(Player p){
       Studying s = new Studying(p);
       s.start();
    }
    //Print before every stop to help guide player on what
    //minigame should be done
    private static void printPlayerStats(Player p){
       System.out.println("You have \t" + p.getAlcohol()   + " alcohols");
       System.out.println("You have \t" + p.getBooks()     + " books");
       System.out.println("You have \t" + p.getDrugs()     + " drugs");
       System.out.println("You have \t" + p.getFood()      + " foods");
       System.out.println("You have \t" + p.getHealth()    + " health");
       System.out.println("You have \t" + p.getKnowledge() + " knowledge");
       System.out.println("You have \t" + p.getMoney()     + " moneys\n\n");
    }
    //Returns true || false depending on if student passes
    private static boolean takeTest(Player p, int quarter){
       int knowledge = p.getKnowledge();
       int knowledgeNeeded = (int) (Math.pow(1.05, quarter) * quarter *100);
       System.out.println("It is time to take your final exams!!!");
       System.out.print("You have " + knowledge + " Knowledge points");
       System.out.println("To barely pass you need " + knowledgeNeeded + " know");
       int adderallBump = knowledge/10;
       int adderallCost = (int) (10 * (Math.random() + 1));
       System.out.println("Would you like to use knolwedge enhancing drugs??? ");
       System.out.println("\tThe cost would be " + adderallCost);
       char [] options = {'Y','N'};
       char choice = Blackjack.getValidChar(options);
       if(choice == 'Y'){
          if(adderallCost>p.getMoney()){
             System.out.println("WHY YOU PLAYING GAMES WITH A DRUG DEALER");
             System.out.println("\tYOU DON'T HAVE ENOUGH MONEY FOR THE GOOD STUFF");
             System.out.println("No adderall for you");
             System.out.println("Health - 1");
             p.setHealth(p.getHealth()-1);
             if(p.getHealth()<1){
                System.out.println("what an awful way to die");
                p.die(false);
             }
          }else{
             System.out.println("Adderall Taken...");
             int rand = (int) (Math.random() *10);
             if(rand <2){
                System.out.println("\tIt wasn't very effective...");
                adderallBump *=0.6;
             }else if(rand>7){
                System.out.println("\tIt was super effective!!!");
                adderallBump *=2;
             }
             System.out.println("Knowlede + " + adderallBump);
             knowledge += adderallBump;
             System.out.print("New knowledge level: " + knowledge);          
          }
       }
       if(knowledge<knowledgeNeeded){
          System.out.println("Would you be interested in bribing a teacher");
          choice = Blackjack.getValidChar(options);
          if(choice == 'Y'){
             System.out.println("grades can't be bought or sold. shame...");
             if(p.getHealth() == 1){
                System.out.println("You died from a slap to a face for your stupidity");
                p.die(false);
             }else{
                System.out.println("Health -1");
                p.setHealth(p.getHealth()-1);
             }
          }
          System.out.println("Looks like you aren't smart enough to pass this test by yourself...");
          System.out.println("Would you like to cheat on this test???");
          int diff = knowledgeNeeded -knowledge;
          if(diff>100){
             diff = 99;
          }
          System.out.println("Chance of getting caught are " + diff + "%.");
          choice = Blackjack.getValidChar(options);
          if(choice == 'Y'){
             int rand = (int) (Math.random()*100);
             if(rand<diff){
                System.out.println("Unfortunately you got caught...");
                System.out.println("You begged to be failed but still be able to finish college");
                return false;
             }else{
                int cheatingSkill = (int) (Math.random() *10);
                if(cheatingSkill<3){
                   System.out.print("You successfully cheated!!!!");
                   System.out.println("\toff the worst student...");
                   System.out.println("FAIL FAIL FAIL");
                   return false;
                }
                
                System.out.println("Your risk paid off and you passed the class!!!");
                return true;
             }
          }else{
             System.out.println("why didnt you cheat??? i told you that you would fail");
             System.out.println("FAIL FAIL FAIL");
             return false;
          }
       }
       System.out.println("fabulous performance on that examination :)))) -> PASS");
       return true;//A given that you will pass tes test given that you are smart enough
       }
}
