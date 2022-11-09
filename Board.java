import greenfoot.*; 
import java.util.HashMap;

public class Board extends World
{
    // Setup Dynamic Game Variables
    public int tick = 0;
    public int ballSpeed = 5; 
    public int blockSpeed = 1;
    public int damage = 1;
    
    // Setup Gameplay & Gameinfo Variables
    private int lives = 3;
    private int score = 0;
    private String playState;
    private HashMap<String, Integer> destroyedBlocks = new HashMap<String, Integer>();
    
    // Setup MidPoints for Text
    public int MidHeight = getHeight()/2;
    public int MidWidth = getWidth()/2;

    public Board()
    {   
        // Iinitial Setup
        super(540, 960, 1);
        setPaintOrder ( Bomb.class, Ball.class, Trail.class );

        // Set Welcome Screen
        playState = "Title";
        String titleText =    "Welcome to AleBreakout\n\nRed = Normal\nGreen = Double\nBlack = Bomb\nBlue = Laser";
        showText(titleText, MidWidth, MidHeight);
        
        Button startButton = new Button("Start", "start");
        addObject(startButton, MidWidth, MidHeight+250);
    }
    
    public void restartGame() {
        // Reset Gameplay Variables
        score = 0;
        destroyedBlocks = new HashMap<String, Integer>();
        lives = 3;
        startGame();
    }
    
    public void startGame(){
        // Remove Title/End Screen
        showText("", MidWidth, MidHeight);
        removeObjects(getObjects(Button.class));
        
        // Initial Lives Display
        for (int i = 0; i<lives;i++) {
             GreenfootImage heart = new GreenfootImage("heart.png");
             getBackground().drawImage(heart, i*26, getHeight()-heart.getHeight());
        }
        
        // Create Paddle & Ball
        Paddle paddle = new Paddle();
        addObject ( paddle, getWidth() / 2, getHeight() - 40);
        paddle.newBall();
        
        // Set Game to active
        playState = "Playing";
    }

    public void act() {
        // If Game is Active
        if (playState == "Playing") {
            // Increase Tick Variable (60 ticks/s)
            tick++;
            if (tick%180==0) {
                // Add Block Row
                for (int i=0; i<getWidth()/(24*1.5);i++) {
                    Block block = new Block();
                    addObject(block, (int)(i*(24*1.5)+(24*0.75)), -1);
                }  
            }
        }
    }

    public void addScore(int amount) {
        // Add Score
        score += amount;
    } 
    
    public void addGameInfo(String infoType, String value) {
        // Collect GameInfo
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
        // Decrease Lives
        lives--;
        
        // Reset Background
        GreenfootImage bg = new GreenfootImage(540, 960);
        bg.setColor(Color.WHITE);
        bg.fill();
        setBackground(bg);
        
        // Initial Lives Display
        for (int i = 0; i<lives;i++) {
             GreenfootImage heart = new GreenfootImage("heart.png");
             getBackground().drawImage(heart, i*26, getHeight()-heart.getHeight());
        }
        
        if (lives < 1) {
            // Deactivate Game & Remove all Objects
            playState = "End";
            removeObjects(getObjects(null));
            
            // Get & Set Highscore
            UserInfo userInfo = UserInfo.getMyInfo();
            if (score > userInfo.getScore()) {
                userInfo.setScore(score);
            }
    
            // Set End Screen
            String endText =    "Highcore: " + Integer.toString(userInfo.getScore())+ "\n" +
                                "Score: " + Integer.toString(score)+ "\n" +
                                "\n" + 
                                "Blocks Destroyed"+ "\n" +
                                "Normal: " + (destroyedBlocks.containsKey("normal") ? destroyedBlocks.get("normal") : 0) + "\n" +
                                "Double: " + (destroyedBlocks.containsKey("double") ? destroyedBlocks.get("double") : 0) + "\n" +
                                "Bombs: " + (destroyedBlocks.containsKey("bomb") ? destroyedBlocks.get("bomb") : 0) + "\n" +
                                "Laser: " + (destroyedBlocks.containsKey("laser") ? destroyedBlocks.get("laser") : 0) + "\n";
            showText(endText, MidWidth, MidHeight);
            
            Button startButton = new Button("Restart", "restart");
            addObject(startButton, MidWidth, MidHeight+250);
        } else {
            // Create new Ball
            (getObjects(Paddle.class).get(0)).newBall();
        }
    }
}
