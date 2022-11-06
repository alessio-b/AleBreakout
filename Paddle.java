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

    /**
     * Wenn der Schläger erzeugt wird, wird auch ein Ball erzeugt.
     */
    public void addedToWorld(World world)
    {
        newBall();
    }
    
    /**
     * Agiere - tue, was immer der Schläger so tun möchte. Diese Methode wird immer dann aufgerufen,
     * wenn in der Umgebung der Button 'Act' oder 'Run' gedrückt wird.
     */
    public void act() 
    {
        if (Greenfoot.isKeyDown ("left")) {
            move(-9);
        }
        if (Greenfoot.isKeyDown ("right")) {
            move(9);
        }
        if (haveBall() && Greenfoot.isKeyDown ("space")) {
            releaseBall();
        }
        
    }
    
    public void move(int dist)
    {
        setLocation (getX() + dist, getY());
        if (myBall != null) {
            myBall.move (dist);
        }
    }
    
    public void newBall()
    {
        myBall = new Ball();
        getWorld().addObject (myBall, getX(), getY()-20);
    }
        
    public boolean haveBall()
    {
        return myBall != null;
    }
        
    public void releaseBall()
    {
        myBall.release();
        myBall = null;
    }
        
}
