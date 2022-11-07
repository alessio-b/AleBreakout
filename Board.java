import greenfoot.*; 
import java.util.HashMap;

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
    
    private boolean playing = true;
    private HashMap<String, Integer> destroyedBlocks = new HashMap<String, Integer>();;

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
        if (playing) {
            tick++;
            if (tick%192==0) {
                genBlocks();   
            }
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
    } 
    
    public void addGameInfo(String infoType, String value) {
        switch(infoType){
            case "destroyed":
                if (destroyedBlocks.containsKey(value)) {
                    destroyedBlocks.put(value, destroyedBlocks.get(value)+1);
                } else {
                    destroyedBlocks.put(value, 1);
                }
        }
    }

    public void lose() {
        playing = false;
        removeObjects(getObjects(null));
        
        UserInfo userInfo = UserInfo.getMyInfo();
        if (score > userInfo.getScore()) {
            userInfo.setScore(score);
        }
        
        int MidHeight = getHeight()/2;
        int MidWidth = getWidth()/2;
        String endText =    "Highcore: " + Integer.toString(userInfo.getScore())+ "\n" +
                            "Score: " + Integer.toString(score)+ "\n" +
                            "\n" + 
                            "Blocks Destroyed"+ "\n" +
                            "Normal: " + (destroyedBlocks.containsKey("normal") ? destroyedBlocks.get("normal") : 0) + "\n" +
                            "Double: " + (destroyedBlocks.containsKey("double") ? destroyedBlocks.get("double") : 0) + "\n" +
                            "Bombs: " + (destroyedBlocks.containsKey("bomb") ? destroyedBlocks.get("bomb") : 0) + "\n" +
                            "Laser: " + (destroyedBlocks.containsKey("laser") ? destroyedBlocks.get("laser") : 0) + "\n"
                            ;
        getBackground().drawImage(new GreenfootImage(endText, 24, null, null), MidWidth, MidHeight-16);
    }
}
