import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    /**
     * Act - do whatever the Block wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public String type;
    
    public Block(int sizex, int sizey) {
        GreenfootImage image = new  GreenfootImage(sizex, sizey);
        
        int rand = Greenfoot.getRandomNumber(100);
        if (rand <= 40) {
                image.setColor(Color.BLACK);
                type = "bomb";
        } else {
                image.setColor(Color.RED);
                type = "normal";
        }
        image.drawRect(0, 0, 100, 100);
        image.fill();
        setImage(image);
    }
    public void act()
    {
        int tick = ((Board) getWorld()).tick;
        int speed = ((Board) getWorld()).speed;
        
        if (tick%6 == 0) {
            setLocation (getX(), getY() + speed);
        }
        
        if (getY() >= 760) {
            getWorld().removeObjects(getWorld().getObjects(Block.class));
        }
    }
}
