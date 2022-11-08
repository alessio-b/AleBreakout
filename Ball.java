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
        image.scale(20,20);
        setImage(image);
    }
    
    public void act() 
    {
        Board board = (Board) getWorld();
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
            if (board.tick%2==0) {
                getWorld().addObject ( new Smoke(), getX(), getY());
            }
            
            // Check Blocks
            Block block = (Block) getOneIntersectingObject(Block.class);
            if (block != null) {
                if (Math.abs(block.getX()-getX()) > Math.abs(block.getY()-getY())) {
                    //System.out.println("X Closer");
                    if (block.getX() > getX()) {
                        deltaX = board.ballSpeed*-1;
                    } else {
                        deltaX = board.ballSpeed;
                    }
                } else {
                    //System.out.println("X Closer");
                    if (block.getY() > getY()) {
                        deltaY = board.ballSpeed*-1;
                    } else {
                        deltaY = board.ballSpeed;
                    }
                }
                block.hit();
            }
            
            //Check Out
            if (getY()+1 == getWorld().getHeight()) {
                getWorld().removeObject(this);
                board.lose();
            }
        }
    }
    
    private void checkPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if (paddle != null) {
            deltaY = -1*((Board) getWorld()).ballSpeed;
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
