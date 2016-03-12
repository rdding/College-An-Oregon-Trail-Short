import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Studying implements Minigame{
   
   private static final String filePathDic = "WordList.txt";
   private static final int MATH = 1;
   private static final int VOCAB = 2;
   
   private Player p;
   private long startTime;
   private int recieved;
   private int correct;
   
   public Studying(Player p){
      startTime = System.currentTimeMillis();
      this.p = p;
      recieved = 0;
      correct  = 0;
      System.out.println("Welcome to your study session!!!!");
   }
   @Override
   public void start() {
      printStudyOptions();    
      int studyChoice = College.readNum(2, "What would you like to study???");
      System.out.println("How hard do you want to study (1-10)");
      int level = College.readNum(10, "What level would you like to study at???");
      System.out.println("How many problems do you want to do???");
      System.out.println("Note that every problem you do decreases your health by 4");
      System.out.println("You can kill yourself if you are not careful. Health: " + p.getHealth());
      int problemCount = College.readNum(10, "How many problems would you like to do???");
      p.setHealth(p.getHealth()-4*problemCount);
      if(p.getHealth()<0){
         p.setHealth(0);
      }
      recieved = problemCount;
      if(studyChoice == MATH){
         while(problemCount>0){
            System.out.println("--------------------------------");
            System.out.println("Here is problem " + (recieved-problemCount+1));
            learnMath(level);
            problemCount--;
         }
      }else if(studyChoice == VOCAB){ //NOT BUILT YET
         ArrayList<String> words = new ArrayList<String>();
         File f = new File (filePathDic);
         try{
            Scanner myScanner = new Scanner(f);
            
            while(myScanner.hasNext()){
               String s= myScanner.next();
               s = s.toUpperCase().trim();
               if(s.length() == (level+3) ){ //only add words of a given size
                  words.add(s);   
               }
            }
            myScanner.close();
         }catch(Exception ex){}
         while(problemCount>0){
            System.out.println("--------------------------------");
            System.out.println("Here is problem " + (recieved-problemCount+1));
            int rand =  (int) (Math.random() * words.size() );
            String randomWord = words.get(rand);
            learnVocab(randomWord,level);
            problemCount--;
         }
      }
      if(p.getHealth() == 0){
         System.out.println("--Death by studying--");
         p.die(false);
      }
      exit();
   }
   //level directly effects the number of operators in there
   //precondition that level is between 1 and 10
   private void learnMath(int level){
      if(level <1){
         level = 1;         
      }else if(level>10){
         level = 10;
      }//validate that the size of the array will be of correct size
      //based on level the number of operators will change
      //also note that number will get larger given the higher levels
      char   [] operators = new char[level];
      double [] operands  = new double [level+1];
      //load operators
      if(level <5){
         for(int i = 0; i<operators.length;i++){
            int rand = (int) (Math.random() * 2);
            if(rand == 0){
               operators[i] = '+';
            }else{
               operators[i] = '-';
            }  
         }
      }else{
         for(int i = 0; i<operators.length;i++){
            int rand = (int) (Math.random() * 4);
            if(rand == 0){
               operators[i] = '+';
            }else if(rand ==2){
               operators[i] = '-';
            }else if(rand ==3){
               operators[i] = '*';
            }else{
               operators[i] = '/';
            }
         }
      }
      //load operands
      for(int i = 0; i<operands.length; i++){
         operands[i] = (int) (Math.random()*(5 * level));
      }
      printMathProblem(operators, operands);
      double solution = 0;
      String value = "";
      try{
         solution = getSolution(operators, operands);
      }catch(RuntimeException ex){
         System.out.println(ex.getMessage());
         ex.printStackTrace();
         //I will throw this if there is an error - Divide by zero
         value = "ERROR";
      }
      if(!value.equals("ERROR")){
         value = solution + "";
      }
      String [] displayedOptions = getOptions(value, level);
      printOptions(displayedOptions);
      int correctIndex = getIndexRightAnswer(value, displayedOptions);
      Scanner myScanner = new Scanner(System.in);
      System.out.println("so tell me the answer...");
      String letter = myScanner.nextLine().trim().toUpperCase();
      char firstLet=0;
      if(letter.length()>0){
         firstLet = letter.charAt(0);
      }
      while(letter.length() != 1 || (firstLet-'A')<0 ||firstLet-'A'>displayedOptions.length ){
         System.out.println("no relly, tell me the answer...");
         letter = myScanner.nextLine().trim().toUpperCase();
      }
      if(letter.charAt(0) == ('A'+correctIndex)){
         System.out.println("CORRECT: GOOD JOB");
         correct++;
         giveStudy((level+3)*2/5);
      }else{
         System.out.println("WRONG: BAD BAD");
         System.out.println("The correct answer was" + ((char)('A'+correctIndex)));
      }
   }
   private void giveStudy(int n){
      p.setKnowledge(p.getKnowledge()+n);
   }
   
   //Method to print options with truncation
   private void printOptions (String [] arr){
      for(int i = 0; i<arr.length; i++){
         String temp = arr[i];
         char option = (char)('A' + i);
         if(temp.indexOf('.') != -1){//means decimate present in sequence
            temp += "0000";
            temp = temp.substring(0, temp.indexOf('.')+3);
            System.out.println(option + ". " + temp);
         }else{
            System.out.println(option + ". " + temp);
         }
      }
   }
   private double getSolution(char [] operators, double [] operands){
      boolean isBaseCase = true;
      if(operators == null || operators.length == 0){
         return operands[0];
      }//Works only for level 1 problems or extremely simplified problems
      if(operators.length == 1){
         if(operators[0] == '+'){
            return operands[0] + operands[1];
         }else if (operators[0] == '-'){
            return operands[0] - operands[1];
         }else if (operators[0] == '*'){
            return operands[0] * operands[1];
         }else{//must be divisor
            return operands[0] / operands[1];
         }
      }
      //////////////////////////////////////
      for(int i = 0; i<operators.length;i++){
         if(operators[i] == '*' || operators[i]== '/'){
            isBaseCase = false;
            break;
         }
      }
      if(isBaseCase){ //Can evaluate easily
         double answer = operands[0];
         for(int i = 0; i< operators.length; i++){
            if(operators[i] == '+'){
               answer += operands[i+1];
            }else if(operators[i] == '-'){
               answer -= operands[i+1];
            }
         }
         return answer;
         ///////////////////////////////////////Note the code appears to be working
         //up to this point. Note errors arise after this point when we try
         //to trivialize the problem and then recompute the solution
      }else{
         int multDivCount = 0;
         for(int i = 0; i<operators.length;i++){
            if(operators[i] == '*' || operators[i] == '/'){
               multDivCount++;
            }
         }
         char [] recurOperators = new char [operators.length-multDivCount];
         double [] recurOperands  = new double  [operands.length-multDivCount]; 
         int orPtr =0;
         int andPtr=0;
         for(int i = 0; i<operators.length; i++){
            if(operators[i]== '*' || operators[i] == '/'){
               double temp = 0;
               if(operators[i] == '*'){
                  //compute the value of tmep
                  temp = operands[i] *operands[i+1];
               }else{
                  temp = operands[i] / operands[i+1];
               }
               operands[i+1] = temp;
            }else{
               recurOperators[orPtr++] = operators[i];
               recurOperands [andPtr++] = operands[i];
            }
         }
         recurOperands[andPtr] = operands[operands.length-1];
         return getSolution(recurOperators, recurOperands);
      }
   }
   private void printMathProblem (char [] operators, double [] operands){
      System.out.println("Evaluate...");
      System.out.print("(" + operands[0] + ") ");
      for(int i = 0; i<operators.length; i++){
         System.out.print(operators[i]);
         System.out.print(" ("+ operands[i+1] +") ");         
      }
      System.out.println();
   }
   //more level will result in more option
   private String [] getOptions(String realValue, int level){
      int chances = level + 4;
      if(realValue.equals("ERROR")){
         String [] multiples = new String[chances];
         multiples[0] = "ERROR";
         for(int i = 1; i< chances; i++){
            multiples[i] = ""+(int) ((Math.random()*1000)-500);
         }
         shuffle(multiples);
         return multiples;
      }
      double answer =  Double.parseDouble(realValue);
      
      String [] multiples = new String[chances];
      multiples[0] = realValue+"";
      multiples[1] = "ERROR"; //Psuedo error
      for(int i =2; i<chances; i++){
         double sol = answer + (Math.random() * 40)-20;
         String val = sol +"";
         while(contains(multiples, val)){
            sol = answer + (Math.random() * 40)-20;
            val = sol+"";
         }
         multiples[i] = val;
      }
      shuffle(multiples);
      return multiples;
      //Returns a list of possible solutions with the real value shuffled
   }
   private boolean contains(String [] repo, String val){
      if(repo == null){
         return false;
      }
      for(int i = 0; i<repo.length; i++){
         if(val.equals(repo[i])){
            return true;
         }
      }
      return false;
   }
   private void shuffle(String [] arr){
      for(int i = 0; i<arr.length; i++){
         int rand = (int) (Math.random() * arr.length);
         String temp = arr[rand];
         arr[rand] = arr[i];
         arr[i] = temp;
      }
   }
   private int getIndexRightAnswer(String answer, String [] arr){
      for(int i = 0; i< arr.length; i++){
         if(answer.equals(arr[i])){
            return i;
         }
      }
      return -1;
   }

   @Override
   public void exit() {
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      System.out.println("----------YOUR STUDY SESSION IS COMPLETE----------");
      long timeDif = (System.currentTimeMillis() - startTime) /1000;
      printTimeStats(timeDif);
      System.out.println("You correctly answered " + correct +" out of " + recieved + " problems!!!");
      int knowledge = p.getKnowledge();
      if(recieved != 0){
         int percent = (correct/recieved)*100;
         System.out.println(percent + "% correct.");
         if(percent>=80){
            System.out.println("Great Job!!! " + percent/10 + " points knowledge bonus!");
            p.setKnowledge(knowledge + (percent/10));
         }else if(percent>=70){
            System.out.println("Great Job!!! 5 points knowledge bonus!");
            p.setKnowledge(knowledge + 5);
         }else{
            if(percent<40){
               System.out.println("Pathetic... no bonus");
            }else{
               System.out.println("ARMATURE HOUR. NO BONUS");  
            }            
         }
      }
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      
   }
   
   private void printStudyOptions(){
      System.out.println("Choose Something to Study:");
      System.out.println("1. MATH");
      System.out.println("2. VOCABULARY");
   }
   
   private void printTimeStats(long seconds){
      String s = "";
      if(seconds == 1){
         s = "You've played 1 second.";
      }else if(seconds<60){
         s = "You've played " + seconds + " seconds.";
      }else if(seconds<3600){
         s = "You've played " + seconds/60 + " minutes and " + seconds%60 +" seconds";
      }else{
         long hours = seconds/3600;
         s = "You've played " + hours + " hours" + seconds/60 + " minutes and "
         + seconds%60 +" seconds";
      }
      System.out.println(s);
   }
   ////////////////////////////////////////////////
   //////////////////////////////////////////////////////////////VOCAB
   ////////////////////////////////////////////////
   private void learnVocab(String s, int level){
      String capped = "";
      for(int i = 0; i<s.length(); i++){
         int rand = (int) (Math.random()*2);
         if(rand ==0){
            capped+=s.charAt(i);
         }else{
            capped+=(char)(s.charAt(i)-'A' +'a');
         }
      }
      System.out.println("TYPE \"" + capped +"\" Exactly...(without the quotes)");
      Scanner myScanner = new Scanner(System.in);
      String userInput = myScanner.nextLine().trim();
      if(userInput.equals(capped)){
         System.out.println("CONGRATULATIONS, you did it right");
         giveStudy((level+3)*2/5);
         correct++;
      }else{
         System.out.println("WRONG WRONG WRONG");
         printDiffs(userInput, capped);
      }
   } 
   private void printDiffs(String input, String expect){
      if(input.length() != expect.length()){
         System.out.println("\tInput Len: " + input.length() + " Expected Len: "+expect.length());
      }
      for(int i = 0; i<Math.min(input.length(), expect.length());i++){
         if(input.charAt(i) != expect.charAt(i)){
            System.out.println("\tDiff at char " + i + " Expected: '" + expect.charAt(i) +"'"
                  + " Input: '" + input.charAt(i) + "'");
         }
      }
      if(input.length() >expect.length()){
         System.out.println("\tExtra chars in input: " + input.substring(expect.length()));
      }else if(input.length()<expect.length()){
         System.out.println("\tMissing traling chars: " + expect.substring(input.length())); 
      }
   }
}//github you playing games with me