import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser extends Actor
{
    public void act()
    {   
        // Move Laser
        setLocation (getX(), getY() - 8);
        
        // Create Smoke Trail
        getWorld().addObject(new Trail(), getX(), getY());
        
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
