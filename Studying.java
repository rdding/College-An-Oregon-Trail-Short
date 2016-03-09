
public class Studying implements Minigame{

   private static final int MATH = 1;/*//not built yet
   private static final int VOCAB = 2;
   private static final int FLANG = 3;
   private static final int POT=4;*/
   
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
      int studyChoice = College.readNum(1, "What would you like to study???");
      int level = College.readNum(10, "What level would you like to study at???");
      int problemCount = College.readNum(10, "How many problems would you like to do???");
      if(studyChoice == MATH){
         while(problemCount>0){
            learnMath(level);
            problemCount--;
         }
      }/*else if(studyChoice == VOCAB){ //NOT BUILT YET
         
      }else if(studyChoice == FLANG){
         
      }else if(studyChoice == POT){
         
      }//should make a call to one of these options before exiting.
      */
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
            //I will throw this if there is an error - Divide by zero
            value = "ERROR";
         }
         if(!value.equals("ERROR")){
            value = solution + "";
         }
         getOptions(value, level);
      }  
   }
   private double getSolution(char [] operators, double [] operands){
      boolean isBaseCase = true;
      if(operators == null || operators.length == 0){
         return operands[0];
      }
      if(operators.length == 1){
         if(operands[0] == '+'){
            return operands[0] + operands[1];
         }else if (operands[0] == '-'){
            return operands[0] - operands[1];
         }else if (operands[0] == '*'){
            return operands[0] * operands[1];
         }else{//must be divisor
            return operands[0] / operands[1];
         }
      }
      for(int i = 0; i<operators.length;i++){
         if(operators[i] == '+' || operators[i]== '-'){
            isBaseCase = false;
            break;
         }
      }
      if(isBaseCase){
         double answer = operands[0];
         for(int i = 0; i< operators.length; i++){
            if(operators[i] == '+'){
               answer += operands[i+1];
            }else if(operators[i] == '-'){
               answer -= operands[i+1];
            }
         }
         return answer;
      }else{
         int multDivCount = 0;
         for(int i = 0; i<operators.length;i++){
            if(operators[i] == '+' || operators[i] == '-'){
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
                  //compute
               }else{
                  
               }
               
            }else{
               //do not compute the + or - yet
            }
         }
         return getSolution(recurOperators, recurOperands);
      }
   }
   private void printMathProblem (char [] operators, double [] operands){
      System.out.println("Evaluate...");
      System.out.print("(" + operators[0] + ") ");
      for(int i = 0; i<operands.length; i++){
         System.out.print(operands[i]);
         System.out.print(" ("+ operators[i+1] +") ");         
      }
   }
   //more level will result in more option
   private String [] getOptions(String realValue, int level){
      return null;
   }

   @Override
   public void exit() {
      System.out.println("--------------------------------------------------");
      System.out.println("--------------------------------------------------");
      System.out.println("----------YOUR STUDY SESSION IS COMPLETE----------");
      long timeDif = (System.currentTimeMillis() - startTime) /1000;
      printTimeStats(timeDif);
      System.out.print("You correctly answered " + correct +" out of " + recieved + "problems!!!");
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
      System.out.println("1. MATH");/*Not built yet
      System.out.println("2. VOCABULARY");
      System.out.println("3. FOREIGN LANGUAGES");
      System.out.println("4. POTPOURRI ");*/
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

}
