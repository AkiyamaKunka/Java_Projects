import java.util.Random;
import java.util.Scanner;

public class Game1 extends Game{

    Game1(String UserActor){
        setActor(UserActor);
        randomActor();
        // 开打 一会写
        play();
    }


    @Override
    public void randomActor() {
        Random ra = new Random();
        int choiceNumberOfActor = ra.nextInt(2); // randomly give 1 or 2
        Game1Actor actorSample1 = new Game1Actor();
        if(choiceNumberOfActor == 1)

            PCActor = actorSample1.giveWarrior();
        else
            PCActor = actorSample1.giveMaster();
    }

    @Override
    public void setActor(String type) {
        Game1Actor actorSample1 = new Game1Actor();
        if(type.equals("Warrior")) {
            userActor = actorSample1.giveWarrior();
        }else { // which means the play chose Master
            userActor = actorSample1.giveMaster();
        }
    }


}