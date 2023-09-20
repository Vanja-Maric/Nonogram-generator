import java.util.ArrayList;

/**
 * Counts cells in rows and columns of a 2D grid
 * containing values (0 and 1).
 */
public class BWCellCounts {
  String[][] imageGrid;

  /**
   * Constructs a BWCellCounts object with the given image grid.
   *
   * @param imageGrid The 2D array representing the image grid.
   */
  public BWCellCounts(String[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  /**
   * Gets the cell counts in rows of the image grid.
   *
   * @return A 2D ArrayList containing the cell counts in each row.
   */
  public ArrayList<ArrayList<Integer>> getRowBlackCellCounts() {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;

    ArrayList<ArrayList<Integer>> rowCellCounts2DList = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String [] oneRow = new String[numberOfColumns];
      oneRow = imageGrid[rowIndex];
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getBlackCellCountsInOneLine(oneRow);
      rowCellCounts2DList.add(lineCellCounts);
  }
  
  print2DArrayList(rowCellCounts2DList);
    return rowCellCounts2DList;
  }

  /**
   * Gets the cell counts in columns of the image grid.
   *
   * @return A 2D ArrayList containing the cell counts in each column.
   */
  public ArrayList<ArrayList<Integer>> getColumnBlackCellCount() {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;
    ArrayList<ArrayList<Integer>> columnCellCount2DList = new ArrayList<>();

    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      String [] colorsInOneColumn = new String [numberOfRows];
      // Get same column index from every row and store it in array that represents column line
      for ( int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
         colorsInOneColumn[rowIndex] = imageGrid[rowIndex][columnIndex];
      }
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getBlackCellCountsInOneLine(colorsInOneColumn);
      columnCellCount2DList.add(lineCellCounts);
    }
    print2DArrayList(columnCellCount2DList);
    return columnCellCount2DList;
  }

   /**
   * Counts the black cells in a single row/column (line) for the specified
   * color number.
   *
   * @param lineToAnalyze The array representing a row or column.
   */
  private ArrayList<Integer> getBlackCellCountsInOneLine (String [] lineToAnalyse) {
    ArrayList<Integer> counts = new ArrayList<>(); 
     int count = 0;
      boolean containsOnly0 = true;
    for (String blackOrWhiteValue : lineToAnalyse) {
       if (blackOrWhiteValue == "black") {
          count++;
          containsOnly0 = false;
        } else {
          if (count != 0) {
            counts.add(count);
            count = 0;
          }
    }
  }
     if (count != 0) {
      counts.add(count);
    }
    if (containsOnly0) {
      counts.add(0);
    }
    return counts;
  }

  /**
   * Validates and sets the image grid.
   *
   * @param imageGrid The 2D array representing the image grid.
   * @throws IllegalArgumentException throws if the image grid is null or has
   *                                  invalid dimensions.
   */
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

  public void print2DArrayList(ArrayList<ArrayList<Integer>> cellCounts2DList) {
    for (int i = 0; i < cellCounts2DList.size(); i++) {
      for (int z = 0; z < cellCounts2DList.get(i).size(); z++) {
        System.out.print(cellCounts2DList.get(i).get(z) + " ");
      }
      System.out.println();
    }
  }
}