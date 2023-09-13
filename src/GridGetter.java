import java.awt.image.BufferedImage;

public class GridGetter {
  public void analyzePicture (BufferedImage image) {
    int imageWidth = image.getWidth();
    int imageHeight = image.getHeight();
    int [][] pixels = new int[imageHeight][imageWidth];

    // Get pixels
    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex  < imageWidth; columnIndex ++) {
        pixels [rowIndex][columnIndex] = image.getRGB(columnIndex, rowIndex);
      }
    }
     for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        if (pixels[rowIndex][columnIndex] != 0) {
          pixels[rowIndex][columnIndex] = 1;
        }
        System.out.print(pixels[rowIndex][columnIndex] + " ");
      }
      System.out.println(); 
    }
  } 
}
