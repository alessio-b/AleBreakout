import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block extends Actor
{
    // Setup BlockInfo
    public String type;
    private int health;
    
    public Block() {
        String img;
        // Randomly generate Block Type
        int rand = Greenfoot.getRandomNumber(100);
        if (rand <= 5) {
                img = "blockLaser.png";
                health = 1;
                this.type = "laser";
        } else if (rand <= 15) {
                img = "blockBomb.png";
                health = 1;
                this.type = "bomb";
        } else if (rand <= 35) {
                img = "blockDouble.png";
                health = 2;
                this.type = "double";
        } else {
                img = "blockNormal.png";
                health = 1;
                this.type = "normal";
        }
        setImage(img);
    }
    public void act()
    {
        Board board = (Board) getWorld();
        // Move Block   
        if (board.tick%6 == 0) {
            setLocation (getX(), getY() + board.blockSpeed);
        }
        // Check for Block out of Bounce
        if (getY() >= board.getHeight()-1) {
            System.out.println("Block Loss");
            board.lose();
        }
    }
    
    // 
    public void hit () {
        this.hit("");
    }
    public void hit(String DamageType) {
        Board board = (Board) getWorld();
        
        //set Damage and update BlockHealth
        int damage = board.damage;
        if (DamageType == "Bomb") {
            damage = board.damage * 3;
        }
        health-=damage;
        
        // Check BlockType
        if (type == "bomb") {
            // Create and set off Bomb / Add Score
            Bomb bomb = new Bomb();
            getWorld().addObject( bomb, getX(), getY());
            //bomb.setTick();
            board.addScore(1);
        } else if (type == "laser") { 
            // Create laser type Upgrade / Add Score
            Upgrade upgrade = new Upgrade("laser");
            getWorld().addObject( upgrade, getX(), getY());
            // Add Score
            board.addScore(1);
        } else if (type == "double") {
            // Set "broken" Image / Add Score
            setImage("blockDouble2.png");
            board.addScore(4);
        } else {
            // Normal Block add Score
            board.addScore(2);
        }
        
        // Delete if no Health
        if (health < 1) {
            getWorld().removeObject(this);
            board.addGameInfo("destroyed", type);
        }
    }
}
