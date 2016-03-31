import objectdraw.*;
import java.lang.InterruptedException;
import java.lang.*;
import java.util.*;

/**
 * @author:
 * about: This program uses the objectdraw library to create an animation.
 *         <describe your animation>
 *
 * compile: javac -cp objectdraw.jar:. Animate.java
 * run: java -cp objectdraw.jar:. Animate
 */

public class Animate extends WindowController {
    final static int SIZE = 500; //size of the window for animation.
    final static int LINUX_MENU_BAR = 50; // account for menu bar on
    // linux systems
    
    /**
     * Program control automatically jumps to this method after executing
     * startController(). This is the method you'll want to use to draw
     * your animation or create threads.
     */
    public void begin() {
        int startSleep = 252;
        
        // The canvas variable is inherited. We'll talk about this later
        // in the class. For now, whenever the API asks for a canvas
        // as a parameter, use myCanvas
        DrawingCanvas myCanvas = canvas;
        
        //Example thread creation to draw a circle
        Thread[] t = new Thread[12];
        for(int i = 0; i < 12; i++) {
            t[i] = new DrawArc(i, canvas);
            catchSleep(startSleep); //make the primary thread sleep
            t[i].start();
        }
        
        
    }
    
    /**
     * Has the program pause for time miliseconds so animations can move
     * at a speed you'd like
     * @param time the time in miliseconds you want to sleep.
     */
    public static void catchSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {}
    }
    
    
    public static void main(String[] args) {
        // start the animation.
        new Animate().startController(SIZE,SIZE+LINUX_MENU_BAR);
    }
}
