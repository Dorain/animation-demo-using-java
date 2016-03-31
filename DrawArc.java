import objectdraw.*;
import java.awt.Color;

/**
 * DrawArc draws a circle at a given location.
 * upon started on as a thread, e.g.
 * Thread arc = new DrawArc(<params>);
 * arc.start();
 * the circle moves diagonally down and back up the screen.
 */
public class DrawArc extends Thread {
    private int position;
    private DrawingCanvas canvas;
    private FilledArc[] fa = new FilledArc[4];
    private final int ARC_SIZE = 10;
    private double centerX = 250.0, centerY = 250.0;
    double radius = 120.0;
    /**
     * @param x - x starting location for the arc
     * @param y - y starting location for the arc
     * @param canvas - the canvas to draw the arc in. Should be
     * provided by objectdraw
     */
    public DrawArc(int num, DrawingCanvas canvas) {
        this.position = num;
        this.canvas = canvas;
        int startAngle = 0;
        int stopAngle = 360;
        double[] x = new double[4];
        double[] y = new double[4];
        for (int i = 0; i < 4; i++) {
            x[i] = centerX + radius * Math.cos(position * Math.PI / 24 + i * Math.PI / 2);
            y[i] = centerY + radius * Math.sin(position * Math.PI / 24 + i * Math.PI / 2);
            fa[i] = new FilledArc(x[i], y[i], ARC_SIZE, ARC_SIZE, startAngle, stopAngle, canvas);
            switch(position % 3) {
                case 0:
                    fa[i].setColor(Color.red);
                    break;
                case 1:
                    fa[i].setColor(Color.green);
                    break;
                default:
                    fa[i].setColor(Color.yellow);
                    break;
            }
        }
    }
    
    /**
     * Executed when the thread starts and runs indefinitly, moving the arc
     * across the screen.
     */
    public void run() {
        double theta =  position * Math.PI / 6;
        Animate.catchSleep(5000);
        while(true) {
            int count = 0;
            int interval = 372;
            double speed = 0.0;
            double[] distanceX = new double[4];
            double[] distanceY = new double[4];
            while(count < interval){
                if(count < 96 || count > interval - 96) {
                    count ++;
                    speed = 1.0 / (double)interval;
                }
                else {
                    count += 3;
                    speed = 3.0 / (double)interval;
                }
                for(int i = 0; i < 4; i++) {
                    distanceX[i] = - 2 * radius * Math.cos(position * Math.PI / 24 + i * Math.PI / 2);
                    distanceY[i] = - 2 * radius * Math.sin(position * Math.PI / 24 + i * Math.PI / 2);
                    fa[i].move(speed * distanceX[i], speed * distanceY[i]);
                }
                Animate.catchSleep(3);
            }
            count = 0;
            Animate.catchSleep(252 * 3 * 3); // secret of the animation :P
            
            while(count < interval){
                if(count < 96 || count > interval - 96) {
                    count ++;
                    speed = 1.0 / (double)interval;
                }
                else {
                    count += 3;
                    speed = 3.0 / (double)interval;
                }
                for(int i = 0; i < 4; i++) {
                    distanceX[i] = 2 * radius * Math.cos(position * Math.PI / 24 + i * Math.PI / 2);
                    distanceY[i] = 2 * radius * Math.sin(position * Math.PI / 24 + i * Math.PI / 2);
                    fa[i].move(speed * distanceX[i], speed * distanceY[i]);
                }
                Animate.catchSleep(3);
            }
            Animate.catchSleep(252 * 3 * 3);
        }
          /*
            while(radius > 20) {
                
                fa.moveTo(centerX + radius * Math.cos(theta), centerY + radius * Math.sin(theta));
                theta += 1.0/50.0;
                radius -= 0.1;
                //make this thread sleep for 10ms to smooth animation
                Animate.catchSleep(5);
                
            }
            while(radius < 150) {
                
                fa.moveTo(centerX + radius * Math.cos(theta), centerY + radius * Math.sin(theta));
                theta += 1.0/50.0;
                radius += 0.1;
                fa.moveTo(centerX + radius * Math.cos(theta), centerY + radius * Math.sin(theta));
                //make this thread sleep for 10ms to smooth animation
                Animate.catchSleep(5);
                
            }
          */
        
    }
}

