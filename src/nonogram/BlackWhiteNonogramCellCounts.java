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
   * Gets the cell counts in all rows of the image grid.
   */
  public ArrayList<ArrayList<Integer>> getAllRowsBlackCellCounts() {
    int numberOfRows = nonogramGrid.length;
    int numberOfColumns = nonogramGrid[0].length;

    ArrayList<ArrayList<Integer>> rowCellCounts2DList = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String[] oneRow = new String[numberOfColumns];
      oneRow = nonogramGrid[rowIndex];
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getCountsInOneRowOrCollumn(oneRow);
      rowCellCounts2DList.add(lineCellCounts);
    }
    return rowCellCounts2DList;
  }

  /**
   * Gets the cell counts in all columns of the image grid.
   */
  public ArrayList<ArrayList<Integer>> getAllColumnsBlackCellCount() {
    int numberOfRows = nonogramGrid.length;
    int numberOfColumns = nonogramGrid[0].length;
    ArrayList<ArrayList<Integer>> columnCellCount2DList = new ArrayList<>();

    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      String[] colorsInOneColumn = new String[numberOfRows];
      // Get same column index from every row and store it in array that represents column line
      for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
        colorsInOneColumn[rowIndex] = nonogramGrid[rowIndex][columnIndex];
      }
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getCountsInOneRowOrCollumn(colorsInOneColumn);
      columnCellCount2DList.add(lineCellCounts);
    }
    return columnCellCount2DList;
  }

  // Counts contiguous black cells found in a single row/column with gaps between
  // groups of black cells.
  private ArrayList<Integer> getCountsInOneRowOrCollumn(String[] rowOrCollumnToAnalyse) {
    ArrayList<Integer> countsInOneRowOrCollumn = new ArrayList<>();
    int countBlackCells = 0;
    boolean containsOnlyWhiteCells = true;

    for (String cell : rowOrCollumnToAnalyse) {
      if (isBlack(cell)) {
        countBlackCells++;
        containsOnlyWhiteCells = false;
      } else {
        boolean addedNumberToCounts = handleWhiteCell(countBlackCells, countsInOneRowOrCollumn);
        if (addedNumberToCounts) {
          countBlackCells = 0;
        }
      }
    }

    handleWhiteCell(countBlackCells, countsInOneRowOrCollumn); // If the last cell in the row/column is black.
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

  private boolean isBlack(String cell) {
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

// TEST THIS