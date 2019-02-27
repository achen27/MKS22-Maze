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
    while (scan.hasNextLine()){
      String line = scan.nextLine();
      r++;
    }
    char[][] a = new char[r][scan.nextLine().length()];
    scan = new Scanner(output);
    for (int i = 0; i < a.length; i++){
      for (int j = 0; j < a[0].length; j++){
        a[i][j] = scan.next().charAt(0);
      }
    }
    return toString(a);
  }

  public static void main(String[] args){
    try {
      String s = args[0];
      System.out.println(printMaze(s));
    } catch (FileNotFoundException e){
      System.out.println("File not found");
    }
  }

}
