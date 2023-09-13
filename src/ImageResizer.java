import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageResizer {
  private int numberOfRows;
  private int numberOfColumns;
  private String imagePath;

  public ImageResizer(String imagePath, int numberOfColumns, int numberOfRows) {
    setImagePath(imagePath);
    setNumberOfColumns(numberOfColumns);
    setNumberOfRows(numberOfRows);
  }

  public BufferedImage loadImage() {
    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      BufferedImage resizedImage = resizeImage(image, numberOfColumns, numberOfRows);
      return resizedImage;
    } catch (IOException e) {
      // Handle exception??????????
      e.printStackTrace();
      return null;
    }
  }

  private BufferedImage resizeImage(BufferedImage originalImage, int imageWidth, int imageHeight) {
    BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
    graphics2D.dispose();
    return resizedImage;
  }

  // ADD VALIDATION`????
  private void setNumberOfRows(int numberOfRows) {
    // Validera????
    this.numberOfRows = numberOfRows;
  }

  private void setNumberOfColumns(int numberOfColumns) {
    // Validera????
    this.numberOfColumns = numberOfColumns;
  }

  private void setImagePath(String imagePath) {
    // Validera????
    this.imagePath = imagePath;
  }
}
