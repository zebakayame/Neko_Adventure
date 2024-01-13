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
        
        

        window.setSize(GamePanel.APP_WIDTH, GamePanel.APP_HEIGHT);
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        
        window.setVisible(true);
        
    }
}
