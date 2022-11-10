import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Upgrade extends Actor
{
    // Setup UpgradeInfo
    private GreenfootImage image;
    public String upgradeType;
    
    public Upgrade(String type) {
        upgradeType = type; 
        // Set Image based on UpgradeType
        switch(upgradeType) {
            case "laser":
                image = new GreenfootImage("beeper.png");
                break;
            case "extraLive":
                image = new GreenfootImage("extraLive.png");
                break;
        }
        image.scale(16,16);
        setImage(image);
    }
    
    public void act()
    {
        Board board = ((Board) getWorld());
        
        // Move Upgrade
        setLocation (getX(), getY() + board.blockSpeed*2);
        
        // Check if out of Bounce
        if (getY() >= board.getHeight()-1) {
            board.removeObject(this);
        }
            
    }
}
