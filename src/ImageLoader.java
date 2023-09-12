import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
    public BufferedImage loadImage(String imagePath, int numberOfRows, int numberOfColumns) {
        try {
        BufferedImage image = ImageIO.read(new File(imagePath));
        BufferedImage resizedImage = resizeImage(image, numberOfRows, numberOfColumns);
        return resizedImage;
        } catch (IOException e) {
            // Hantera exception??????????
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage resizeImage (BufferedImage originalImage, int imageWidth, int imageHeight) {
        BufferedImage resizedImage =  new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
