package Draw;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Draw2D {

    Color c = new Color(255, 255, 255, 255);
    private boolean debug = false; 

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

        int depthCanvas = depth + 50;
        int heightCanvas = height + 50;
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
            g2d.drawRect((depthCanvas / 2 - (pillarDepth / 2)), 45, pillarDepth, height); //Middle pillar
        }
        g2d.drawRect(30, 30, depth, 15); //RoofHeight

        //Testing tools
        if (debug) {
            g2d.setColor(Color.BLACK);
            g2d.drawLine(60, heightCanvas - 20, depthCanvas - 50, heightCanvas - 20); //Width Line
            for (int i = 0; i < (depthCanvas - 100)/10; i++) {
                g2d.drawLine(60 + (i * 10), heightCanvas - 17, 60 + (i * 10), heightCanvas - 23); //End top
                if (i % 2 == 0) {
                    g2d.drawString("" + (i + 1), 55 + (i * 10), heightCanvas - 25);
                } else {
                    g2d.drawString("" + (i + 1), 55 + (i * 10), heightCanvas - 5);
                }
            }
        }
        //Length lines
        g2d.setColor(Color.BLACK);
        g2d.drawLine(20, 30, 20, heightCanvas - 5); //Height Line
        g2d.drawLine(17, 30, 23, 30); //End top
        g2d.drawLine(17, heightCanvas - 5, 23, heightCanvas - 5); //End top

        g2d.drawLine(30, 20, depthCanvas - 20, 20); //Width Line
        g2d.drawLine(depthCanvas - 20, 17, depthCanvas - 20, 23); //End bot
        g2d.drawLine(30, 17, 30, 23); //End top

        String widthStr = "Dybde: " + depth + " cm";
        String heightStr = "Højde: " + (height + 15) + " cm";

        //Text på lortet
        g2d.drawString(widthStr, (depthCanvas - metrics.stringWidth(widthStr)) / 2, 15);
        AffineTransform at = new AffineTransform();
        at.rotate(-Math.PI / 2);
        g2d.setTransform(at);
        g2d.drawString(heightStr, -(heightCanvas - metrics.stringWidth(heightStr) / 2), 15);

        //Make the drawing "final"
        g2d.dispose();

        return bImage;
    }

    public BufferedImage drawFront(int height, int width) {

        int widthCanvas = width + 50;
        int heightCanvas = height + 50;

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
        g2d.drawLine(20, 30, 20, heightCanvas - 5); //Height Line
        g2d.drawLine(17, 30, 23, 30); //End top
        g2d.drawLine(17, heightCanvas - 5, 23, heightCanvas - 5); //End top

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

    public BufferedImage drawDick(int height, int width) {

        int widthCanvas = width + 50;
        int heightCanvas = height + 50;

        BufferedImage bImage = new BufferedImage(widthCanvas, heightCanvas, BufferedImage.TYPE_BYTE_INDEXED);
        Graphics2D g2d = bImage.createGraphics();
        FontMetrics metrics = g2d.getFontMetrics();

        //Background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, widthCanvas, heightCanvas);

        //Dick Drawing
        g2d.setColor(Color.BLACK);
        g2d.fillRect(80, 80, 15, 15);
        g2d.fillRect(100, 80, 15, 15);
        g2d.fillRect(90, 50, 15, 40);
        g2d.setColor(Color.PINK);
        g2d.fillRect(97, 50, 2, 5);

        //Make the drawing "final"
        g2d.dispose();

        return bImage;

    }

    public BufferedImage drawTest() {
        int width = 200;
        int height = 200;
        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED);

        //background color
        Graphics2D g2d = bimage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        //other
        g2d.setColor(Color.red);
        g2d.fill(new Ellipse2D.Float(0, 0, 200, 100));
        g2d.dispose();

        //savePNG(bimage);
        return bimage;
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
