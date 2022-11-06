import greenfoot.*; 

/**
 * Das Spielbrett. In dem Spiel gibt es einen Schläger, der bewegt werden kann.
 * 
 * @author mik
 * @version 1.0
 */
public class Board extends World
{
    private Paddle paddle;
    private Block block;
    public int blockCount;
    public int tick = 1;
    public int speed = 1; 
    
    /**
     * Konstruktor für Objekte der Klasse Board.
     * 
     */
    public Board()
    {    
        super(432, 768, 1);
        setPaintOrder ( Ball.class, Smoke.class );
        
        speed = 1;
        
        paddle = new Paddle();
        addObject ( paddle, getWidth() / 2, getHeight() - 40);
    }
    
    public void act() {
        tick++;
        if (tick%192==0) {
            genBlocks(16);   
        }
    }
    
    public void ballIsOut()
    {
        paddle.newBall();
    }
    
    public boolean genBlocks(int blocksWidth) {
        for (int i=0; i<getWidth()/(blocksWidth*1.5);i++) {
            blockCount++;
            block = new Block( blocksWidth, blocksWidth);
            int coordx = (int)(i*(blocksWidth*1.5)+(blocksWidth*0.75));
            addObject(block, coordx, -1);
        }
        return true;
    }
}
