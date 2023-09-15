import java.util.ArrayList;

/**
 * Counts cells in rows and columns of a 2D grid
 * containing values (0 and 1).
 */
public class BWCellCounts {
  int[][] imageGrid;

  /**
   * Constructs a BWCellCounts object with the given image grid.
   *
   * @param imageGrid The 2D array representing the image grid.
   */
  public BWCellCounts(int[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  /**
   * Gets the cell counts in rows of the image grid.
   *
   * @return A 2D ArrayList containing the cell counts in each row.
   */
  public ArrayList<ArrayList<Integer>> getRowCellCounts() {
    return getCellCounts(true);
  }

  /**
   * Gets the cell counts in columns of the image grid.
   *
   * @return A 2D ArrayList containing the cell counts in each column.
   */
  public ArrayList<ArrayList<Integer>> getColumnCellCount() {
    return getCellCounts(false);
  }

  /**
   * Calculates cell counts in rows or columns of the image grid.
   *
   * @param isRow If true, calculates cell counts in rows; if false, calculates
   *              cell counts in columns.
   * @return A 2D ArrayList containing the cell counts in row or column.
   */
  private ArrayList<ArrayList<Integer>> getCellCounts(boolean isRow) {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;
    ArrayList<ArrayList<Integer>> cellCountList = new ArrayList<>();
    int maxIterations;

    if (isRow) {
      maxIterations = numberOfRows;
    } else {
      maxIterations = numberOfColumns;
    }
    for (int i = 0; i < maxIterations; i++) {
      ArrayList<Integer> oneLineList = new ArrayList<>();
      int count = 0;
      boolean containsOnly0 = true;

      int innerMax;
      if (isRow) {
        innerMax = numberOfColumns;
      } else {
        innerMax = numberOfRows;
      }
      for (int j = 0; j < innerMax; j++) {
        int cellValue;
        if (isRow) {
          cellValue = imageGrid[i][j];
        } else {
          cellValue = imageGrid[j][i];
        }
        if (cellValue == 1) {
          count++;
          containsOnly0 = false;
        } else {
          if (count != 0) {
            oneLineList.add(count);
            count = 0;
          }
        }
      }

      if (count != 0) {
        oneLineList.add(count);
      }
      if (containsOnly0) {
        oneLineList.add(0);
      }

      cellCountList.add(oneLineList);
    }
    print2DArrayList(cellCountList);
    return cellCountList;
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

  public void print2DArrayList(ArrayList<ArrayList<Integer>> cellCounts2DList) {
    for (int i = 0; i < cellCounts2DList.size(); i++) {
      for (int z = 0; z < cellCounts2DList.get(i).size(); z++) {
        System.out.print(cellCounts2DList.get(i).get(z) + " ");
      }
      System.out.println();
    }
    System.out.println(cellCounts2DList.size());
  }
}