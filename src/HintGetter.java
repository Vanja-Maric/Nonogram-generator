/**
 * Provides hints from an image grid at specific grid cell.
 */
public class HintGetter {
  private int[][] imageGrid;

  /**
   * Constructs a HintGetter object with the given image grid.
   *
   * @param imageGrid The 2D array representing the image grid.
   * @throws IllegalArgumentException if the image grid is null or has invalid
   *                                  dimensions.
   */
  public HintGetter(int[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  /**
   * Retrieves a hint from the image grid at the specified row and column indixes.
   *
   * @param rowIndex    The index of the row in the image grid.
   * @param columnIndex The index of the column in the image grid.
   * @return The hint value at the specified row and column.
   */
  public int getHint(int rowIndex, int columnIndex) {
    // Validation???
    return imageGrid[setRowIndex(rowIndex)][setColumnIndex(columnIndex)];
  }

  /**
   * Validates and sets the image grid.
   *
   * @param imageGrid The 2D array representing the image grid.
   * @throws IllegalArgumentException throws if the image grid is null or has
   *                                  invalid dimensions.
   */
  private void setImageGrid(int[][] imageGrid) {
    if (imageGrid == null) {
      throw new IllegalArgumentException("Please add image grid. Image grid cannot be 0.");
    }

    int numberOfRows = imageGrid.length;
    if (numberOfRows <= 0) {
      throw new IllegalArgumentException("Number of rows in image grid cannot be less than 1.");
    }

    int numberOfColumns = imageGrid[0].length;
    if (numberOfColumns <= 0) {
      throw new IllegalArgumentException("Number of rows in image grid cannot be less than 1.");
    }

    this.imageGrid = imageGrid;
  }

  /**
   * Validates and sets the row index.
   *
   * @param rowIndex The row index to be validated.
   * @return The validated row index.
   * @throws IllegalArgumentException if the row index is less than 0.
   */
  private int setRowIndex(int rowIndex) {
    if (rowIndex < 0) {
      throw new IllegalArgumentException("Row cannot have index less than 0.");
    }
    return rowIndex;
  }

  /**
   * Validates and sets the column index.
   *
   * @param columnIndex The column index to be validated.
   * @return The validated column index.
   * @throws IllegalArgumentException if the column index is less than 0.
   */
  private int setColumnIndex(int columnIndex) {
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column cannot have index less than 0.");
    }
    return columnIndex;
  }
}
