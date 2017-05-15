package Draw;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Draw2D {

    Color c = new Color(255, 255, 255, 255);
    private final boolean debug = false;
    private final boolean fog = true;

    public BufferedImage drawRoof(int width, int depth) {

        int depthCanvas = depth + 50;
        int widthCanvas = width + 50;

        BufferedImage bImage = new BufferedImage(depthCanvas, widthCanvas, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = bImage.createGraphics();
        FontMetrics metrics = g2d.getFontMetrics();

        //Background
        g2d.fillRect(0, 0, depthCanvas, widthCanvas);

        //DrawingRoof
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(30, 30, depth, width);

        g2d.setColor(Color.BLACK);

        g2d.drawLine(20, 30, 20, widthCanvas - 20); //Height Line
        g2d.drawLine(17, 30, 23, 30); //End top
        g2d.drawLine(17, widthCanvas - 20, 23, widthCanvas - 20); //End bot

        g2d.drawLine(30, 20, depthCanvas - 20, 20); //Width Line
        g2d.drawLine(30, 17, 30, 23); //End top
        g2d.drawLine(depthCanvas - 20, 17, depthCanvas - 20, 23); //End bot

        String widthStr = "Dybde: " + depth + " cm";
        String heightStr = "Bredde: " + width + " cm";

        //Text på lortet
        g2d.drawString(widthStr, (depthCanvas - metrics.stringWidth(widthStr)) / 2, 15);
        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString(heightStr, -(widthCanvas - metrics.stringWidth(heightStr) / 2), 15);

        //Make the drawing "final"
        g2d.dispose();

        return bImage;
    }

    public BufferedImage drawSide(int height, int depth) {

        int depthCanvas = depth + 100;
        int heightCanvas = height + 100;
        int pillarDepth = 15; // (depth - (depthCanvas - 65))

        BufferedImage bImage = new BufferedImage(depthCanvas, heightCanvas, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = bImage.createGraphics();
        FontMetrics metrics = g2d.getFontMetrics();

        //Background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, depthCanvas, heightCanvas);

        //Side view rendering
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(45, 45, pillarDepth, height); //Pillar 1
        g2d.drawRect(depth, 45, pillarDepth, height); //Pillar 2
        if (depth > 400) {
            g2d.drawRect(((depthCanvas - 50) / 2 - (pillarDepth / 2)), 45, pillarDepth, height); //Middle pillar
        }
        g2d.drawRect(30, 30, depth, 15); //RoofHeight

        //Testing tools
        if (debug) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(60, heightCanvas - 20, depthCanvas - 100, heightCanvas - 20); //Width Line
            for (int i = 0; i < (depthCanvas - 150) / 10; i++) {
                g2d.drawLine(60 + (i * 10), heightCanvas - 17, 60 + (i * 10), heightCanvas - 23); //End top
                if (i % 2 == 0) {
                    g2d.drawString("" + (i + 1), 55 + (i * 10), heightCanvas - 25);
                } else {
                    g2d.drawString("" + (i + 1), 55 + (i * 10), heightCanvas - 55);
                }
            }
        }
        //Length lines
        g2d.setColor(Color.BLACK);
        g2d.drawLine(20, 30, 20, heightCanvas - 55); //Height Line
        g2d.drawLine(17, 30, 23, 30); //End top
        g2d.drawLine(17, heightCanvas - 55, 23, heightCanvas - 55); //End top

        g2d.drawLine(30, 20, depth + 30, 20); //Width Line
        g2d.drawLine(depth + 30, 17, depth + 30, 23); //End bot
        g2d.drawLine(30, 17, 30, 23); //End top

        if (fog) {
            //Linjer på stolpens brede
            g2d.drawLine(45, heightCanvas - 45, 60, heightCanvas - 45);
            g2d.drawLine(45, heightCanvas - 42, 45, heightCanvas - 48);
            g2d.drawLine(60, heightCanvas - 42, 60, heightCanvas - 48); 
            
            
            g2d.drawLine(65, 46, 65, heightCanvas - 55);
            g2d.drawLine(62, 46, 68, 46);
            g2d.drawLine(62, heightCanvas - 55, 68, heightCanvas - 55);
            g2d.drawLine(depth + 35, 30, depth + 41, 30);
            g2d.drawLine(depth + 35, 45, depth + 41, 45);
            g2d.drawLine(depth + 38, 30, depth + 38, 45);
            if (depth > 400) {
                g2d.drawLine(((depthCanvas - 32) / 2), 55, depth - 1, 55);
                g2d.drawLine(depth - 1, 52, depth - 1, 58);
                g2d.drawLine(((depthCanvas - 32) / 2), 52, ((depthCanvas - 32) / 2), 58);
            }
            //g2d.drawLine(, depth, depth, depth);
        }

        String widthStr = "Dybde: " + depth + " cm";
        String heightStrRoof = "Højde: " + (height + 15) + " cm";
        String heightStr = "Højde: " + (height) + " cm";
        String heightRoof = "Højde: 15 cm";
        String depthPost = "Dybde: 15 cm";
        String depthInside = "Dybde: " + ((depth - 75) / 2) + " cm";
        //Text på lortet
        g2d.drawString(widthStr, (depthCanvas - metrics.stringWidth(widthStr)) / 2, 15);
        if (fog) {
            g2d.drawString(depthInside, depthCanvas - (depth / 2), 75);
            g2d.drawString(depthPost, 30, heightCanvas - 30);
        }
        
        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString(heightStrRoof, -(heightCanvas - metrics.stringWidth(heightStrRoof) / 2) + 50, 15);
        if (fog) {
            g2d.drawString(heightStr, -(heightCanvas - metrics.stringWidth(heightStr) / 2) + 50, 80);
            g2d.drawString(heightRoof, -(80), depth + 55);
        }

        //Make the drawing "final"
        g2d.dispose();

        return bImage;
    }

    public BufferedImage drawFront(int height, int width) {

        int widthCanvas = width + 50;
        int heightCanvas = height + 100;

        BufferedImage bImage = new BufferedImage(widthCanvas, heightCanvas, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = bImage.createGraphics();
        FontMetrics metrics = g2d.getFontMetrics();

        //Background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, widthCanvas, heightCanvas);

        //Side view rendering
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(45, 45, width - (widthCanvas - 65), height); //Pillar 1
        g2d.drawRect(width, 45, width - (widthCanvas - 65), height); //Pillar 2
        g2d.drawRect(30, 30, width, 15); //RoofHeight

        //Length lines
        g2d.setColor(Color.BLACK);
        g2d.drawLine(20, 30, 20, heightCanvas - 55); //Height Line
        g2d.drawLine(17, 30, 23, 30); //End top
        g2d.drawLine(17, heightCanvas - 55, 23, heightCanvas - 55); //End top

        g2d.drawLine(30, 20, widthCanvas - 20, 20); //Width Line
        g2d.drawLine(30, 17, 30, 23); //End top
        g2d.drawLine(widthCanvas - 20, 17, widthCanvas - 20, 23); //End bot

        String widthStr = "Bredde: " + width + " cm";
        String heightStr = "Højde: " + (height + 15) + " cm";

        //Text på lortet
        g2d.drawString(widthStr, (widthCanvas - metrics.stringWidth(widthStr)) / 2, 15);
        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString(heightStr, -(heightCanvas - metrics.stringWidth(heightStr) / 2), 15);

        //Make the drawing "final"
        g2d.dispose();

        return bImage;
    }

    public void savePNG(final BufferedImage bi) {
        try {
            RenderedImage rendImage = bi;
            File output = new File("C:/test.bmp");
            ImageIO.write(rendImage, "bmp", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void sendPNG(final BufferedImage bi){
        try {
            RenderedImage rendImage = bi;
            ImageIO.write(rendImage, "bmp", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
