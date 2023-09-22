package nonogram;

import java.util.ArrayList;

// This class counts black and white cells in rows and columns of an image grid.
public class BWCellCounts {
  String[][] imageGrid;

  public BWCellCounts(String[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  // Gets the cell counts in all rows of the image grid.
  public ArrayList<ArrayList<Integer>> getAllRowsBlackCellCounts() {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;

    ArrayList<ArrayList<Integer>> rowCellCounts2DList = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String[] oneRow = new String[numberOfColumns];
      oneRow = imageGrid[rowIndex];
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getBlackCellCountsInOneLine(oneRow);
      rowCellCounts2DList.add(lineCellCounts);
    }
    return rowCellCounts2DList;
  }

  // Gets the cell counts in all columns of the image grid.
  public ArrayList<ArrayList<Integer>> getAllColumnsBlackCellCount() {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;
    ArrayList<ArrayList<Integer>> columnCellCount2DList = new ArrayList<>();

    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      String[] colorsInOneColumn = new String[numberOfRows];
      // Get same column index from every row and store it in array that represents
      // column line
      for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
        colorsInOneColumn[rowIndex] = imageGrid[rowIndex][columnIndex];
      }
      ArrayList<Integer> lineCellCounts = new ArrayList<>();
      lineCellCounts = getBlackCellCountsInOneLine(colorsInOneColumn);
      columnCellCount2DList.add(lineCellCounts);
    }
    return columnCellCount2DList;
  }

  // Counts the black cells in a single row/column (line).
  private ArrayList<Integer> getBlackCellCountsInOneLine(String[] lineToAnalyse) {
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

  // Validates and sets the image grid.
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
}