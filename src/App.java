import javax.swing.JFrame;

import code.GamePanel;

public class App {
    public static void main(String[] args) throws Exception {
        initWindow();
    }

    /**
     * initWindow()
     */
    public static void initWindow() {
        
        JFrame window = new JFrame("Neko Adventure");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        // Pack everything that was like window.add into a nice little chunk
        // Since there is not other than gamepanel, gampanel will take the whole Space
        // IT IS GAMEPANEL THAT MANAGE THE SIZE COLOR AND SO ON ON THE SCREEN
        window.pack(); 

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        // Init the game Loop and time of the game
        gamePanel.startGameThread();        

    }
}
