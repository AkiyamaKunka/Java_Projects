import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
public class JavaGo {
//    static private final String INPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/input.txt";
//    static private final String OUTPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/output.txt";
    public static void main(String[] args){
//        FileInputStream instream = null;
//        PrintStream outstream = null;
//        try {
//            instream = new FileInputStream(INPUT);
//            outstream = new PrintStream(new FileOutputStream(OUTPUT));
//            System.setIn(instream);
//            System.setOut(outstream);
//        } catch (Exception e) {
//            System.err.println("Error Occurred.");
//        }
        Scanner in = new Scanner(System.in);
//        // open I/O files
        System.out.println("Do you wanna play 1. Warrior vs Master, or 2. Witcher hunting Werewolf?");
        System.out.println("Input 1 or 2");
        int number;
        while(true) {
            number = in.nextInt();
            if (!(number == 1 || number == 2)) {
                System.out.println("Please choose the right number");
            }else break;
        }

        Play myPlay = new Play(number, false); // not use file input




        return;

     }
}