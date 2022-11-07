import greenfoot.*; 

/**
 * Das Spielbrett. In dem Spiel gibt es einen Schläger, der bewegt werden kann.
 * 
 * @author mik
 * @version 1.0
 */
public class Board extends World
{
    public int score = 0;
    public int tick = 1;
    public int speed = 1; 
    public int damage = 1;

    /**
     * Konstruktor für Objekte der Klasse Board.
     * 
     */
    public Board()
    {    
        super(432, 768, 1);
        setPaintOrder ( Ball.class, Smoke.class );

        speed = 1;

        Paddle paddle = new Paddle();
        addObject ( paddle, getWidth() / 2, getHeight() - 40);
        paddle.newBall();
        System.out.println("Debug");
    }

    public void act() {
        tick++;
        if (tick%192==0) {
            genBlocks();   
        }
    }

    public boolean genBlocks() {
        for (int i=0; i<getWidth()/(16*1.5);i++) {
            Block block = new Block();
            int coordx = (int)(i*(16*1.5)+(16*0.75));
            addObject(block, coordx, -1);
        }
        return true;
    }

    public void addScore(int amount) {
        score += amount;
        //System.out.println(score);
    } 
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
