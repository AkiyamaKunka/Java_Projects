import java.util.Random;
import java.util.Scanner;

public abstract class Game {
    //包括：角色数量.
    static int numberOfActors;
    //Actor a1,
    //Actor a2,
    public Actor userActor;
    public Actor PCActor;
    //定义成员函数（包括抽象）：
    abstract public void randomActor();//设置角色类型，创建随机角色
    abstract public void setActor(String type);//根据用户输入创建特定角色
    public void play(){
        //    定义play方法： 这里还没写完
        //    a1和a2对抗，选择防守的先出招，输出blood比较高的角色。
        Scanner in = new Scanner(System.in);
        while(userActor.state == 1 && PCActor.state == 1) {
            System.out.println("Choose your action: Defend or Attack");
            String userAction = in.nextLine();
            if(!(userAction.equals("Attack") || userAction.equals("Defend"))){
                System.out.println("Input Error, please try again");
                continue;
            }
            String PCAction = randomAction();

            if (userAction.equals("Defend")) {
                userActor.defend();
                System.out.printf("User Defend! Now defend index is %.0f\n", userActor.giveIndexOfDefend());
            }
            if (PCAction.equals("Defend")) {
                PCActor.defend();
                System.out.printf("PC Defend! Now defend index is %.0f\n", PCActor.giveIndexOfDefend());
            }
            if (userAction.equals("Attack"))
                System.out.printf("Damage caused by user is %.0f \n", userActor.attack(PCActor));
            if (PCAction.equals("Attack"))
                System.out.printf("Damage caused by PC is %.0f \n", PCActor.attack(userActor));

            printRoundEnd(userActor, PCActor);
            judgeState(userActor, PCActor);
            System.out.println("one round finished");
        }
        printBattleEnd(userActor, PCActor);
    }
    public void printRoundEnd(Actor user, Actor PC){
        System.out.println("Play's " + userActor.giveType() +
                " blood is " + user.blood);
        System.out.println("PC's " + PCActor.giveType() +
                " blood is " + PC.blood);
    }

    public void printBattleEnd(Actor user, Actor PC){
        if(user.state == PC.state){
            System.out.println("Drew!");
        }else if(user.state == 0){
            System.out.println("PC won!");
        }else{
            System.out.println("User won!");
        }
    }
    public void judgeState(Actor user, Actor PC){
        if(user.blood <= 0)
            user.state = 0;
        if(PC.blood <= 0)
            PC.state = 0;
    }
    public String randomAction(){
        Random ra = new Random();
        int choiceNumberOfActor = ra.nextInt(2); // randomly give 1 or 2
        if(choiceNumberOfActor == 1)
            return "Attack";
        else
            return "Defend";
    }
}

