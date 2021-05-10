public abstract class Actor implements CanPlay{
    //属性name，int blood, int state,进攻指数，防守指数等
    //定义基础方法：
    //一般进攻，双击进攻、防守等
    public String name;
    public int blood;
    public int state; // here is country or status?
    public double indexAttact;
    public double indexDefend;
    abstract public double attack(Actor another);
    abstract public void defend();
    abstract public String giveType();
    public double giveIndexOfDefend(){
        return indexDefend;
    }

    // these abstract methods are waiting to be realized

}
