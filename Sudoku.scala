import scala.io.Source

object Sudoku extends App {
  

  //***********************************************************************
  //                             VARIABLES
  //***********************************************************************
  
  val sudoku = 
  Array(
        Array(0, 0, 4, 0, 2, 8, 0, 0, 5),
        Array(0, 3, 2, 5, 0, 9, 0, 7, 0),
        Array(0, 5, 0, 0, 6, 0, 9, 0, 0),
        Array(2, 7, 0, 0, 3, 1, 0, 4, 0),
        Array(4, 6, 0, 0, 0, 0, 0, 1, 7),
        Array(0, 1, 0, 6, 7, 0, 0, 9, 3),
        Array(0, 0, 6, 0, 5, 0, 0, 3, 0),
        Array(0, 4, 0, 9, 0, 6, 7, 8, 0),
        Array(9, 0, 0, 4, 8, 0, 6, 0, 0)
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
  val ANSI_RED_BACKGROUND = "\u001B[41m";
  val ANSI_GREEN_BACKGROUND = "\u001B[42m";



  //***********************************************************************
  //                             FUNCTIONS
  //***********************************************************************
  
  // Print Sudoku grid
  def printGrid(args: Array[Array[Int]]) = {
      var i = 0; // Vertical line
      var j = 0; // Horizontal line
      var a = 0;
      var indice = 0;

      // Print letters and numbers for rows and cols
        
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
        // Print the letters
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
  
  /*
  def rowChecker(row: Array[Array[Int]]) = {
    // For loop: Check if a row only have different numbers
  }

  def colChecker(col: Array[Array[Int]]) = {
    // For loop: Check if a col only have different numbers
  }
  */


  def zeroChecker(args: Array[Array[Int]]){
    // For loop: Check there is/are zero
  }




  //***********************************************************************
  //                               MAIN
  //***********************************************************************
  
  // val name = scala.io.StdIn.readLine("What's your name? ")
  // println("Hello " + name)

  // Print the rules for playing the game  
  for (line <- Source.fromFile(filename).getLines) {
    println(line)
  }


  printGrid(sudoku)

  
}