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

  /**
   * Constructs a GridGetter object
   *
   * @param imagePath       The path to the image file.
   * @param numberOfColumns Desired number of columns in the grid.
   * @param numberOfRows    Desired number of rows in the grid.
   */
  public GridGetter(String imagePath, int numberOfColumns, int numberOfRows) {
    setImagePath(imagePath);
    setNumberOfColumns(numberOfColumns);
    setNumberOfRows(numberOfRows);
  }

  /**
   * Loads the image from the specified path and resizes it to the specified
   * dimensions.
   *
   * @return The resized image.
   */
  public BufferedImage loadImage() {
    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      BufferedImage resizedImage = resizeImage(image, numberOfColumns, numberOfRows);
      return resizedImage;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Metod for resizing image.
   *
   * @return The resized image.
   */
  private BufferedImage resizeImage(BufferedImage originalImage, int imageWidth, int imageHeight) {
    BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
    graphics2D.dispose();
    return resizedImage;
  }

  /**
   * Gets the image grid 
   *
   * @returns 2D Array with rgb values of every pixel.
   */
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
   * Changes grid that coitains rgb to grid that contains 0 for transparent pixels (cells) 
   * and 1 for pixels (cells) that are not transparent
   *
   * @returns Grid with 1 and 0 cells.
   */
  public int[][] getBlackAndWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    int[][] blackAndWhiteGrid = new int[gridCellsTable.length][gridCellsTable[0].length];
    int imageHeight = gridCellsTable.length;
    int imageWidth = gridCellsTable[0].length;

    // In new array colored pixels equals 1 and white pixels equals 0
    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        blackAndWhiteGrid[rowIndex][columnIndex] = determineBlackOrWhiteCell(gridCellsTable[rowIndex][columnIndex]);
      }
    }
    printGrid(blackAndWhiteGrid);
    return blackAndWhiteGrid;
  }

    /**
   * Changes pixel (cell) value to 1 if it is higher than 0. (Pixel not compeleteley transparent)
   *
   * @returns Pixel (cell) value that is 1 or 0
   */
  private int determineBlackOrWhiteCell(int cell) {
    if (cell != 0) {
      cell = 1;
    }
    return cell;
  }

  /**
   * Changes grid that coitains rgb to grid that contains 0 (White) for transparent pixels (cells) 
   * 1 for pixels (cells) that are not red or closest to red
   * 2 for pixels (cells) that are green or closest to green
   * 3 for pixels (cells) that are blue or closest to blue, or equaly close to 2 of mentioned colors
   *
   * @returns Grid with 1, 2, 3 and 0 cells.
   */
  public int[][] getRedBlueGreenWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    int[][] redGreenBlueWhiteColorsGrid = new int[gridCellsTable.length][gridCellsTable[0].length];
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

  /**
   * Calculates distance of one color to other example color
   *
   * @returns Distance in rgb values of target colour and exempel colour 
   */
  public double distanceFromRedBlueOrGreen(Color pixelColor, Color exempelGBColor) {
    // The Euclidean distance between two points in three-dimensional space (RGB
    // color)
    // Formula:
    // sqrt((x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2)
    int distanceFromRed = pixelColor.getRed() - exempelGBColor.getRed();
    int distanceFromGreen = pixelColor.getGreen() - exempelGBColor.getGreen();
    int distanceFromBlue = pixelColor.getBlue() - exempelGBColor.getBlue();
    return Math.sqrt(distanceFromRed * distanceFromRed + distanceFromGreen * distanceFromGreen
        + distanceFromBlue * distanceFromBlue);
  }

   /**
   * Changes pixel rgb value to cell (pixel value) that is 1, 2, 3, 0
   * If the pixels rgb value is 0 it remains 0
   * It the pixels rgb value is closest to red color cell gets value 1
   * It the pixels rgb value is closest to green color cell gets value 2
   * It the pixels rgb value is closest to blue color cell gets value 3
   *
   * @returns Pixel (cell) value that is 1, 2, 3 or 0
   */
  private int determineRedGreenBlueWhiteCell(int cell, Color red, Color green, Color blue) {
    if (cell == 0) {
      cell = 0;
    } else {
      Color pixelColor = new Color(cell);

      double distanceFromRed = distanceFromRedBlueOrGreen(pixelColor, red);
      double distanceFromGreen = distanceFromRedBlueOrGreen(pixelColor, green);
      double distanceFromBlue = distanceFromRedBlueOrGreen(pixelColor, blue);

      if (distanceFromRed < distanceFromGreen && distanceFromRed < distanceFromGreen) {
        cell = 1; // Red
      } else if (distanceFromGreen < distanceFromBlue) {
        cell = 2; // Green
      } else {
        cell = 3; // Blue (Eaven if it is equal amount of 2 colors)
      }
    }
    return cell;
  }

  private void printGrid(int[][] grid) {
    for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < grid[0].length; columnIndex++) {
        System.out.print(grid[rowIndex][columnIndex] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Sets and validates the number of rows for the image grid.
   *
   * @param numberOfRows The number of rows to set.
   * @throws IllegalArgumentException if the number of rows is less than or equal to 0.
   */
  private void setNumberOfRows(int numberOfRows) {
    if (numberOfRows <= 0) {
      throw new IllegalArgumentException("Number of rows in image grid cannot be less than 1.");
    }
    this.numberOfRows = numberOfRows;
  }

   /**
   * Sets and validates the number of columns for the image grid.
   *
   * @param numberOfColumns The number of columns to set.
   * @throws IllegalArgumentException if the number of columns is less than or equal to 0.
   */
  private void setNumberOfColumns(int numberOfColumns) {
    if (numberOfColumns <= 0) {
      throw new IllegalArgumentException("Number of rows in image grid cannot be less than 1.");
    }
    this.numberOfColumns = numberOfColumns;
  }

  /**
   * Sets the image path for the grid.
   *
   * @param imagePath The path to the image file.
   */
  private void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
