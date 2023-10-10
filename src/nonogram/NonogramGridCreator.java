package nonogram;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Creates an nonogram grid with marked colored and white cells for nonogram
 * game from loaded image.
 */
public class NonogramGridCreator {
  private int numberOfRows;
  private int numberOfColumns;
  private String imagePath;

  public NonogramGridCreator(String imagePath, int numberOfColumns, int numberOfRows) {
    setImagePath(imagePath);
    setNumberOfColumns(numberOfColumns);
    setNumberOfRows(numberOfRows);
  }

  /**
   * Returns a 2D String array representing the black and white nonogram grid
   * created from the RGB values of all pixels in the image.
   * Each element in the array represents a cell in the grid and is either black
   * or white.
   * Black cells represent pixels in the image that are not completely
   * transparent.
   * White cells represent pixels in the image that are completely transparent.
   * 
   * @return a 2D String array representing the black and white nonogram grid
   */
  public String[][] getBlackAndWhiteGrid() {
    int[][] rgbValuesOfAllPixelsGrid = getRgbValuesOfAllPixels();
    String[][] blackAndWhiteNonogramGrid = new String[rgbValuesOfAllPixelsGrid.length][rgbValuesOfAllPixelsGrid[0].length];
    int imageHeight = rgbValuesOfAllPixelsGrid.length;
    int imageWidth = rgbValuesOfAllPixelsGrid[0].length;

    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        blackAndWhiteNonogramGrid[rowIndex][columnIndex] = determineBlackOrWhiteCell(
            rgbValuesOfAllPixelsGrid[rowIndex][columnIndex]);
      }
    }
    return blackAndWhiteNonogramGrid;
  }

  // Returns a 2D array of RGB values for each pixel in the loaded and resized
  // image.
  // The first dimension represents the row index and the second dimension
  // represents the column index.
  private int[][] getRgbValuesOfAllPixels() {
    BufferedImage resizedImg = loadAndResizeImage();
    int imageWidth = resizedImg.getWidth();
    int imageHeight = resizedImg.getHeight();
    int[][] RgbValuesOfAllPixelsGrid = new int[imageHeight][imageWidth];

    for (int rowIndex = 0; rowIndex < imageHeight; rowIndex++) {
      for (int columnIndex = 0; columnIndex < imageWidth; columnIndex++) {
        RgbValuesOfAllPixelsGrid[rowIndex][columnIndex] = resizedImg.getRGB(columnIndex, rowIndex);
      }
    }
    return RgbValuesOfAllPixelsGrid;
  }

  // Loads the image from the specified path and resizes it to the specified size.
  private BufferedImage loadAndResizeImage() {
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

  // Changes pixels (cells) rgn value to black if its rgb value is higher than 0
  // (pixel not compeleteley transparent) and to white if the rgb value is 0
  private String determineBlackOrWhiteCell(int cellsRgbValue) {
    String cellColor = "white";
    if (cellsRgbValue != 0) {
      cellColor = "black";
    }
    return cellColor;
  }

  // Validation methods
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
    // Check if the file extension is ".png"
    if (!imagePath.toLowerCase().endsWith(".png")) {
      throw new IllegalArgumentException("The image file must have a '.png' extension.");
    }
    this.imagePath = imagePath;
  }
}
