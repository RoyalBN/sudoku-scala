object Sudoku extends App {
  val easy_sudoku = 
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

  // val name = scala.io.StdIn.readLine("What's your name? ")
  // println("Hello " + name)



  // Print Sudoku grid
  def printGrid(args: Array[Array[Int]]) = {
      var i = 0; // Vertical line
      var j = 0; // Horizontal line
      var a = 0;
      var counter = 0;

      // For loop: Print all rows
      println();
      for(i <- 0 to 8){ 
        for(j <- 0 to 8){
            print(easy_sudoku(i)(j) + " ");
        }   
        println();
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

  printGrid(easy_sudoku)



}