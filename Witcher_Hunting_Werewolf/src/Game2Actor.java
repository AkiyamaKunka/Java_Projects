import java.util.Random;

public class Game2Actor {
    public Actor giveWitcher(){
        return new Game2Actor.Witcher();
    }
    public Actor giveWerewolf(){
        return new Game2Actor.Werewolf();
    }
    public class Witcher extends Actor{  // Master继承Actor
        Witcher(){
            System.out.println("Witcher created!");
            name = "Master_1";
            blood = 100;
            state = 1; // alive is 1, dead is 0
            indexAttact = 150;
            indexDefend = 2;
        }
        //    默认blood=100
        //    实现攻击方法：
        //    例如：如果对方是同类，则一般进攻，进攻等于攻击指数除以防守指数。
        //    如果非同类，则double进攻，进攻等于攻击指数*2除以防守指数。
        @Override
        public double attack(Actor another) {
            Random ra = new Random();
            int crit = ra.nextInt(5);

            double damage;
            if(crit == 4) // which means witcher will give a critic attack
            {
                damage = (this.indexAttact * 10) / another.indexDefend;
                System.out.println("\"Surrender to the power of thunder!\" (Witcher Crit)");
            }else{      // normal attack with random damage
                damage = (this.indexAttact * crit) / another.indexDefend;
            }
            another.blood -= damage;
            return damage;
        }
        @Override
        public void defend() {
            Random ra = new Random();
            int recover = ra.nextInt(3);
            if(recover == 1) {
                System.out.println("\"Lord heard my prayer!\" (Witcher HP + 100)");
                this.blood += 100;
            }
        }
        @Override
        public String giveType() {
            return "Witcher";
        }
        //  定义内部私有不同类型的Actor：
    }
    public class Werewolf extends Actor implements CanPlay{ //    Warrior继承Actor
        //    默认blood=300
        //    攻击方法，例如：如果对方是同类，double进攻，进攻等于攻击指数*2除以防守指数。
//    如果非同类，则一般进攻，进攻等于攻击指数除以防守指数。
        Werewolf(){
            System.out.println("Werewolf created!");
            name = "Werewolf";
            blood = 400;
            state = 1; // alive is 1, dead is 0
            indexAttact = 50;
            indexDefend = 3;
        }
        @Override
        public void defend() {
            indexDefend += 3;
            System.out.println("Werewolves become stronger on full moon night..");
        }
        @Override
        public String giveType() {
            return "Werewolf";
        }
        @Override
        public double attack(Actor another) {
            double damage;
            damage = this.indexAttact / another.indexDefend;
            another.blood -= damage;
            indexAttact = indexAttact * 1.5;
            System.out.println("Werewolves feel more hungry...");
            return damage;
        }
    }
}

