import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Bomb extends Actor
{
    private int timer = 0;
    
    public void act()
    {
        // increase Timer (60/s)
        timer++;
        
        if (((Board) getWorld()).tick%7== 0) {
            setLocation (getX(), getY() + ((Board) getWorld()).blockSpeed);
        }

        // Clear Image
        getImage().clear();
        
        // Set Image (Animation) based of timer
        if (timer >= 100) {
            getWorld().removeObject(this);
        } else if (timer >= 90) {
            setImage("explosion/5.png");
        } else if (timer >= 80) {
            setImage("explosion/4.png");
        } else if (timer >= 70) {
            setImage("explosion/3.png");
            // Get Blocks and Hit
            for (Block block: getIntersectingObjects(Block.class)) {
                block.hit("bomb");
            }
        } else if (timer >= 60) {
            setImage("explosion/2.png");
        } else if (timer >= 50) {
            setImage("explosion/1.png");
        } else {
            setImage("bomb.png");
        }
        
        // Set Image Size
        int size = (int)(24*Math.pow(1.02,timer));
        getImage().scale(size, size);
    }
}
