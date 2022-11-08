import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    private Bomb bomb;
    private Upgrade upgrade;
    
    public String type;
    private int health;
    
    public Block() {
        String img;
        int rand = Greenfoot.getRandomNumber(100);
        if (rand <= 5) {
                img = "blockLaser.png";
                health = 1;
                this.type = "laser";
        } else if (rand <= 15) {
                img = "blockBomb.png";
                health = 1;
                this.type = "bomb";
        } else if (rand <= 35) {
                img = "blockDouble.png";
                health = 2;
                this.type = "double";
        } else {
                img = "blockNormal.png";
                health = 1;
                this.type = "normal";
        }
        //System.out.println(type);
        setImage(img);
    }
    public void act()
    {
        Board board = (Board) getWorld();
        
        if (board.tick%6 == 0) {
            setLocation (getX(), getY() + board.blockSpeed);
        }
        
        if (getY() >= board.getHeight()-1) {
            System.out.println("Block Loss");
            board.lose();
        }
    }
    
    public void hit () {
        this.hit("");
    }
    public void hit(String DamageType) {
        Board board = (Board) getWorld();
        
        int damage = board.damage;
        if (DamageType == "Bomb") {
            damage = damage * 3;
        }
        health-=damage;
        
        if (type == "bomb") {
            bomb = new Bomb();
            getWorld().addObject( bomb, getX(), getY());
            bomb.setTick();
            board.addScore(1);
        } else if (type == "laser") { 
            upgrade = new Upgrade("laser");
            getWorld().addObject( upgrade, getX(), getY());
            board.addScore(1);
        } else if (type == "double") {
            setImage("blockDouble2.png");
            board.addScore(4);
        } else {
            board.addScore(2);
        }
        
        if (health < 1) {
            getWorld().removeObject(this);
            board.addGameInfo("destroyed", type);
        }
    }
}
