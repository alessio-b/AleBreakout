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
        Board board = ((Board) getWorld());
        
        setLocation (getX(), getY() + board.blockSpeed*2);
        
        if (getY() >= board.getHeight()-1) {
            board.removeObject(this);
        }
            
    }
}
