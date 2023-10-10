package nonogram;

import java.util.ArrayList;

/**
 * This method generates cell counts for contiguous black cells found in each
 * row with gaps between groups of black cells for the loaded grid.
 * Counts are stored in 2DArrayLists.
 */
public class BlackWhiteNonogramCellCounts {
  String[][] nonogramGrid;

  public BlackWhiteNonogramCellCounts(String[][] nonogramGrid) {
    setNonogramGrid(nonogramGrid);
  }

   /**
   * Counts and returns the number of consecutive black cells separated by white cells in each row of the nonogram grid.
   * 
   * @return ArrayList of ArrayLists of integers that represents the number of black cells in each row of the nonogram grid.
   */
  public ArrayList<ArrayList<Integer>> getBlackCellCountsInAllRows() {
    int numberOfRows = nonogramGrid.length;
    int numberOfColumns = nonogramGrid[0].length;
    ArrayList<ArrayList<Integer>> blackCellCountsInAllRows = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String[] colorsInOneRow = new String[numberOfColumns];
      colorsInOneRow = nonogramGrid[rowIndex];

      ArrayList<Integer> blackCellCountsInOneRow = new ArrayList<>();
      blackCellCountsInOneRow = getCountsInOneRowOrCollumn(colorsInOneRow);

      blackCellCountsInAllRows.add(blackCellCountsInOneRow);
    }
    return blackCellCountsInAllRows;
  }
 
  /**
   * Counts and returns the number of consecutive black cells separated by white cells in each column of the nonogram grid.
   * 
   * @return ArrayList of ArrayLists of integers that represents the number of black cells in each column of the nonogram grid.
   */
  public ArrayList<ArrayList<Integer>> getBlackCellCountsInAllColumns() {
    int numberOfRows = nonogramGrid.length;
    int numberOfColumns = nonogramGrid[0].length;
    ArrayList<ArrayList<Integer>> blackCellCountsInAllColumns = new ArrayList<>();

    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      String[] colorsInOneColumn = new String[numberOfRows];
      // Get same column index from every row and store it in array that represents column line
      for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
        colorsInOneColumn[rowIndex] = nonogramGrid[rowIndex][columnIndex];
      }

      ArrayList<Integer> blackCellCountsInOneColumn = new ArrayList<>();
      blackCellCountsInOneColumn = getCountsInOneRowOrCollumn(colorsInOneColumn);
      blackCellCountsInAllColumns.add(blackCellCountsInOneColumn);
    }
    return blackCellCountsInAllColumns;
  }

  // Counts contiguous black cells found in a single row/column with gaps between
  // groups of black cells.
  private ArrayList<Integer> getCountsInOneRowOrCollumn(String[] rowOrCollumnToAnalyse) {
    ArrayList<Integer> countsInOneRowOrCollumn = new ArrayList<>();
    int currentCountOfBlackCells = 0;
    boolean containsOnlyWhiteCells = true;

    for (String cell : rowOrCollumnToAnalyse) {
      if (isCellBlack(cell)) {
        currentCountOfBlackCells++;
        containsOnlyWhiteCells = false;
      } else {
        boolean addedCurrentCountOfBlackCellsToCounts = handleWhiteCell(currentCountOfBlackCells, countsInOneRowOrCollumn);
        if (addedCurrentCountOfBlackCellsToCounts) {
          currentCountOfBlackCells = 0;
        }
      }
    }

    handleWhiteCell(currentCountOfBlackCells, countsInOneRowOrCollumn); // If the last cell in the row/column is black.
    handleOnlyWhiteCellsInOneLine(containsOnlyWhiteCells, countsInOneRowOrCollumn);

    return countsInOneRowOrCollumn;
  }

  private boolean handleWhiteCell(int countBlackCells, ArrayList<Integer> countsInOneRowOrColumn) {
    if (countBlackCells != 0) {
      countsInOneRowOrColumn.add(countBlackCells);
      return true;
    }
    return false;
  }

  private void handleOnlyWhiteCellsInOneLine(boolean containsOnlyWhiteCells, ArrayList<Integer> countsInOneRowOrColumn) {
    if (containsOnlyWhiteCells) {
      countsInOneRowOrColumn.add(0);
    }
  }

  private boolean isCellBlack(String cell) {
    return cell.equals("black");
  }

  // Validation methods
  private void setNonogramGrid(String[][] imageGrid) {
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

    this.nonogramGrid = imageGrid;
  }
}
