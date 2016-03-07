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
        Player player = characterCreate();
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
    
    public static Player characterCreate(){
        displayRoles();
        int choice = readNumSelect(4, "What is your choice?");
        if(choice == 1){
            return engineerCreate();
        } else if(choice == 2){
            return agbusinessCreate();
        } else if(choice == 3){
            return englishCreate();
        } else {
            roleInfo();
            characterCreate();
        }
    }
    
    public static void roleInfo(){
        //see gameInfo() comments
    }
    
    public static Player engineerCreate(){
        return new Player(30, 500, roommateCreate());
    }
    
    public static Player agbusinessCreate(){
        return new Player(24, 400, roommateCreate());
    }
    
    public static Player englishCreate(){
        return new Player(18, 300, roommateCreate());
    }
    
    public static Roommate[] roommateCreate(){
        //I'll finish this tonight
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