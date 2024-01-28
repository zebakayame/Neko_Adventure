package code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

    public static int playerX = 0;
    public static int playerY = 0;

    public double veloX;
    public double veloY;
    
    // the key manager the player need to intereact
    KeyManager keyM;
    GamePanel gp;
    Debog deb = new Debog();
    Mapping mapper;

    public double initialPlayerSpeed = 10; 
    public double playerSpeed = initialPlayerSpeed;
    public double gravity = 5;
    public double maxGravity;


    public String imgStat = "down";

    // Get the current of the project
    private String playerTexturePath = System.getProperty("user.dir") + "/assets/Texture/player.png";

    // Image
    BufferedImage img;
    BufferedImage imgdown;
    BufferedImage imgup;
    BufferedImage imgleft;
    BufferedImage imgright;
    BufferedImage imgToDraw;

    public Player(KeyManager keyM, GamePanel gp, Mapping mapper){
        this.keyM = keyM; // settings up the keyManager so the player can access it
        this.gp = gp;
        this.mapper = mapper;
        this.maxGravity = gp.tilesScale;
    }

    public void updatePlayer(){
        positionUpdate();

        //System.out.println(playerTexturePath);
        //deb.consoleDebog("Player position: " + playerX + "\t" + playerY);
    }

    public void drawPlayer(Graphics2D g2){
        
        // Call the function to fix player Texture
        setPlayerTexture();

        // Movement CLASS
        // Depending on Stat of hte image (imgStat), show specific Image for movement
        

        // TODO => Implement alpha for Camera "player Move"
        int width_movement = gp.APP_WIDTH_HOOPER;
        int height_movement = gp.APP_HEIGHT_HOOPER;

        switch (imgStat) {
            case "up":
                imgToDraw = imgup;
                g2.drawImage(imgToDraw, width_movement, height_movement, gp.tilesScale, gp.tilesScale, gp);
                break;
            case "down":
                imgToDraw = imgdown;
                g2.drawImage(imgToDraw, width_movement, height_movement, gp.tilesScale, gp.tilesScale, gp);
                break;
            case "left":
                imgToDraw = imgleft;
                g2.drawImage(imgToDraw, width_movement, height_movement, gp.tilesScale, gp.tilesScale, gp);
                break;
            case "right":
                imgToDraw = imgright;
                g2.drawImage(imgToDraw, width_movement, height_movement, gp.tilesScale, gp.tilesScale, gp);
                break;
            default:
                g2.drawImage(imgdown, width_movement, height_movement, gp.tilesScale, gp.tilesScale, gp);
                break;
        }

    }

    // Call the function to fix player Texture
    private void setPlayerTexture() {
        try {
            img = ImageIO.read(new File(playerTexturePath));
            imgdown = img.getSubimage(0, 0 , 64, 64); // Gets the sub image of the player charachter
            imgup = img.getSubimage(64, 0, 64, 64);
            imgright = img.getSubimage(128, 0, 64, 64);
            imgleft = img.getSubimage(192, 0, 64, 64);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void positionUpdate() {
        // init the velocity to 0
        veloX = 0;

        // Make the velocity vector
        if(keyM.upPressed && veloY == 0){
            
            veloY = -50;
            imgStat = "up";
        }
        /*if(keyM.downPressed){
            veloY = 1;
            imgStat = "down";
        }*/
        if(keyM.rightPressed){
            veloX = 1;
            imgStat = "right";
        }
        if (keyM.leftPressed) {
            veloX = -1;
            imgStat = "left";
        }

        if(keyM.shiftPressed){
            playerSpeed = initialPlayerSpeed * 2.5;
        }else{
            playerSpeed = initialPlayerSpeed;
        }
        
        /*// Normalize the vector, this is for the up-right, up-left and etc. movement 
        if(veloX != 0 || veloY != 0){
            double length = Math.sqrt(veloX * veloX + veloY * veloY);
            veloX = (veloX / length) * playerSpeed;
            veloY = (veloY / length) * playerSpeed;
        }*/
        
        if(!collisionCheckY()){
            if(veloY <= maxGravity){
                veloY += gravity;
            }else{
                veloY = maxGravity;
            }
            
            playerY += veloY;
        }else{
            playerY = (int)(playerY / gp.tilesScale) * gp.tilesScale; 
            if(playerY + veloY < playerY){
                playerY+= veloY;
            }
            veloY = 0;
        }
        deb.consoleDebog("" + veloY);
        // Apply the velocity
        playerX += veloX * playerSpeed;
        
        // change map location
        mapper.changeAlpha(playerX, playerY);
    }

    private boolean collisionCheckY() {
        int playerXTile = playerX / gp.tilesScale;
        int playerYTile = playerY / gp.tilesScale;

        int tileType_Down = mapper.map[playerXTile][playerYTile + 1];

        boolean touch = false;
        
        if(mapper.checkIfCollisioner(tileType_Down)){
            touch = true;
        }
        return touch;

    }
}
