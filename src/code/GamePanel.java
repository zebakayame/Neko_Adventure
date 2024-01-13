package code;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel{
    
    // Screen Settings
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double ScreenWidth = screenSize.getWidth();
    public static double ScreenHeight = screenSize.getHeight();

    final int originalTilesSize = 32;
    final int scale = 8;

    public static int APP_WIDTH = (int) (ScreenWidth / 2);
    public static int APP_HEIGHT = (int) (ScreenHeight / 2);


}
