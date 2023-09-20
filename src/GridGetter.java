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
   * Changes grid that coitains rgb to grid that contains name white for transparent pixels (cells) 
   * and black for pixels (cells) that are not transparent
   *
   * @returns Grid with black and white cells.
   */
  public String [][] getBlackAndWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    String[][] blackAndWhiteGrid = new String [gridCellsTable.length][gridCellsTable[0].length];
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
   * Changes pixel (cell) value to black if it is higher than 0. (Pixel not compeleteley transparent)
   *
   * @returns Pixel (cell) value that is black or white
   */
  private String determineBlackOrWhiteCell(int cell) {
    String cellColor = "white";
    if (cell != 0) {
      cellColor = "black";
    } 
    return cellColor;
  }

  /**
   * Converts grid to red, blue and green colors with help of rgb value if the rgb is not 0
   * If the rgb value is niether of these colors, cells (pixels) color is set to closest of these 3 colors
   * If the rgb value is 0 (transparent pixel) the value of cell (pixel) will be set to white
   *
   * @returns Grid with colored cells.
   */
  public String [][] getRedBlueGreenWhiteGrid() {
    int[][] gridCellsTable = getGrid();
    String [][] redGreenBlueWhiteColorsGrid = new String[gridCellsTable.length][gridCellsTable[0].length];
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
    printGrid(redGreenBlueWhiteColorsGrid);
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
   * Changes pixel rgb value to cell (pixel value) that is red, blue, green
   * If the pixels rgb value is 0 it gets white color
   *
   * @returns Pixel (cell) color
   */
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
        colorCell = "blue"; //Eaven if it is equal amount of 2 colors
      }
    }
    return colorCell;
  }

  private void printGrid(String [][] grid) {
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
