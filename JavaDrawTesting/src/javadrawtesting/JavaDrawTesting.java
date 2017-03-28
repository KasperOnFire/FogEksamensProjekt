package javadrawtesting;

import java.awt.Graphics;
import javax.swing. JFrame;

public class JavaDrawTesting extends JFrame {

    public JavaDrawTesting(){
        setTitle("Testing");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void paint(Graphics g){
        g.drawRect(480, 480, 200, 100);
        
    }
    
    public static void main(String[] args) {
        JavaDrawTesting t = new JavaDrawTesting();
        t.paint(null);


    }
    
}
