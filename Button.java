import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private String action;
    public GreenfootImage buttonImage;
    private Board board = (Board) getWorld();
    
    private int xPos;
    private int yPos;
    
    public Button(String buttonText, String actionPar) {
        action = actionPar;
        
        buttonImage = new GreenfootImage(buttonText, 24, new Color(255, 255, 255), new Color(0, 0, 0), new Color(128, 128, 128));
        setImage(buttonImage);
    }
    
    public void drawButton() {
        ((Board) getWorld()).getBackground().drawImage(buttonImage, xPos-buttonImage.getWidth()/2, yPos );
    }
    
    public void act()
    {
        Board board = (Board) getWorld();
        if (Greenfoot.mouseClicked(this)) {
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