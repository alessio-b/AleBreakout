import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Der Spielschläger. Er wird über die Tastatur gesteuert (links, rechts, Leertaste). 
 * Er erzeugt außerdem einen neuen Ball, wenn er sich selbst erzeugt.
 * 
 * @author mik
 * @version 1.0
 */
public class Paddle extends Actor
{
    private Ball myBall;  // wird vor dem Ballanschlag verwendet
    
    private int BallCount;
    private int laserTimer;
    
    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setLocation(mouse.getX(), getY());
            if (myBall != null) {
                myBall.setLocation(mouse.getX(), myBall.getY());
            }
        }
        
        if (isTouching(Upgrade.class)) {
            Actor upgrade = getOneIntersectingObject(Upgrade.class);
            if( ((Upgrade) upgrade).upgradeType == "laser") {
                laserTimer += 900;
            }
            getWorld().removeObject(upgrade);
        }
   
        if (laserTimer > 1) {
            laserTimer--;
            if (laserTimer%60==0) {
                Laser laser = new Laser();
                getWorld().addObject( laser, getX()+20, getY() - 4);
                Laser laser2 = new Laser();
                getWorld().addObject( laser2, getX()-20, getY() - 4);
            }
        }
        
        if (myBall != null && Greenfoot.isKeyDown ("space")) {
            myBall.release();
            myBall = null;
        }
        
    }
    
    public void newBall()
    {
        BallCount++;
        myBall = new Ball();
        getWorld().addObject (myBall, getX(), getY()-20);
    }    
}
