import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Upgrade extends Actor
{
    GreenfootImage image;
    public String upgradeType;
    
    public Upgrade(String type) {
        upgradeType = type; 
        switch(upgradeType) {
            case "laser":
                image = new GreenfootImage("beeper.png");
                break;
        }
        image.scale(16,16);
        setImage(image);
    }
    
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        setLocation (getX(), getY() + speed*2);
        
        if (getY() > 760) {
            getWorld().removeObject(this);
        }
            
    }
}
