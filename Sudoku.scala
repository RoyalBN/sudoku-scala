import scala.io.Source

class Sudoku(val name: String, val difficulty: Int) {

  // EASY 
  var easy_sudoku = 
  Array(
        Array(7,9,4,1,2,8,3,6,5), Array(6,3,2,5,4,9,1,7,8), Array(8,5,1,3,6,7,9,2,4),
        Array(2,7,9,8,3,1,5,4,6), Array(4,6,3,2,0,5,8,1,7), Array(5,1,8,6,7,4,2,9,3),
        Array(1,8,6,7,5,2,4,3,9), Array(3,4,5,9,1,6,7,8,2), Array(0,2,7,4,8,3,6,5,0)
  )

  var easy_sudoku_solution = 
  Array(
        Array(7,9,4,1,2,8,3,6,5), Array(6,3,2,5,4,9,1,7,8), Array(8,5,1,3,6,7,9,2,4),
        Array(2,7,9,8,3,1,5,4,6), Array(4,6,3,2,9,5,8,1,7), Array(5,1,8,6,7,4,2,9,3),
        Array(1,8,6,7,5,2,4,3,9), Array(3,4,5,9,1,6,7,8,2), Array(9,2,7,4,8,3,6,5,1)
  )


  // MEDIUM
  var medium_sudoku = 
  Array(
        Array(0,0,0,9,7,0,0,4,0), Array(0,8,4,0,0,5,0,0,0), Array(0,0,7,0,1,0,0,0,0),
        Array(8,0,0,4,0,0,0,0,1), Array(4,0,2,5,8,7,9,0,3), Array(5,0,0,0,0,3,0,0,2),
        Array(0,0,0,0,5,0,7,0,0), Array(0,0,0,2,0,0,1,3,0), Array(0,5,0,0,6,1,0,0,0)
  ) 

  var medium_sudoku_solution =
  Array(
        Array(1,3,5,9,7,2,6,4,8), Array(9,8,4,6,3,5,2,1,7), Array(6,2,7,8,1,4,3,5,9),
        Array(8,9,3,4,2,6,5,7,1), Array(4,1,2,5,8,7,9,6,3), Array(5,7,6,1,9,3,4,8,2),
        Array(2,4,1,3,5,8,7,9,6), Array(7,6,8,2,4,9,1,3,5), Array(3,5,9,7,6,1,8,2,4)
  ) 


  // HARD  
  var hard_sudoku =
  Array(
        Array(0,0,0,0,0,0,6,8,0), Array(0,0,0,0,7,3,0,0,9), Array(3,0,9,0,0,0,0,4,5),
        Array(4,9,0,0,0,0,0,0,0), Array(8,0,3,0,5,0,9,0,2), Array(0,0,0,0,0,0,0,3,6),
        Array(9,6,0,0,0,0,3,0,8), Array(7,0,0,6,8,0,0,0,0), Array(0,2,8,0,0,0,0,0,0)
  ) 

  var hard_sudoku_solution =
  Array(
        Array(1,7,2,5,4,9,6,8,3), Array(6,4,5,8,7,3,2,1,9), Array(3,8,9,2,6,1,7,4,5),
        Array(4,9,6,3,2,7,8,5,1), Array(8,1,3,4,5,6,9,7,2), Array(2,5,7,1,9,8,4,3,6),
        Array(9,6,4,7,1,5,3,2,8), Array(7,3,1,6,8,2,5,9,4), Array(5,2,8,9,3,4,1,6,7)
  ) 


  // Alphabetical letters for rows
  val letters = Array("A","B","C","D","E","F","G","H","I");
  
  // Terminal colors settings
  val ANSI_RESET = "\u001B[0m";
  val ANSI_RED = "\u001B[31m";
  val ANSI_GREEN = "\u001B[32m";
  val ANSI_RED_BACKGROUND = "\u001B[41m";
  val ANSI_GREEN_BACKGROUND = "\u001B[42m";

  // Variable for finishing the game
  var game_finished = 0

  // Default settings  
  var sudoku = easy_sudoku
  var sudoku_solution = easy_sudoku_solution


  // Choose a difficulty 
  difficulty match {
     case 1 => sudoku = easy_sudoku
               sudoku_solution = easy_sudoku_solution
               
     case 2 => sudoku = medium_sudoku
               sudoku_solution = medium_sudoku_solution
     
     case 3 => sudoku = hard_sudoku
               sudoku_solution = hard_sudoku_solution
     
     case _ => println("Please choose a number between 1 and 3")                              
  } 



  // ***********************************************************************
  //                             FUNCTIONS
  // ***********************************************************************
  
  // *********************
  //   Print Sudoku grid
  // *********************
  def printGrid() = {
      var i = 0; // Vertical line
      var j = 0; // Horizontal line
      var a = 0;
      var indice = 0;


      // Print the number header
      for(indice <- 1 to 9){
         if(indice == 4 || indice == 7){
           print(ANSI_GREEN_BACKGROUND + ANSI_RED + "  ");
         }
         print(ANSI_GREEN_BACKGROUND + ANSI_RED + indice + " " + ANSI_RESET);
      }
      print(ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET);
      println();
 

      // Print all rows
      for(i <- 0 to 8){ 
        for(j <- 0 to 8){
            // Print the values
            print(sudoku(i)(j) + " ");
            // Print vertical lines
            if(j == 2 || j == 5){
               print("| ");
            }
        }
        // Print the letters on the side
        print(ANSI_GREEN_BACKGROUND + ANSI_RED + " " + letters(a) + " " + ANSI_RESET);
        a = a + 1;

        
        // Print horizontal lines
        println();
        if (i == 2 || i == 5){
            for(a <- 0 to 10){
              print("- ");
            }
            print(ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET);
            println(); 
        }
        
      }
      println(); 
  }
  


  // *********************
  //   Check the answer
  // *********************  
  def answerChecker(args: String) {
    var upperIndice = args.toUpperCase
    var i1_value = 0
    var i1 = upperIndice(0)
    var i2 = args(2).asDigit
    var i3 = args(4).asDigit
    
    
    i1 match {
       case 'A' => i1_value = 0
       case 'B' => i1_value = 1
       case 'C' => i1_value = 2
       case 'D' => i1_value = 3
       case 'E' => i1_value = 4
       case 'F' => i1_value = 5
       case 'G' => i1_value = 6
       case 'H' => i1_value = 7
       case 'I' => i1_value = 8
       case _ => println("Letter needed for argument 1")
    }


    // If correct : Print the number
    if(i3 == sudoku_solution(i1_value)(i2-1)){
       sudoku(i1_value)(i2-1) = i3
       println("Correct answer !")
       println("Grid updated")
       gameEndChecker(sudoku)
    }
    else println("Wrong answer, try again...")  

  }


  // *********************
  //  Check for Game End
  // ********************* 
  def gameEndChecker(args: Array[Array[Int]]){
    // Check if the game is finished
    var i = 0
    var j = 0
    game_finished = 1
    for(i <- 0 to 8){ 
        for(j <- 0 to 8){
            if(sudoku(i)(j) == 0){
               game_finished = 0 
            }
            
        }  
    }

  }


  // *********************
  //    Get the value
  // *********************  
  def getGameFinished(): Int = {
     return game_finished
  }

   
}



// ***********************************************************************
//                                 MAIN
// ***********************************************************************
object Demo {
   def main(args: Array[String]) {
      
      
      val name = scala.io.StdIn.readLine("What's your name? ")
      var difficulty = scala.io.StdIn.readLine("Choose a difficulty from 1 (easy) to 3 (hard):")
      
      while(difficulty == "" || difficulty < "1" || difficulty > "3"){
         difficulty = scala.io.StdIn.readLine("Please choose a difficulty from 1 to 3:")  
      }

      val sdk = new Sudoku(name, difficulty.toInt);
      
      // Print player's name
      println("Hello " + name)
      
      // Load and print the rules for playing the game  
      val filename = "src/main/scala/example/files/rules.txt"
      for (line <- Source.fromFile(filename).getLines) {
         println(line)
      }
      
      // Main loop 
      while(sdk.getGameFinished != 1){

      // Print the Sudoku grid
      sdk.printGrid()
       
      // User entries
      var choice = scala.io.StdIn.readLine("Choose a position and a number:")
      val issues = choice.length() != 5 || choice(1) != ','|| choice(3) != ':' ||
                   choice(2).asDigit < 1 || choice(2).asDigit > 9 ||
                   choice(4).asDigit < 1 || choice(4).asDigit > 9 || choice == "";
   
      // Check if there is a issue
      if(issues){
         println("Something wrong with the syntax...Try again")
      }
      else sdk.answerChecker(choice) 

      }
      sdk.printGrid()
      println("Congratulation " + name + " ! You solved the Sudoku !")
  
   }
}
