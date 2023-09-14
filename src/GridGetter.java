import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GridGetter {
  private int numberOfRows;
  private int numberOfColumns;
  private String imagePath;

  public GridGetter(String imagePath, int numberOfColumns, int numberOfRows) {
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

  private int [][] getGrid () { 
    BufferedImage resizedImg = loadImage();
    int imageWidth = resizedImg.getWidth();
    int imageHeight = resizedImg.getHeight();
    int [][] pixels = new int[imageHeight][imageWidth];

    // Get pixels
    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex  < imageWidth; columnIndex ++) {
        pixels [rowIndex][columnIndex] = resizedImg.getRGB(columnIndex, rowIndex);
      }
    }
    return pixels;
  } 

  public int [][] getBlackAndWhiteGrid () {
    int [][] gridPixels = getGrid();
    int [][] blackAndWhiteGrid = new int[gridPixels.length][gridPixels[0].length];
    int imageHeight = gridPixels.length;
    int imageWidth = gridPixels[0].length;


    // In new array colored pixels equals 1 and white pixels equals 0
     for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        if (gridPixels[rowIndex][columnIndex] != 0) {
          blackAndWhiteGrid[rowIndex][columnIndex] = 1;
        } else {
          blackAndWhiteGrid[rowIndex][columnIndex] = 0;
        }
        System.out.print(blackAndWhiteGrid[rowIndex][columnIndex] + " ");
      }
      System.out.println(); 
    } 
    return blackAndWhiteGrid;
  }

  public int [][] getRedBlueGreenWhiteGrid () {
    int [][] gridPixels = getGrid();
    int [][] RGBgrid = new int[gridPixels.length][gridPixels[0].length];
    int imageHeight = gridPixels.length;
    int imageWidth = gridPixels[0].length;
    Color red = new Color(255, 0, 0);
    Color green  = new Color (0, 255, 0);
    Color blue = new Color(0, 0, 255);



    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        if (gridPixels[rowIndex][columnIndex] == 0) {
          RGBgrid[rowIndex][columnIndex] = 0;
        } else {
          Color pixelColor = new Color(gridPixels[rowIndex][columnIndex]);

          double distanceFromRed = distanceFromRedBlueOrGreen(pixelColor, red);
          double distanceFromGreen = distanceFromRedBlueOrGreen(pixelColor, green);
          double distanceFromBlue = distanceFromRedBlueOrGreen(pixelColor, blue);

          if (distanceFromRed  < distanceFromGreen && distanceFromRed < distanceFromGreen) {
            RGBgrid[rowIndex][columnIndex] = 1; // Red
          } else if (distanceFromGreen < distanceFromBlue) {
            RGBgrid[rowIndex][columnIndex] = 2; // Green
          } else {
            RGBgrid[rowIndex][columnIndex] = 3; // Blue (Eaven if it is equal amount of 2 colors)
          }
        }
        System.out.print(RGBgrid[rowIndex][columnIndex] + " ");
      }
      System.out.println();
    }
    return RGBgrid;
  }

  public double distanceFromRedBlueOrGreen (Color pixelColor, Color exempelGBColor) {
    // The Euclidean distance between two points in three-dimensional space (RGB color) 
    // Formula:
    // sqrt((x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2)

    int distanceFromRed = pixelColor.getRed() - exempelGBColor.getRed();
    int distanceFromGreen = pixelColor.getGreen() - exempelGBColor.getGreen();
    int distanceFromBlue = pixelColor.getBlue() - exempelGBColor.getBlue();
    return Math.sqrt(distanceFromRed * distanceFromRed + distanceFromGreen * distanceFromGreen + distanceFromBlue * distanceFromBlue);
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
