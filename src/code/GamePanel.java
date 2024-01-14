package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable{
    
    // Screen Settings
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double ScreenWidth = screenSize.getWidth();
    public static double ScreenHeight = screenSize.getHeight();

    final static int originalTilesSize = 32;
    final static int scale = 2;
    final static int widthTilesCount = 16;
    final static int heightTilesCount = 9;

    public static int APP_WIDTH = originalTilesSize * scale * widthTilesCount;
    public static int APP_HEIGHT = originalTilesSize * scale * heightTilesCount;

    Thread gameThread;
    KeyManager keyManager = new KeyManager();

    final int updatePerSecondCount = 24; // The "fps" of the game update
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
            System.out.println("currentTime: " + currentTime + " after 1 sec: " + timeToWait);
            
            // while for waiting that the currentTime hit the or is bigger than the Time to wait
            // After this it will proceed to update the game
            while (currentTime<timeToWait) {
                currentTime = System.currentTimeMillis(); // Update the currentTime and keep track of it
            }
            System.out.println("Oh yeah");

            //  Update the game
            update();
            //  Redraw the game -> making the visible fps basically
            // u need to call repaint() and NOT paintComponenet(), why?, CUZ FUCKING JAVA
            repaint();
            
             
        }
        
    }

    public void update(){
        

        /*if(keyManager.upPressed){
            System.out.println("Up is pressed");
        }else{
            System.out.println("Nope");
        }*/
    }

    // Already implemented class from library, don't ask how it works, it just works
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        // Graphics2D g2 = (Graphics2D)g;
        
        
    }


}
