import greenfoot.*;

/**
 * Der Spielball. Er bewegt sich und prallt von den Wänden und dem Schläger ab.
 * 
 * @author mik
 * @version 1.0
 */
public class Ball extends Actor
{   
    private int deltaX;         // Geschwindigkeit der x-Bewegung
    private int deltaY;         // Geschwindigkeit der y-Bewegung
    private int count = 2;
    
    private boolean stuck = true;   //liegt auf dem Schläger
    
    /**
     * Aktion. Der Ball bewegt sich, wenn er nicht auf dem Schläger liegt.
     */
    public Ball() {
        GreenfootImage image = new  GreenfootImage("ball.png");
        image.scale(16,16);
        setImage(image);
    }
    
    public void act() 
    {
        int tick = ((Board) getWorld()).tick;
        if (!stuck) 
        {
            setLocation (getX() + deltaX, getY() + deltaY);;
            checkPaddle();
            
            // Check Walls
            if (getX() == 0 || getX() == getWorld().getWidth()-1) {
                deltaX = -deltaX;
            }
            if (getY() == 0) {
                deltaY = -deltaY;
            }
            if (tick%2==0 && false) {
                getWorld().addObject ( new Smoke(), getX(), getY());
            }
            
            // Check Blocks
            Block block = (Block) getOneIntersectingObject(Block.class);
            if (block != null) {
                deltaY = -deltaY;
                block.hit();
            }
            
            //Check Out
            if (getY()+1 == getWorld().getHeight()) {
                (getWorld().getObjects(Paddle.class).get(0)).newBall();
                getWorld().removeObject(this);
            }
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
     * Löst den Ball vom Schläger.
     */
    public void release()
    {
        deltaX = Greenfoot.getRandomNumber(11) - 5;
        deltaY = -5;
        stuck = false;
    }
}
