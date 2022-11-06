import greenfoot.*;

/**
 * Der Spielball. Er bewegt sich und prallt von den Wänden und dem Schläger ab.
 * 
 * @author mik
 * @version 1.0
 */
public class Ball extends Actor
{
    private Bomb bomb;
    
    private int deltaX;         // Geschwindigkeit der x-Bewegung
    private int deltaY;         // Geschwindigkeit der y-Bewegung
    private int count = 2;
    
    private boolean stuck = true;   //liegt auf dem Schläger
    
    /**
     * Aktion. Der Ball bewegt sich, wenn er nicht auf dem Schläger liegt.
     */
    public void act() 
    {
        if (!stuck) 
        {
            move();
            //makeSmoke();
            checkCollision();
            checkOut();
        }
    }
    
    /**
     * Bewegt den Ball. Prüft, was wir getroffen haben.
     */
    public void move()
    {
        setLocation (getX() + deltaX, getY() + deltaY);
        checkPaddle();
        checkWalls();
    }
    
    /**
     * Prüft, ob wir eine der drei Wände getroffen haben. Im Bedarfsfall Richtung umkehren.
     */
    private void checkWalls()
    {
        if (getX() == 0 || getX() == getWorld().getWidth()-1) {
            deltaX = -deltaX;
        }
        if (getY() == 0) {
            deltaY = -deltaY;
        }
    }
    
    /**
     * Prüft, ob der Ball ins Aus gegangen ist (Boden des Bildschirm).
     */
    private void checkOut()
    {
        if (getY()+1 == getWorld().getHeight()) {
            ((Board) getWorld()).ballIsOut();
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Prüft ob der Ball mit Block kollidiert.
     */
    private void checkCollision() {
        Block block = (Block) getOneIntersectingObject(Block.class);
        if (block != null) {
            deltaY = -deltaY;
            if (block.type == "bomb") {
                bomb = new Bomb();
                getWorld().addObject( bomb, block.getX(), block.getY());
                bomb.setTick();
            }
            getWorld().removeObject(block);
        }
    }
    
    private void checkPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if (paddle != null) {
            deltaY = -deltaY;
            int offset = getX() - paddle.getX();
            deltaX = deltaX + (offset/10);
            if (deltaX > 7) {
                deltaX = 7;
            }
            if (deltaX < -7) {
                deltaX = -7;
            }
        }            
    }
    
    /**
     * Bewegt den Ball um einen gegebenen Betrag zur Seite.
     */
    public void move(int dist)
    {
        setLocation (getX() + dist, getY());
    }

    /**
     * Gibt eine Rauchwolke aus (nur bei jedem zweiten Aufruf).
     */
    private void makeSmoke()
    {
        count--;
        if (count == 0) {
            getWorld().addObject ( new Smoke(), getX(), getY());
            count = 2;
        }
    }
    
    /**
     * Löst den Ball vom Schläger.
     */
    public void release()
    {
        deltaX = Greenfoot.getRandomNumber(11) - 5;
        deltaY = -5;
        stuck = false;
    }
}
