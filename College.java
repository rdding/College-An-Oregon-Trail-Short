/**
* Runs the College game.
*
*@author Richard Ding
*@version program7
*/
import java.util.Scanner;

public class College {
    public static void main(String[] args){
        welcomeCaller();
        int degreeChoice = characterSelect();
        String playerName = nameSelect();
        Roommate[] roommates = roommateCreate();
        int degreeLength = 30;
        int money = 500;
        if (degreeChoice == 2) {
            degreeLength = 24;
            money = 400;
        } else if (degreeChoice == 3){
            degreeLength = 18;
            money = 300;
        }
        Player player = new Player(degreeLength, money, playerName, roommates)
        System.out.println("Before you get fucked by an unstable financial future");
        System.out.println("you should blow all your money on kegs and cocaine.")
        System.out.println("You have $" +player.getMoney() " in cash.")
        shop(player);
    }
    
    public static void welcome(){
        System.out.println("COLLEGE: A VULGAR OREGON TRAIL ADVENTURE");
        System.out.println();
        System.out.println("You may:")
        System.out.println("    1. GET FUCKED BY STUDENT LOANS");
        System.out.println("    2. LEARN ABOUT GETTING FUCKED BY STUDENT LOANS");
        System.out.println("    3. GO FUCK YOURSELF")
        System.out.println("What is your choice?")
    }
    
    public static void welcomeCaller(){
        welcome()
        int choice = readNumSelect(3, "What is your choice?");
        if (choice == 2) {
            gameInfo();
            welcomeCaller();
        } else if(choice == 3){
            System.exit(0);
        }
    }
    
        public static void gameInfo(){
        //WRITETHIS
        //display screen by screen in the oregon trail style
        //need to figure out how to progress with button press
        //ex. press space to continue
        //we can also cut out that part and display all the info in one chunk
    }
    
    public static int characterSelect(){
        displayRoles();
        int choice = readNumSelect(4, "What is your choice?");
        if(choice == 4){
            roleInfo();
            characterCreate();
        } else {
            return choice;
        }
    }
    
    public static String nameSelect(){
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
    }
  
    public static void roleInfo(){
        //see gameInfo() comments
    }
    
    public static Roommate[] roommateCreate(){
        String name1;
        String name2;
        String name3;
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. ");
        System.out.println("    2. ");
        System.out.println("    3. ");
        name1 = scan.nextLine();
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. ");
        System.out.println("    3. ");
        name2 = scan.nextLine();
        System.out.println("What are the names of your roommates?");
        System.out.println("    1. " +name1);
        System.out.println("    2. " +name2);
        System.out.println("    3. ");
        name3 = scan.nextLine();
        boolean isCorrect = true;
        while(true){
            System.out.println("Are these names correct?");
            System.out.println("    1. " +name1);
            System.out.println("    2. " +name2);
            System.out.println("    3. " +name3);
            if(response.equals("yes") || response.equals("Yes") || response.equals("y") || 
               response.equals("YES")) {
                   isCorrect = false;
                   break;
               }
            System.out.println("Which name is incorrect? ");
            int incorrect = readNum(3, "Which name is incorrect?");
            System.out.println("Enter the new name: ");
            String newName = scan.nextLine();
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
    
    public static void shop(Player player){
        //write this.
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
}