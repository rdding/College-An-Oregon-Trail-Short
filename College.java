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
        System.out.println("Before you get fucked by an unstable financial future");
        System.out.println("you should blow all your money on booze and cocaine.");
        System.out.println("You have $" +player.getMoney() + " in cash.");
        shop(player);
    }
    
    private static void welcome(){
        System.out.println("COLLEGE: A VULGAR OREGON TRAIL ADVENTURE\n");
        System.out.println("You may:");
        System.out.println("    1. GET FUCKED BY STUDENT LOANS");
        System.out.println("    2. LEARN ABOUT GETTING FUCKED BY STUDENT LOANS");
        System.out.println("    3. GO FUCK YOURSELF");
        System.out.println("What is your choice?");
    }
    
    private static void welcomeCaller(){
        welcome();
        int choice = readNum(3, "What is your choice?");
        if (choice == 2) {
            gameInfo();
            welcomeCaller();
        } else if(choice == 3){
            System.exit(0);//wat
        }
    }
    
    private static void gameInfo(){
        System.out.println("DO YOU ENJOY BEING FILTHY RICH?");
        System.out.println("DO YOU ENJOY BEING DEBT FREE?");
        System.out.println("DO YOU ENJOY A LIFE FREE OF STUDYING?");
        System.out.println("DO YOU ENJOY BEING SOBER?");
        System.out.println("TOO FUCKING BAD");
        System.out.println("WELCOME TO COLLEGE, FUCKER");
    }
    
    private static int characterSelect(){
        displayRoles();
        int choice = readNum(4, "What is your choice?");
        if(choice == 4){
            roleInfo();
            characterCreate();
        } else {
            return choice;
        }
    }
    
    private static void displayRoles() {
       System.out.println("WHAT DEGREE DO YOU WANT WHEN YOU DIE?")
       System.out.println("   1. Computer Science");
       System.out.println("   2. AgriBusiness");
       System.out.println("   3. English");   
   }

   private static String nameSelect(){
        Scanner scan = new Scanner(System.in);
        
        String name;
        boolean isCorrect = true;
        
        while(isCorrect) {
            System.out.println("What is your legal name?(Verified via NSA databases)");
            name = scan.nextLine();
            System.out.println("Is this name correct? " +name);
            String response = scan.nextLine();
            if(response.equals("yes") || response.equals("Yes") || response.equals("y") || 
               response.equals("YES")) {
                   return name;
               }
        }
        return "";
    }
  
    private static void roleInfo(){
        System.out.println("Computer Science: FILTHY RICH BUT COLLEGE TAKES FOREVER")
        System.out.println("Agribusiness: YOU'RE A FUCKING FARMER. MEDIOCRE EVERYTHING");
        System.out.println("English: WHY WOULD YOU DO THIS? BROKE AS FUCK.")
    }
    
    private static Roommate[] roommateCreate(){
        String name1, name2, name3;

        Scanner scan = new Scanner(System.in);
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. ");
        System.out.println("    2. ");
        System.out.println("    3. ");
        name1 = scan.nextLine().trim();
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. ");
        System.out.println("    3. ");
        name2 = scan.nextLine().trim();
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. " +name2);
        System.out.println("    3. ");
        name3 = scan.nextLine().trim();

        while(true){
            System.out.println("Are these names correct?");
            System.out.println("    1. " +name1);
            System.out.println("    2. " +name2);
            System.out.println("    3. " +name3);
            
            String response = scan.nextLine().trim().toUpperCase();
            if(response.equals("YES") || response.equals("Y")) {
                   break;
            }
            System.out.println("Which name is incorrect? ");
            int incorrect = readNum(3, "Which name is incorrect?");
            System.out.println("Enter the new name: ");
            String newName = scan.nextLine().trim();
            if(incorrect == 1) {
                name1 = newName;
            } else if (incorrect == 2) {
                name2 = newName;
            } else {
                name3 = newName;
            }
        }
        Roommate[] roommates = new Roommate[3];
        roommates[0] = new Roommate(name1);
        roommates[1] = new Roommate(name2);
        roommates[2] = new Roommate(name3);
        return roommates;
    }
    
    private static void shop(Player player){
        int alcohol = 0;
        int books = 0;
        int drugs = 0;
        int food = 0;
        int total = 0;
        boolean buying = true;
        while(buying){
            System.out.println("Trying to buy shit?");
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
                System.out.println()
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
                total -= drugs;
                food = 5 * readNum(100, "DOES THIS LOOK LIKE COSTCO TO YOU?");
                total += drugs;
            } else {
                if(total == 0){
                    System.out.println("I BET YOU'RE A COMMUNIST LIKE BERNIE SANDLERS.")
                    return;
                } else {
                    if(total > player.getMoney()){
                        System.out.println("YOU'RE POOR AS SHIT.");
                        System.out.println("You have have been thrown out of the University Store.")
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
        System.out.println("Nice job blowing your savings, dickhead.");
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
       Minigame m = new Blackjack(p);
       m.start();
    }
}
