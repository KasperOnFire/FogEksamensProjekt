package javadrawtesting;

import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class JavaDrawTesting extends JFrame {

    public JavaDrawTesting() {
        setTitle("Testing");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint() {

    }

    public static void main(String[] args) {
        //JavaDrawTesting t = new JavaDrawTesting();
        BufferedImage img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawRect(200, 200, 100, 200);
        g2d.dispose();        
        
        
        
    }

}
