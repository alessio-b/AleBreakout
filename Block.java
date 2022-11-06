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
    GreenfootImage image = new  GreenfootImage(16, 16);
    
    public Block() {
        
        int rand = Greenfoot.getRandomNumber(100);
        if (rand <= 20) {
                image.setColor(new Color(0, 0, 255));
                health = 1;
                type = "laser";
        } else if (rand <= 40) {
                image.setColor(new Color(0, 0, 0));
                health = 1;
                type = "bomb";
        } else if (rand <= 60) {
                image.setColor(new Color(0, 255, 0));
                health = 2;
                type = "double";
        } else {
                image.setColor(new Color(255, 0, 0));
                health = 1;
                type = "normal";
        }
        image.drawRect(0, 0, 100, 100);
        image.fill();
        setImage(image);
    }
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        if (tick%6 == 0) {
            setLocation (getX(), getY() + speed);
        }
        
        if (getY() >= 760) {
            getWorld().removeObjects(getWorld().getObjects(Block.class));
        }
    }
    
    public void hit () {
        this.hit("");
    }
    public void hit(String DamageType) {
        Board board = (Board) getWorld();
        
        int damage = ((Board) getWorld()).damage;
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
            image.setColor(new Color(0, 128, 0));
            setImage(image);
            board.addScore(4);
        } 
        
        if (health < 1) {
            getWorld().removeObject(this);
        }
    }
}
