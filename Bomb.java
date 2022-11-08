import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private int startTick;
    
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).blockSpeed;
        
        if (tick%7== 0) {
            setLocation (getX(), getY() + speed);
        }

        getImage().clear();
        int size = (int)(24*Math.pow(1.02,tick-startTick));
        //System.out.println(tick+" - "+startTick);
        if (tick-startTick >= 100) {
            getWorld().removeObject(this);
        } else if (tick-startTick >= 90) {
            setImage("explosion/5.png");
        } else if (tick-startTick >= 80) {
            setImage("explosion/4.png");
        } else if (tick-startTick >= 70) {
            setImage("explosion/3.png");
            for (Block block: getIntersectingObjects(Block.class)) {
                block.hit("bomb");
            }
        } else if (tick-startTick >= 60) {
            setImage("explosion/2.png");
        } else if (tick-startTick >= 50) {
            setImage("explosion/1.png");
        } else {
            setImage("bomb.png");
        }
        getImage().scale(size, size);
    }
    public void setTick() {
        startTick = ((Board) getWorld()).tick;
        //System.out.println(startTick);
    }
}
