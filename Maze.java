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
      try{

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

        char[][] a = new char[r][c];
        int idx = 0;
        int countS = 0;
        int countE = 0;

        for (int i = 0; i < a.length; i++){
          for (int j = 0; j < a[0].length; j++){
            a[i][j] = output.charAt(idx);
            if (a[i][j] == 'S'){
              countS++;
            }
            if (a[i][j] == 'E'){
              countE++;
            }
            idx++;
          }
        }

        if (countS != 1 || countE != 1){
          throw new IllegalStateException("Too many/No Start/End");
        }

      }catch(FileNotFoundException e){
        System.out.println("File Not Found");
      }

    }

    public static String toString(char[][] c){
      String output = "";
      for (int i = 0; i < c.length; i++){
        for (int j = 0; j < c[0].length; j++){
          output += c[i][j];
          if (j == c[0].length - 1){
            output += "\n";
          }
        }
      }
      return output;
    }

}
