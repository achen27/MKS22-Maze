import java.util.*;
import java.io.*;

public class Maze{

  private char[][] maze;
  private boolean animate;//false by default

  /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
  */
  public Maze(String filename) throws FileNotFoundException{
    animate = false;

    File f = new File(filename);
    Scanner scan = new Scanner(f);
    String output = "";
    int r = 0;
    int c = 0;

    while (scan.hasNextLine()){
      String line = scan.nextLine();
      c = line.length();
      output += line;
      r++;
    }

     maze = new char[r][c];
    int idx = 0;
    int countS = 0;
    int countE = 0;

    for (int i = 0; i < maze.length; i++){
      for (int j = 0; j < maze[0].length; j++){
        maze[i][j] = output.charAt(idx);
        if (maze[i][j] == 'S'){
          countS++;
        }
        if (maze[i][j] == 'E'){
          countE++;
        }
        idx++;
      }
    }

    if (countS != 1 || countE != 1){
      throw new IllegalStateException("Too many/No Start/End");
    }

  }

  public String toString(){
    String output = "";
    for (int i = 0; i < maze.length; i++){
      for (int j = 0; j < maze[0].length; j++){
        output += maze[i][j];
        if (j == maze[0].length - 1){
          output += "\n";
        }
      }
    }
    return output;
  }

  private void wait(int millis){
    try {
      Thread.sleep(millis);
    }catch (InterruptedException e) {

    }
   }

  public void setAnimate(boolean b){
      animate = b;
  }

  public void clearTerminal(){
      //erase terminal, go to top left of screen.
      System.out.println("\033[2J\033[1;1H");
  }

  /*Wrapper Solve Function returns the helper function
    Note the helper function has the same name, but different parameters.
    Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
  */
  public int solve(){
    int r;
    int c;
    for (int i = 0; i < maze.length; i++){
      for (int j = 0; j < maze[0].length; j++){
        if (maze[i][j] == 'S'){
          r = i;
          c = j;
        }
      }
    }

          //erase the S

          //and start solving at the location of the s.
          //return solve(???,???);
  }

}
