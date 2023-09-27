package nonogram;

/**
 * Provides hints from an image grid for a specified grid cell.
 */
public class HintGetter {
  private String[][] imageGrid;

  public HintGetter(String[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  /**
   * Retrieves a hint from the image grid at the specified row and column indixes.
   * Throws exception if row index have index less than 0 or bitgger than number of rows in image Grid - 1.
   *  Throws exception if column index have index less than 0 or bitgger than number of columns - 1.
   */
  public String getHint(int cellsRowIndex, int cellsColumnIndex) {
    if (cellsRowIndex > imageGrid.length - 1 || cellsRowIndex < 0) {
      throw new IllegalArgumentException(
          "Row index cannot have index less than 0 or bitgger than " + (imageGrid.length - 1) + ".");
    }
    if (cellsColumnIndex > imageGrid[0].length - 1 || cellsColumnIndex < 0) {
      throw new IllegalArgumentException(
          "Column index cannot have index less than 0 or bitgger than " + (imageGrid[0].length - 1) + ".");
    }
    return imageGrid[setRowIndex(cellsRowIndex)][setColumnIndex(cellsColumnIndex)];
  }

  private void setImageGrid(String[][] imageGrid) {
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

  private int setRowIndex(int rowIndex) {
    if (rowIndex < 0) {
      throw new IllegalArgumentException("Row cannot have index less than 0.");
    }
    return rowIndex;
  }

  private int setColumnIndex(int columnIndex) {
    if (columnIndex < 0) {
      throw new IllegalArgumentException("Column cannot have index less than 0.");
    }
    return columnIndex;
  }
}
