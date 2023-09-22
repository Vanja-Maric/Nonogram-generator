package nonogram;

import java.util.ArrayList;

// This class counts colored cells in rows and columns of an image grid.
public class RGBCellCounts {
  String[][] imageGrid;

  public RGBCellCounts(String[][] imageGrid) {
    setImageGrid(imageGrid);
  }

  // Counts the colored cells in each column for the specified color.
  private ArrayList<ArrayList<Integer>> getColumnCellCountsForOneColor(String color) {
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
      ArrayList<Integer> lineColourCounts = new ArrayList<>();
      lineColourCounts = getOneColorCellCountsInOneLine(colorsInOneColumn, color);
      columnCellCount2DList.add(lineColourCounts);
    }
    return columnCellCount2DList;
  }

  // Counts the colored cells (pixels) in each row for the specified color.
  private ArrayList<ArrayList<Integer>> getRowCellCountsforOneColor(String color) {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;

    ArrayList<ArrayList<Integer>> rowCellCount2DList = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String[] numbersInOneLine = new String[numberOfColumns];
      numbersInOneLine = imageGrid[rowIndex];
      ArrayList<Integer> lineColourCounts = new ArrayList<>();
      lineColourCounts = getOneColorCellCountsInOneLine(numbersInOneLine, color);
      rowCellCount2DList.add(lineColourCounts);
    }
    return rowCellCount2DList;
  }

  // Counts the colored cells in a single row/column (line) for the specified
  // color.
  private ArrayList<Integer> getOneColorCellCountsInOneLine(String[] lineToAnalyse, String color) {
    color = color.toLowerCase().trim();
    if (!color.equals("red") && !color.equals("green") && !color.equals("blue")) {
      throw new IllegalArgumentException("Please eneter a valid color name - red, green or blue.");
    }

    ArrayList<Integer> colorCounts = new ArrayList<>(); // Make a new sublist that will be added to 2D array
    String lastCheckedColor = "white";
    boolean containsOnlyWhite = true;
    int count = 0;

    for (String colorValue : lineToAnalyse) {
      if (colorValue.equals(color)) {
        if (!lastCheckedColor.equals(color) && !lastCheckedColor.equals("white") && containsOnlyWhite) {
          // If it is the first time that the color appears in that chain
          colorCounts.add(0); // Push 0 to know that there is som other colored number in front of this.
        }
        count++;
        containsOnlyWhite = false;
      } else if (!colorValue.equals(color) && lastCheckedColor.equals(color)) {
        colorCounts.add(count); // If it is the first time some other number
        count = 0; // appears after a chain of this colored number
      }
      if (!colorValue.equals("white")) {
        containsOnlyWhite = false;
      }
      // If the number that is not the target color number or 0, add 0 to know that on
      // that place is some other color number in this line
      if (!lastCheckedColor.equals(colorValue) && !colorValue.equals("white") && !colorValue.equals(color)) {
        colorCounts.add(0);
      }
      lastCheckedColor = colorValue;
    }
    if (count != 0) {
      colorCounts.add(count);
    }
    if (containsOnlyWhite) {
      colorCounts.add(0);
    }
    return colorCounts;
  }

  // Gets all colors of cell counts in all rows or all columns.
  private ArrayList<ArrayList<Integer>> getColorCellCountsForAllRowsOrColumns(
      ArrayList<ArrayList<Integer>> redCountsinAllRowsOrColumns,
      ArrayList<ArrayList<Integer>> greenCountsInAllRowsOrColumns,
      ArrayList<ArrayList<Integer>> blueCountsInAllRowsOrColumns) {
    ArrayList<ArrayList<Integer>> allColorsCountsTogehter = new ArrayList<>();

    for (int firstDimensionIndex = 0; firstDimensionIndex < redCountsinAllRowsOrColumns.size(); firstDimensionIndex++) {
      ArrayList<Integer> innerList = new ArrayList<>();
      for (int secondDimensionIndex = 0; secondDimensionIndex < redCountsinAllRowsOrColumns.get(firstDimensionIndex)
          .size(); secondDimensionIndex++) {
        if (redCountsinAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          innerList.add(redCountsinAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex));
        } else if (greenCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          innerList.add(greenCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex));
        } else if (blueCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          innerList.add(blueCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex));
        }
      }

      if (innerList.isEmpty()) {
        innerList.add(0);
      }
      allColorsCountsTogehter.add(firstDimensionIndex, innerList);
    }
    return allColorsCountsTogehter;
  }

  // Gets colors of cell counts for all colors in all rows or all columns.
  private ArrayList<ArrayList<String>> getColorsOfCellCounts(ArrayList<ArrayList<Integer>> redCountsinAllRowsOrColumns,
      ArrayList<ArrayList<Integer>> greenCountsInAllRowsOrColumns,
      ArrayList<ArrayList<Integer>> blueCountsInAllRowsOrColumns) {
    ArrayList<ArrayList<String>> colors = new ArrayList<>();

    for (int firstDimensionIndex = 0; firstDimensionIndex < redCountsinAllRowsOrColumns.size(); firstDimensionIndex++) {
      ArrayList<String> colorRow = new ArrayList<>();
      for (int secondDimensionIndex = 0; secondDimensionIndex < redCountsinAllRowsOrColumns.get(firstDimensionIndex)
          .size(); secondDimensionIndex++) {
        if (redCountsinAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          colorRow.add("red");
        } else if (greenCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          colorRow.add("green");
        } else if (blueCountsInAllRowsOrColumns.get(firstDimensionIndex).get(secondDimensionIndex) != 0) {
          colorRow.add("blue");
        } else {
          colorRow.add("white");
        }
      }
      colors.add(colorRow);
    }
    return colors;
  }

  public ArrayList<ArrayList<Integer>> getColorCellCountsRows() {
    return getColorCellCountsForAllRowsOrColumns(getRowCellCountsforOneColor("red"),
        getRowCellCountsforOneColor("green"), getRowCellCountsforOneColor("blue"));
  }

  public ArrayList<ArrayList<Integer>> getColorCellCountsColumns() {
    return getColorCellCountsForAllRowsOrColumns(getColumnCellCountsForOneColor("red"),
        getColumnCellCountsForOneColor("green"), getColumnCellCountsForOneColor("blue"));
  }

  public ArrayList<ArrayList<String>> getColorsOfCellCountsRows() {
    return getColorsOfCellCounts(getRowCellCountsforOneColor("red"), getRowCellCountsforOneColor("green"),
        getRowCellCountsforOneColor("blue"));
  }

  public ArrayList<ArrayList<String>> getColorsOfCellCountsColumns() {
    return getColorsOfCellCounts(getColumnCellCountsForOneColor("red"),
        getColumnCellCountsForOneColor("green"), getColumnCellCountsForOneColor("blue"));
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
