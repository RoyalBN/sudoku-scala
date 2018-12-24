import scala.io.Source

object Sudoku extends App {
  
  // ***********************************************************************
  //                             VARIABLES
  // ***********************************************************************
  
  val sudoku = 
  Array(
        Array(7, 9, 4, 1, 2, 8, 3, 6, 5),
        Array(6, 3, 2, 5, 4, 9, 1, 7, 8),
        Array(8, 5, 1, 3, 6, 7, 9, 2, 4),
        Array(2, 7, 9, 8, 3, 1, 5, 4, 6),
        Array(4, 6, 3, 2, 0, 5, 8, 1, 7),
        Array(5, 1, 8, 6, 7, 4, 2, 9, 3),
        Array(1, 8, 6, 7, 5, 2, 4, 3, 9),
        Array(3, 4, 5, 9, 1, 6, 7, 8, 2),
        Array(0, 2, 7, 4, 8, 3, 6, 5, 0)
  )

  val sudoku_solution = 
  Array(
        Array(7, 9, 4, 1, 2, 8, 3, 6, 5),
        Array(6, 3, 2, 5, 4, 9, 1, 7, 8),
        Array(8, 5, 1, 3, 6, 7, 9, 2, 4),
        Array(2, 7, 9, 8, 3, 1, 5, 4, 6),
        Array(4, 6, 3, 2, 9, 5, 8, 1, 7),
        Array(5, 1, 8, 6, 7, 4, 2, 9, 3),
        Array(1, 8, 6, 7, 5, 2, 4, 3, 9),
        Array(3, 4, 5, 9, 1, 6, 7, 8, 2),
        Array(9, 2, 7, 4, 8, 3, 6, 5, 1)
  )


  // Alphabetical letters for rows
  val letters = Array("A","B","C","D","E","F","G","H","I");

  // Load the rules file
  val filename = "src/main/scala/example/files/rules.txt"
  
  // Terminal colors settings
  val ANSI_RESET = "\u001B[0m";
  val ANSI_RED = "\u001B[31m";
  val ANSI_GREEN = "\u001B[32m";
  val ANSI_RED_BACKGROUND = "\u001B[41m";
  val ANSI_GREEN_BACKGROUND = "\u001B[42m";

  // Variable for finishing the game
  var game_finished = 0



  // ***********************************************************************
  //                             FUNCTIONS
  // ***********************************************************************
  
  // *********************
  //   Print Sudoku grid
  // *********************
  def printGrid(args: Array[Array[Int]]) = {
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

  

  // ***********************************************************************
  //                                MAIN
  // ***********************************************************************
  
  // Print player's name
  val name = scala.io.StdIn.readLine("What's your name? ")
  println("Hello " + name)

  // Print the rules for playing the game  
  for (line <- Source.fromFile(filename).getLines) {
     println(line)
  }


  // Main loop 
  while(game_finished != 1){

    // Print the Sudoku grid
    printGrid(sudoku)
       
    // User entries
    var choice = scala.io.StdIn.readLine("Choose a position and a number:")
    val issues = choice.length() != 5 || choice(1) != ','|| choice(3) != ':' ||
                 choice(2).asDigit < 1 || choice(2).asDigit > 9 ||
                 choice(4).asDigit < 1 || choice(4).asDigit > 9 || choice == "";
  
    // Check if there is a issue
    if(issues){
       println("Something wrong with the syntax...Try again")
    }
    else answerChecker(choice)  
   
  }
  printGrid(sudoku)
  println("Congratulation " + name + " ! You solved the Sudoku !")

}