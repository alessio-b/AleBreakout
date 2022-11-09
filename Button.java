import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    // Setup action Variable
    private String action;
    
    public Button(String buttonText, String actionPar) {
        // Set action Variable
        action = actionPar;
        
        // Create Image based on Text
        GreenfootImage buttonImage = new GreenfootImage(buttonText, 24, new Color(255, 255, 255), new Color(0, 0, 0), new Color(128, 128, 128));
        setImage(buttonImage);
    }
    
    public void act()
    {
        Board board = (Board) getWorld();
        // Check if mouse clicked Button
        if (Greenfoot.mouseClicked(this)) {
            // Call Method based on action Variable
            switch(action){
                case "start":
                    board.startGame();
                    break;
                case "restart":
                    board.restartGame();
                    break;
                }
        }
    }
}