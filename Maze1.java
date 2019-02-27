import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Maze1{

  public static String printMaze(String s) throws FileNotFoundException{
    File f = new File(s);
    Scanner scan = new Scanner(f);
    String output = "";
    while (scan.hasNextLine()){
      String line = scan.nextLine();
      output += line + "\n";
    }
    return output;
  }

  public static String printArray(String s) throws FileNotFoundException{
    File f = new File(s);
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
    System.out.println(r);
    char[][] a = new char[r][c];
    scan = new Scanner(output);
    for (int i = 0; i < a.length; i++){
      for (int j = 0; j < a[0].length; j++){
        if (scan.hasNext()){
          String str = scan.next();
          a[i][j] = str.charAt(0);
        }
      }
    }
    return toString(a);
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

  public static void main(String[] args){
    try {
      String s = args[0];
      System.out.println(printArray(s));
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
  }

}
