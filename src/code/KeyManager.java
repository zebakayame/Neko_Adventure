package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    /*
     *  READ ME
     *  This is the Key Manage
     * It manage all the key pressed on keyboard, mouse or etc bla bla bla
     * Anyway, for that to function we listen once if the key is pressed and when the key is realeased
     * We simply call the realeast function to set the boolean Up there to false
     */

    @Override
    public void keyPressed(KeyEvent e) {
        
        int keyCode = e.getKeyCode(); // return key code of the key Pressed

        if(keyCode == KeyEvent.VK_W){
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(keyCode == KeyEvent.VK_SHIFT){
            shiftPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode(); // return key code of the key Pressed

        if(keyCode == KeyEvent.VK_W){
            upPressed = false;
        }
        if(keyCode == KeyEvent.VK_S){
            downPressed = false;
        }
        if(keyCode == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(keyCode == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(keyCode == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }
    }
    
}
