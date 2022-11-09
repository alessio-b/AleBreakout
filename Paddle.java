import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Paddle extends Actor
{
    // Setup
    private Ball myBall;
    private int laserTimer;
    
    public void act() 
    {
        // Get Mouse Location and Move
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            setLocation(mouse.getX(), getY());
            // Move Ball (if not released)
            if (myBall != null) {
                myBall.setLocation(mouse.getX(), myBall.getY());
            }
        }
        
        // Check for Upgrade 
        if (isTouching(Upgrade.class)) {
            Upgrade upgrade = (Upgrade) getOneIntersectingObject(Upgrade.class);
            // if UpgradeType Laser add to LaserTimer (15s)
            if(upgrade.upgradeType == "laser") {
                laserTimer += 900;
            }
            getWorld().removeObject(upgrade);
        }
   
        // If Laser still active
        if (laserTimer > 1) {
            laserTimer--;
            // Shoot 2 Lasers (every 1s)
            if (laserTimer%60==0) {
                Laser laser = new Laser();
                getWorld().addObject( laser, getX()+20, getY() - 4);
                Laser laser2 = new Laser();
                getWorld().addObject( laser2, getX()-20, getY() - 4);
            }
        }
        
        // Check for Ball release
        if (myBall != null && Greenfoot.isKeyDown ("space")) {
            myBall.release();
            myBall = null;
        }
        
    }
    
    // Create New Ball
    public void newBall()
    {
        myBall = new Ball();
        getWorld().addObject (myBall, getX(), getY()-20);
    }    
}
