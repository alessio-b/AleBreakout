import greenfoot.*; 

/**
 * Rauch. Eine einfache Rauchwolke.
 * 
 * @author mik
 * @version 1.0
 */
public class Smoke  extends Actor
{
   private GreenfootImage image;   // das Originalbild
    private int fade;              // Geschwindigkeit des Auflösens

    public Smoke()
    {
        image = getImage();
        fade = Greenfoot.getRandomNumber(4) + 1;  // 1 bis 3
        if (fade > 3) {
          fade = fade - 2;
        }
    }
    
    /**
     * Wird bei jedem Schritt kleiner, bis es verschwindet.
     */
    public void act() 
    {
        shrink();
    }    
    
    private void shrink()
    {
        if(getImage().getWidth() < 10) {
            getWorld().removeObject(this);
        }
        else {
            GreenfootImage img = new GreenfootImage(image);
            img.scale ( getImage().getWidth()-fade, getImage().getHeight()-fade );
            setImage (img);
        }
    }
}
