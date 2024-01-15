package code;

/*
 * This class is made for deboguing stuff
 * We can simply enable debog -> It will print things on console
 */

public class Debog {
    
    private boolean debog = true;

    public void consoleDebog(String txt){
        if(debog){
            System.out.println(txt);
        }
    }    
}
