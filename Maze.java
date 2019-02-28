import java.util.*;
import java.io.*;

public class Maze{

  private char[][] maze;
  private boolean animate;//false by default
  private int[] rows = {1,0,-1,0}; //arrays to store possible
  private int[] cols = {0,1,0,-1};         //ways to move

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
    try{
      File f = new File(filename); //creats new file
      Scanner scan = new Scanner(f); //creates new scanenr
      String output = ""; //string to move file contents into
      int r = 0; //variable to store number of line
      int c = 0; //variable to store length of line

      while (scan.hasNextLine()){ //reading file into string
        String line = scan.nextLine();
        c = line.length();
        output += line;
        r++;
      }

      maze = new char[r][c]; //creates new 2Dchar array for maze
      int idx = 0; //variable to keep track of location in output string
      int countS = 0; //variable to keep track of number of number of S
      int countE = 0; //variable to keep track of number of number of E

      for (int i = 0; i < maze.length; i++){ //moving string into array
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

      if (countS != 1 || countE != 1){ //checks to see if there is exactly one S and E
        throw new IllegalStateException("Too many/No Start/End");
      }
    } catch(FileNotFoundException e){
      System.out.println("Invalid filename: "+filename);

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
    int r = 0;
    int c = 0;
    for (int i = 0; i < maze.length; i++){
      for (int j = 0; j < maze[0].length; j++){
        if (maze[i][j] == 'S'){
          r = i;
          c = j;
        }
      }
    }
    maze[r][c] = '@';
    System.out.println("START AT:" + r + " " + c);
    return solve(r,c,0);
  }

  /*
        Recursive Solve function:

        A solved maze has a path marked with '@' from S to E.

        Returns the number of @ symbols from S to E when the maze is solved,
        Returns -1 when the maze has no solution.

        Postcondition:
          The S is replaced with '@' but the 'E' is not.
          All visited spots that were not part of the solution are changed to '.'
          All visited spots that are part of the solution are changed to '@'
      */
  private int solve(int row, int col, int count){ //you can add more parameters since this is private

        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(100);
        }
        if (maze[row][col] == 'E'){
          maze[row][col] = 'E';
          return count;
        }
        //System.out.println(row + " " + col);
        for (int i = 0; i < 4; i++){
            //System.out.println(this.toString());

          if (maze[row+rows[i]][col+cols[i]] == ' '  || maze[row+rows[i]][col+cols[i]] == 'E'){
            if (maze[row+rows[i]][col+cols[i]] == ' '){
                maze[row+rows[i]][col+cols[i]] = '@';
            }
            int c = solve(row+rows[i],col+cols[i],count+1);
            if (c != -1){
              count = c;
              return count;
            } else {
              maze[row+rows[i]][col+cols[i]] = '.';
            }
          }

        }
        //System.out.println("count = " +count);
        //System.out.println("no end");
        return -1; //so it compiles
    }

    private int countSteps(){
      int c = 0;
      for (int i = 0; i < maze.length; i++){
        for (int j = 0; j < maze[0].length; j++){
          if (maze[i][j] == '@'){
            c++;
          }
        }
      }
      return c;
    }


}
