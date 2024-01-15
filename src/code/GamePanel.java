package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable{
    
    // Screen Settings
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double ScreenWidth = screenSize.getWidth();
    public static double ScreenHeight = screenSize.getHeight();

    public final int originalTilesSize = 32;
    public final int scale = 2;
    public final int widthTilesCount = 15;
    public final int heightTilesCount = 9;
    public final int tilesScale = originalTilesSize * scale;

    public int APP_WIDTH = originalTilesSize * scale * widthTilesCount;
    public int APP_HEIGHT = originalTilesSize * scale * heightTilesCount;

    Thread gameThread;
    KeyManager keyManager = new KeyManager(); // Call the KeyManager
    Player player = new Player(keyManager, this); // Make the player

    final int updatePerSecondCount = 32; // The "fps" of the game update
    final int tickSize = 1000 / updatePerSecondCount; // Time needed when the game update each second

    public Timer timer; // keep Timer reference for further use

    public GamePanel(){
        this.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        // allow the Gamepanel to read Keys
        this.addKeyListener(keyManager);
        // make the gamepanel on focus
        this.setFocusable(true);
    }

    // this start the thread on the cpu to make a coninuous RUN by calling run()
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // When we start the game thread, it automatically call this run method to make the game alove
    @Override
    public void run() {

        // as long as this game thread exist
        //it will reapeat the thing
        while (gameThread != null) {

            long currentTime = System.currentTimeMillis();
            long timeToWait = currentTime + tickSize;
            
            // while for waiting that the currentTime hit the or is bigger than the Time to wait
            // After this it will proceed to update the game
            while (currentTime<timeToWait) {
                currentTime = System.currentTimeMillis(); // Update the currentTime and keep track of it
            }

            //  Update the game
            update();
            //  Redraw the game -> making the visible fps basically
            // u need to call repaint() and NOT paintComponenet(), why?, CUZ FUCKING JAVA
            repaint();
            
             
        }
        
    }

    public void update(){
        
        player.updatePlayer();
        
    }

    // Already implemented class from library, don't ask how it works, it just works
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        // Clear everything
        g2.setBackground(Color.BLACK);

        player.drawPlayer(g2);

        
    }


}
