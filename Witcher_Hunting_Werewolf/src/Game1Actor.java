public class Game1Actor {

    public Actor giveWarrior(){
        return new Warrior();
    }
    public Actor giveMaster(){
        return new Master();
    }
    public class Master extends Actor{  // Master继承Actor
        Master(){
            System.out.println("Master created!");
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
            double damage;
            if(another.giveType().equals(this.giveType()))
            {
                damage = this.indexAttact / another.indexDefend;
            }else{
                damage = (this.indexAttact * 2) / another.indexDefend;
            }

            another.blood -= damage;
            return damage;
        }
        @Override
        public void defend() {
            indexDefend++;
        }
        @Override
        public String giveType() {
            return "Master";
        }

        //  定义内部私有不同类型的Actor：

    }
    public class Warrior extends Actor implements CanPlay{ //    Warrior继承Actor
          //    默认blood=300
        //    攻击方法，例如：如果对方是同类，double进攻，进攻等于攻击指数*2除以防守指数。
//    如果非同类，则一般进攻，进攻等于攻击指数除以防守指数。
        Warrior(){
            System.out.println("Warrior created!");
            name = "Warrior_1";
            blood = 300;
            state = 1; // alive is 1, dead is 0
            indexAttact = 40;
            indexDefend = 3;
        }
        @Override
        public void defend() {
            indexDefend += 3;
        }
        @Override
        public String giveType() {
            return "Warrior";
        }
        @Override
        public double attack(Actor another) {
            double damage;
            if(!another.giveType().equals(this.giveType()))
            {
                damage = this.indexAttact / another.indexDefend;
            }else{
                damage = (this.indexAttact * 2) / another.indexDefend;
            }
            another.blood -= damage;
            return damage;
        }

    }
}
