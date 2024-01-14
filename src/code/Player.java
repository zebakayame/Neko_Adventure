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

    public Player(KeyManager keyM, GamePanel gp){
        this.keyM = keyM; // settings up the keyManager so the player can access it
        this.gp = gp;
    }

    public void updatePlayer(){
        positionUpdate();
    }

    public void drawPlayer(Graphics2D g2){
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/ressource/Texture/player.png"));
            img = img.getSubimage(0, 0 , 32, 64); // Gets the sub image of the player charachter
        } catch (IOException e) {
            System.out.println(e);
        }

        g2.drawImage(img, playerX, playerY, gp.tilesScale, gp.tilesScale * 2, gp);

    }

    private void positionUpdate() {
        // init the velocity to 0
        veloX = 0;
        veloY = 0;

        // Makle the velocity vector
        if(keyM.upPressed){
            veloY = -1;
        }
        if(keyM.downPressed){
            veloY = 1;
        }
        if(keyM.rightPressed){
            veloX = 1;
        }
        if (keyM.leftPressed) {
            veloX = -1;
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
