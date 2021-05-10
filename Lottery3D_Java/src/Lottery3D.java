import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;
abstract class BaseGame {
    public Integer[] userNumber;
    protected Integer[] winNumber;
    private String userInput;
    protected int ans; // the number to return money to Test class need some change

    BaseGame(Integer[] winNumber){
        this.winNumber = winNumber;
    }
    BaseGame(Integer[] winNUmber, String Input){
        this.winNumber = winNUmber;
        userInput = Input;
        setUserNumber();
    }
    public Integer[] getUserNumber(){
        return userNumber;
    }

    public abstract int getWins();
    public abstract boolean isValid();
    protected void setUserNumber(){
        userNumber = stringToIntArray(userInput);
    }
    protected Integer[] stringToIntArray(String s){
        int size = s.length();
        Integer[] intArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = (int) (s.charAt(i) - 48);
        }
        return intArray;
    }
    public int getMoney(){return ans;}
}
enum Choice {
    SUM,
    SINGLE,
    GROUP,
    ONED,
    GUESS1D,
    TOWD,
    TRACTOR,
    GENERAL,
    PACKAGE,
    NOPE
}
class General extends BaseGame {
    private int type; // type = 1 and type 2 mean hit the jackpot
    General(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        if(isValid()){
            if(type == 1) return 470;
            if(type == 2) return 21;
        }
        return 0;
    }

    @Override
    public boolean isValid() {
        type = 0;

        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if(winNumber[i].equals(userNumber[i])) cnt++;
        }
        if(cnt == 3){
            type = 1; return true;
        }
        cnt = 0;
        for (int i = 0; i < 3; i++) {
            if(winNumber[i].equals(userNumber[i])) cnt++;
        }
        if(cnt == 2){
            type = 2; return true;
        }
        return false;
    }
}
class Group extends BaseGame {
    int type; // type 1 is 346 yuan, type 2 is 173 yuan

    Group(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        if(!isValid())
            return 0;
        if(type == 1)
            return 346;
        else  // type is 2 here
            return 173;
    }

    @Override
    public boolean isValid() {
        int pivot1 = userNumber[0];
        int win1Index = -1;
        for (int i = 0; i < 3; i++) {
            if(pivot1 == winNumber[i])
                win1Index = i;
        } // the first digit

        int pivot2 = userNumber[1];
        int win2Index = -1;
        for (int i = 0; i < 3; i++) {
            if(i != win1Index && pivot2 == winNumber[i])
                win2Index = i;
        } // the second digit

        int pivot3 = userNumber[2];
        int win3Index = -1;
        for (int i = 0; i < 3; i++) {
            if(i != win1Index &&
                    i != win2Index &&
                    pivot3 == winNumber[i])
                win3Index = i;
        } // the third digit
        if(win1Index == -1 || win2Index == -1 ||
                win3Index == -1)
            return false;

        if(pivot1 == pivot2 || pivot1 == pivot3
                || pivot2 == pivot3)
            type = 1;
        else
            type = 2;
        return true;
    }
}
class Guess1D extends BaseGame {

    Guess1D(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        int pivot = userNumber[0];
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if(pivot == winNumber[i])
                cnt++;
        }
        if(cnt == 0)
            return 0;
        else if(cnt == 1)
            return 2;
        else if(cnt == 2){
            return 12;
        }else if(cnt == 3)
            return 230;
        return -1;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
class OneD extends BaseGame {

    OneD(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        if(!isValid())          // not valid
            return -1;
        for (int i = 0; i < 3; i++) {
            if(userNumber[i] != -6 && userNumber[i].equals(winNumber[i]))
                return 10; // win money
        }
        return 0; // no money
    }

    @Override
    public boolean isValid() {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            //System.out.println("user number is " + userNumber[i]);
            if(userNumber[i] == -6)
                cnt++;

        }
        return cnt == 2;

    }
}
class Package extends BaseGame {
    private int type;
    private static final int [] money = {
            0, 693, 173, 606, 86
    };
    // 0 means no nomey, 1 : 693, 2: 173, 3 : 606, 4 :86
    Package(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        isValid();
        return money[type];
    }

    public boolean hasSameElement() {
        String[] array1 = {winNumber[0].toString(), winNumber[1].toString(), winNumber[2].toString()};
        String[] array2 = {userNumber[0].toString(), userNumber[1].toString(), userNumber[2].toString()};
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (Arrays.equals(array1, array2)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid() {
        type = 0;
        if (!hasSameElement()) return false; // 0 money
        if (    winNumber[0] == userNumber[0] &&
                winNumber[1] == userNumber[1] &&
                winNumber[2] == userNumber[2]) {
            if (    winNumber[0] == winNumber[1] ||
                    winNumber[1] == winNumber[2] ||
                    winNumber[0] == winNumber[2]) {
                type = 1;
            } else type = 3;
            return true;
        }
        if (    winNumber[0] == winNumber[1] ||
                winNumber[1] == winNumber[2] ||
                winNumber[0] == winNumber[2]) {
            type = 2;
        }else{
            type = 4;
        }
        return true;
    }

}
class Single extends BaseGame {

    Single(Integer[] winNumber, String input) {
        super(winNumber, input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        return isValid() ? 1040 : 0;
    }
    @Override
    public boolean isValid() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if(winNumber[i] != userNumber[i])
                return false;
            sum += winNumber[i];
        }
        return true;
    }
}
class Sum extends BaseGame {
    public final static Integer[] money;
    static{
        // initialize money, index is sum of numbers, from
        // 0 to 27
        money = new Integer[]{
                1040, 345, 172, 104, 69,
                49, 37, 29, 23, 19,
                16, 15, 15, 14, 14,
                15, 15, 16, 19, 23,
                29, 37, 49, 69, 104,
                172, 345, 1040
        };
    }
    Sum(Integer[] winNUmber, String Input){
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        int sumUser = 0;
        int sumWin = 0;

        for (int i = 0; i < 3; i++) {

            if(i < userNumber.length) {
                sumUser += userNumber[i] * Math.pow(10, userNumber.length - i - 1);
                //System.out.println("power is " + Math.pow(10, userNumber.length - i - 1));
                //System.out.println("sum is " + sumUser);
            }

            sumWin += winNumber[i];

        }
        return sumUser == sumWin ? money[sumUser] : 0;
    }

    @Override
    public boolean isValid() {

        // wait to realize
        return false;
    }

}
class Test {
    //static private final String INPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/input.txt";
    //static private final String OUTPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/output.txt";
    private int gameAns;
    /**
     * 随机生产 factor 位的数字，最大不超过 19位，因为long的最大值为19位
     * @param factor
     * @return
     */
    public static String characters1 = "0123456789";
    public static String randomStr(int factor){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < factor; i++) {
            // nextInt(10) = [0, 10)
            sb.append(characters1.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }
    public int getGameAns(){
        return gameAns;
    }
    Test(Choice x, boolean useRandom, int defaultWinNumber, String userNumString) {
        Scanner in = new Scanner(System.in);
        Integer[] winNumber = new Integer[3];
        String userInput;

        if (useRandom) { //  we chose to use random WinNumber
            userInput = randomStr(3);
            //System.out.println("Your preferred number is:");
        } else {
            userInput = String.valueOf(defaultWinNumber);
            //System.out.println("The win number is:" + userInput);
        }
        for (int i = 0; i < 3; i++) {
            winNumber[i] = (int) (userInput.charAt(i) - 48);
        }

        userInput = userNumString; // pass user's number to this argument
        BaseGame game; // game class declaration

        if (x == Choice.SUM) {
            game = new Sum(winNumber, userInput);
        }else if(x == Choice.SINGLE){
            game = new Single(winNumber, userInput);
        }else if(x == Choice.GROUP){
            game = new Group(winNumber, userInput);
        }else if(x == Choice.ONED){
            game = new OneD(winNumber, userInput);
        }
        else if(x == Choice.GUESS1D){
            game = new Guess1D(winNumber, userInput);
        }else if(x == Choice.TOWD){
            game = new Towd(winNumber, userInput);
        }else if(x == Choice.TRACTOR){
            game = new Tractor(winNumber);
        }else if(x == Choice.GENERAL){
            game = new General(winNumber, userInput);
        }else if(x == Choice.PACKAGE) {
            game = new Package(winNumber, userInput);
        }else {
            game = new Sum(winNumber, userInput); // it's wrong, just to past compile
        }
        gameAns = game.getMoney();
    }
}
class Towd extends BaseGame {

    Towd(Integer[] winNUmber, String Input) {
        super(winNUmber, Input);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if(userNumber[i] == -6)
                continue;
            if (userNumber[i] == winNumber[i])
                cnt++;
        }
        if(cnt == 2)
            return 104;
        return 0;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
class Tractor extends BaseGame {


    Tractor(Integer[] winNumber) {
        super(winNumber);
        ans = getWins();
        //System.out.println(ans);
    }

    @Override
    public int getWins() {
        return isValid() ? 65 : 0;
    }

    @Override
    public boolean isValid() {
        return winNumber[0] + 1 == winNumber[1] && winNumber[1] + 1 == winNumber[2] ||
                winNumber[2] + 1 == winNumber[1] && winNumber[1] + 1 == winNumber[0];
    }
}
class UserInput {
    private static final String [] model_array = {
            "group",
            "guess1d",
            "oned",
            "single",
            "sum",
            "towd",
            "tractor",
            "general",
            "package"
    };
    private String userInput;
    private String userModel;
    private String userNumString;
    UserInput(String userInputFromMain){
        //Scanner in = new Scanner(System.in);
        userInput = userInputFromMain; // get user's input of model
        if(!userInput.equals("tractor")){
            String [] s = userInput.split(" ");
            userModel = s[0];
            userNumString = s[1];
        }else{
            userModel = userInput;
            userNumString = "000";
        }
        if (!judgeValid()) {
            System.out.println("The game model does not exist, please try again.");
            //userInput = in.nextLine();
        }
    }
    public boolean judgeValid(){
        boolean isValid = true;
        for (int i = 0; i < model_array.length; i++) {
            if(userModel.equals(model_array[i]))
                return true;
        }
        return false;
    }

    public String getUserNumString() {
        return userNumString;
    }

    public Choice giveTypeOfGame(){
        if(userModel.equals("group")) {
            //System.out.println("The number should be from 000 to 999");
            return Choice.GROUP;
        }
        if(userModel.equals("guess1d")) {
            //System.out.println("The number should be from 1**");
            return Choice.GUESS1D;
        }
        if(userModel.equals("oned")) {
            //System.out.println("The number should be like *2* or 1** or **4");
            return Choice.ONED;
        }
        if(userModel.equals("single")) {
            //System.out.println("The number should be from 000 to 999");
            return Choice.SINGLE;
        }
        if(userModel.equals("sum")) {
            //System.out.println("The number should be from 000 to 999");
            return Choice.SUM;
        }
        if (userModel.equals("tractor")) {
            //System.out.println("Does not need number input");
            return Choice.TRACTOR;
        }
        if (userModel.equals("general")){
            //System.out.println("Input your number:");
            return Choice.GENERAL;
        }
        if (userModel.equals("package")){
            //System.out.println("Input your number");
            return Choice.PACKAGE;
        }
        else return Choice.NOPE;
    }
}

public class Main {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int winNumber = in.nextInt();
        int round = in.nextInt();
        in.nextLine();

        PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() { // maximum priority_queue
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < round; i++) {
            //System.out.println("Please select your game model: \ngroup, guess1d, oned, single, sum, towd, tractor, general,  \n");
            String userInputFromMain = in.nextLine();
            //System.out.println("your input is " + userInputFromMain);

            UserInput inputClass = new UserInput(userInputFromMain);
            if (inputClass.judgeValid()) {
                Test testSum = new Test(inputClass.giveTypeOfGame(), false, winNumber, inputClass.getUserNumString());
                q.add(testSum.getGameAns());
            }
        }
        System.out.println(q.peek());
    }
}