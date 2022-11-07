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
    private GreenfootImage image;
    
    public Bomb() {
        setImage("bomb.png");
        image = getImage();
    }
    
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        if (tick%7== 0) {
            setLocation (getX(), getY() + speed);
        }
        if ((tick-startTick)%4==0) {
            image.scale((int)(image.getWidth()*1.1), (int)(image.getHeight()*1.1));
        }
        
        //System.out.println(tick+" - "+startTick);
        if (tick-startTick>=48) {
            for (Block block: getIntersectingObjects(Block.class)) {
                block.hit("bomb");
            }
            getWorld().removeObject(this);
        }
    }
    public void setTick() {
        startTick = ((Board) getWorld()).tick;
        //System.out.println(startTick);
    }
}
