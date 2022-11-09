import greenfoot.*; 

public class Trail extends Actor
{
    // Setup 
    private GreenfootImage image = getImage();
    private int fade;

    public Trail()
    {
        // Randomly set fading Variable
        fade = Greenfoot.getRandomNumber(4) + 1;
    }
    
    public void act() 
    {
        // Shrink Image or remove Trail
        if(getImage().getWidth() > 10) {
            GreenfootImage img = new GreenfootImage(image);
            img.scale ( getImage().getWidth()-fade, getImage().getHeight()-fade );
            setImage (img);
            
        } else {
            getWorld().removeObject(this);
        }
    }    
}
