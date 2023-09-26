package nonogram;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Creates image grid with marked colored and white cells for nonogram game.
 */
public class GridGetter {
  private int numberOfRows;
  private int numberOfColumns;
  private String imagePath;

  public GridGetter(String imagePath, int numberOfColumns, int numberOfRows) {
    setImagePath(imagePath);
    setNumberOfColumns(numberOfColumns);
    setNumberOfRows(numberOfRows);
  }

  // Loads the image from the specified path and resizes it to the specified size.
  private BufferedImage loadImage() {
    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      BufferedImage resizedImage = resizeImage(image, numberOfColumns, numberOfRows);
      return resizedImage;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  // Metod for resizing image to specified size - determined by pixels.
  private BufferedImage resizeImage(BufferedImage originalImage, int imageWidth, int imageHeight) {
    BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
    graphics2D.dispose();
    return resizedImage;
  }

  private int[][] getGrid() {
    BufferedImage resizedImg = loadImage();
    int imageWidth = resizedImg.getWidth();
    int imageHeight = resizedImg.getHeight();
    int[][] pixels = new int[imageHeight][imageWidth];

    // Get pixels
    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        pixels[rowIndex][columnIndex] = resizedImg.getRGB(columnIndex, rowIndex);
      }
    }
    return pixels;
  }

  /**
   * Returns a two-dimensional array representing a black and white grid.
   * Each element in the grid is a representation of a black or white cell.
   *
   * @return Two-dimensional array representing the black and white grid.
   */
  public String[][] getBlackAndWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    String[][] blackAndWhiteGrid = new String[gridCellsTable.length][gridCellsTable[0].length];
    int imageHeight = gridCellsTable.length;
    int imageWidth = gridCellsTable[0].length;

    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        blackAndWhiteGrid[rowIndex][columnIndex] = determineBlackOrWhiteCell(gridCellsTable[rowIndex][columnIndex]);
      }
    }
    return blackAndWhiteGrid;
  }

  // Changes pixels (cells) rgn value to black if its rgb value is higher than 0
  // (pixel not compeleteley transparent)
  // and to white if the rgb value is 0
  private String determineBlackOrWhiteCell(int cell) {
    String cellColor = "white";
    if (cell != 0) {
      cellColor = "black";
    }
    return cellColor;
  }

   /**
   * Returns a two-dimensional array representing a red, green, blue and white grid.
   * Each element in the grid is a representation of a red, green, blue  and white cell.
   *
   * @return Two-dimensional array representing the red, green, blue and white grid.
   */
  public String[][] getRedBlueGreenWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    String[][] redGreenBlueWhiteColorsGrid = new String[gridCellsTable.length][gridCellsTable[0].length];
    int imageHeight = gridCellsTable.length;
    int imageWidth = gridCellsTable[0].length;
    Color red = new Color(255, 0, 0);
    Color green = new Color(0, 255, 0);
    Color blue = new Color(0, 0, 255);

    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        redGreenBlueWhiteColorsGrid[rowIndex][columnIndex] = determineRedGreenBlueWhiteCell(
            gridCellsTable[rowIndex][columnIndex], red, green, blue);
      }
    }
    return redGreenBlueWhiteColorsGrid;
  }

  // The Euclidean distance between two points in three-dimensional space (RGB
  // color)
  // Formula: sqrt((x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2)
  private double distanceFromRedBlueOrGreen(Color pixelColor, Color exempelGBColor) {
    int distanceFromRed = pixelColor.getRed() - exempelGBColor.getRed();
    int distanceFromGreen = pixelColor.getGreen() - exempelGBColor.getGreen();
    int distanceFromBlue = pixelColor.getBlue() - exempelGBColor.getBlue();
    return Math.sqrt(distanceFromRed * distanceFromRed + distanceFromGreen * distanceFromGreen
        + distanceFromBlue * distanceFromBlue);
  }

  // Changes pixels rgb value to cells (pixels value) that is red, blue, green.
  // If the rgb value is niether of these colors, cells (pixels) color is set to
  // closest of these 3 colors.
  // If the pixels rgb value is 0 it gets white color.
  private String determineRedGreenBlueWhiteCell(int cell, Color red, Color green, Color blue) {
    String colorCell = "white";
    if (cell != 0) {
      Color pixelColor = new Color(cell);

      double distanceFromRed = distanceFromRedBlueOrGreen(pixelColor, red);
      double distanceFromGreen = distanceFromRedBlueOrGreen(pixelColor, green);
      double distanceFromBlue = distanceFromRedBlueOrGreen(pixelColor, blue);

      if (distanceFromRed < distanceFromGreen && distanceFromRed < distanceFromGreen) {
        colorCell = "red";
      } else if (distanceFromGreen < distanceFromBlue) {
        colorCell = "green";
      } else {
        colorCell = "blue"; // Eaven if it is equal amount of 2 colors
      }
    }
    return colorCell;
  }

  private void setNumberOfRows(int numberOfRows) {
    if (numberOfRows <= 0) {
      throw new IllegalArgumentException("Number of rows in image grid cannot be less than 1.");
    }
    this.numberOfRows = numberOfRows;
  }

  private void setNumberOfColumns(int numberOfColumns) {
    if (numberOfColumns <= 0) {
      throw new IllegalArgumentException("Number of columns in image grid cannot be less than 1.");
    }
    this.numberOfColumns = numberOfColumns;
  }

  private void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
