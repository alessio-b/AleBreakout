import greenfoot.*;

public class Ball extends Actor
{   
    // Setup Movement Variables and Smoke Counter
    private double deltaX;
    private double deltaY;
    
    // 
    private boolean ballReleased = false;
    
    public Ball() {
        // Setup Ball and Scale
        GreenfootImage image = new  GreenfootImage("ball.png");
        image.scale(20,20);
        setImage(image);
    }
    
    public void act() 
    {
        Board board = (Board) getWorld();
        
        // Check if Ball is released
        if (ballReleased) 
        {
            // Move acording to Movement Variables
            setLocation (getX() + (int)deltaX, getY() + (int)deltaY);;
            
            // Check for Paddle / Bounce 
            if (isTouching(Paddle.class)) {
                Actor paddle = getOneIntersectingObject(Paddle.class);
                // Set Vertical Movement
                deltaY = -1*board.ballSpeed;
                // Set Horizontal Movement
                int offset = getX() - paddle.getX();
                deltaX = deltaX + (offset/6);
                // Limit Ball Speed
                while (Math.abs(deltaY+deltaX) > board.ballSpeed*2.5) {
                    deltaY = deltaY * 0.9;
                    deltaX = deltaX * 0.9;
                }
            }    
            
            // Check for Walls / Bounce
            if (getX() == 0 || getX() == getWorld().getWidth()-1) {
                deltaX = -deltaX;
            }
            if (getY() == 0) {
                deltaY = -deltaY;
            }
            
            // Check for BlockCollision
            if (isTouching(Block.class)) {
                Block block = (Block) getOneIntersectingObject(Block.class);
                // Check if Horizontal or Vertical Collision ( DistanceX > DistanceY )
                if (Math.abs(block.getX()-getX()) > Math.abs(block.getY()-getY())) {
                    // Horizontal: Check if Right or Left Collision
                    if (block.getX() > getX()) {
                        deltaX = board.ballSpeed*-1;
                    } else {
                        deltaX = board.ballSpeed;
                    }
                } else {
                    // Vertical: Check if Bottom or Top Collision
                    if (block.getY() > getY()) {
                        deltaY = board.ballSpeed*-1;
                    } else {
                        deltaY = board.ballSpeed;
                    }
                }
                // Hit Block
                block.hit();
            }
            
            //Check if Ball Out
            if (getY()+1 == getWorld().getHeight()) {
                getWorld().removeObject(this);
                board.lose("ball");
            }
        }
    }
    
    // Ball Released from Paddle, start Movemen
    public void release()
    {
        deltaX = Greenfoot.getRandomNumber(11) - 5;
        deltaY = -5;
        ballReleased = true;
    }
}
