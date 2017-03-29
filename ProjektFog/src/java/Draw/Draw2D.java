package Draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Draw2D {

    public void drawRoof() {

    }

    public void drawSide() {

    }
     
    public BufferedImage drawTest() {
        int width = 200;
        int height = 200;
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_BYTE_INDEXED);

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
