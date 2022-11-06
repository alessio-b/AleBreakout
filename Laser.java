import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Actor
{
    private Block block;
    private GreenfootImage image = getImage();
    public Laser() {
        image.scale(6,16);
        setImage(image);
    }
    
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        setLocation (getX(), getY() - speed*2);
        
        Block block = (Block) getOneIntersectingObject(Block.class);
        if (block != null) {
            block.hit();
            getWorld().removeObject(this);
            return;
        }
        if (getY() < 1) {
            getWorld().removeObject(this);
        }
    }
}
