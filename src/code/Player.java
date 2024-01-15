package code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

    public static int playerX = 100;
    public static int playerY = 100;

    public double veloX;
    public double veloY;

    public static int playerSpeed = 5;
    // the key manager the player need to intereact
    KeyManager keyM;
    GamePanel gp;

    public String imgStat = "down";

    public Player(KeyManager keyM, GamePanel gp){
        this.keyM = keyM; // settings up the keyManager so the player can access it
        this.gp = gp;
    }

    public void updatePlayer(){
        positionUpdate();
    }

    public void drawPlayer(Graphics2D g2){
        
        BufferedImage img = null;
        BufferedImage imgdown = null;
        BufferedImage imgup = null;
        BufferedImage imgleft = null;
        BufferedImage imgright = null;
        BufferedImage imgToDraw = null;
        try {
            img = ImageIO.read(new File("/home/kuku/Documents/VisualStudioWorkSpace/Project/Neko_Adventure/src/ressource/Texture/player.png"));
            imgdown = img.getSubimage(0, 0 , 32, 64); // Gets the sub image of the player charachter
            imgup = img.getSubimage(32, 0, 32, 64);
            imgleft = img.getSubimage(64, 0, 32, 64);
            imgright = img.getSubimage(64, 0, 32, 64);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        switch (imgStat) {
            case "up":
                imgToDraw = imgup;
                g2.drawImage(imgToDraw, playerX, playerY, gp.tilesScale, gp.tilesScale * 2, gp);
                break;
            case "down":
                imgToDraw = imgdown;
                g2.drawImage(imgToDraw, playerX, playerY, gp.tilesScale, gp.tilesScale * 2, gp);
                break;
            case "left":
                imgToDraw = imgleft;
                g2.drawImage(imgToDraw, playerX, playerY, gp.tilesScale, gp.tilesScale * 2, gp);
                break;
            case "right":
                imgToDraw = imgright;
                g2.drawImage(imgToDraw, playerX +  gp.tilesScale , playerY, -gp.tilesScale, gp.tilesScale * 2, gp);
                break;
            default:
                g2.drawImage(imgdown, playerX, playerY, gp.tilesScale, gp.tilesScale * 2, gp);
                break;
        }
        


    }

    private void positionUpdate() {
        // init the velocity to 0
        veloX = 0;
        veloY = 0;

        // Makle the velocity vector
        if(keyM.upPressed){
            veloY = -1;
            imgStat = "up";
        }
        if(keyM.downPressed){
            veloY = 1;
            imgStat = "down";
        }
        if(keyM.rightPressed){
            veloX = 1;
            imgStat = "right";
        }
        if (keyM.leftPressed) {
            veloX = -1;
            imgStat = "left";
        }
        
        // Normalize the vector, this is for the up-right, up-left and etc. movement 
        if(veloX != 0 || veloY != 0){
            double length = Math.sqrt(veloX * veloX + veloY * veloY);
            veloX = (veloX / length) * playerSpeed;
            veloY = (veloY / length) * playerSpeed;
        }

        // Apply the velocity
        playerX += veloX;
        playerY += veloY;

    }
}
