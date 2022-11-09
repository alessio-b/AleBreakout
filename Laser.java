import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Actor
{
    public void act()
    {   
        // Move Laser
        setLocation (getX(), getY() - 8);
        
        // Create Smoke Trail (every 0.1s)
        if (((Board) getWorld()).tick%4==0) {
            getWorld().addObject ( new Smoke(), getX(), getY());
        }
        
        // Check for Block Collision or Out of Bounce
        if (isTouching(Block.class)) {
            Block block = (Block) getOneIntersectingObject(Block.class);
            block.hit();
            getWorld().removeObject(this);
        } else if (getY() < 1) {
            getWorld().removeObject(this);
        }
    }
}
