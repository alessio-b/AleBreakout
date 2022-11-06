import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private int startTick;
    private GreenfootImage image = getImage();
    
    public Bomb() {
        image.scale(16,16);
        setImage(image);
    }
    
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        if (tick%6== 0) {
            setLocation (getX(), getY() + speed);
        }
        if ((tick-startTick)%4==0) {
            image.scale((int)(image.getWidth()*1.3), (int)(image.getHeight()*1.3));
        }
        
        //System.out.println(tick+" "+startTick);
        if (tick-startTick>=36) {
            for (Block block : getIntersectingObjects(Block.class)) {
                getWorld().removeObject(block);
            }
            getWorld().removeObject(this);
            
        }
    }
    public void setTick() {
        startTick = ((Board) getWorld()).tick;
        System.out.println(startTick);
    }
}