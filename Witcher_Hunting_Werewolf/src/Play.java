import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
public class Play {
    static private final String INPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/input.txt";
    static private final String OUTPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/output.txt";
    Play(int gameNumber, boolean isFileInput) {
        if(isFileInput) {           // use file input and output, unfinished
            FileInputStream instream = null;
            PrintStream outstream = null;
            try {
                instream = new FileInputStream(INPUT);
                outstream = new PrintStream(new FileOutputStream(OUTPUT));
                System.setIn(instream);
                System.setOut(outstream);
            } catch (Exception e) {
                System.err.println("Error Occurred.");
            }
            Scanner in = new Scanner(System.in);
            // open I/O files
        }else {
            if (gameNumber == 1) {
                System.out.println("Select your actor: Warrior or Master");
                Scanner in = new Scanner(System.in);
                String UserActor;
                while (true) {
                    UserActor = in.nextLine();
                    if (UserActor.equals("Warrior") || UserActor.equals("Master"))
                        break;
                    else {
                        System.out.println("Please choose the right type of actor");
                    }
                }
                Game1 UserGame = new Game1(UserActor);

            }else{      // play the second game
                System.out.println("Select your actor: Witcher or Werewolf");
                Scanner in = new Scanner(System.in);
                String UserActor;
                while (true) {
                    UserActor = in.nextLine();
                    if (UserActor.equals("Witcher") || UserActor.equals("Werewolf"))
                        break;
                    else {
                        System.out.println("Please choose the right type of actor");
                    }
                }
                Game2 UserGame = new Game2(UserActor);
            }
        }
    }
}