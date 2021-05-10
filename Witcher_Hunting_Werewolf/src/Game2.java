import java.util.Random;

public class Game2 extends Game {
    Game2(String UserActor) {
        setActor(UserActor);
        randomActor();
        // 开打 一会写
        play();
    }


    @Override
    public void randomActor() {
        Random ra = new Random();
        int choiceNumberOfActor = ra.nextInt(2); // randomly give 1 or 2
        Game2Actor actorSample1 = new Game2Actor();
        if (choiceNumberOfActor == 1)
            PCActor = actorSample1.giveWitcher();
        else
            PCActor = actorSample1.giveWerewolf();
    }

    @Override
    public void setActor(String type) {
        Game2Actor actorSample1 = new Game2Actor();
        if (type.equals("Witcher")) {
            userActor = actorSample1.giveWitcher();
        } else { // which means the play chose Werewolf
            userActor = actorSample1.giveWerewolf();
        }
    }

}


