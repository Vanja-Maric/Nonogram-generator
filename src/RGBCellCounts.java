import java.util.ArrayList;

/**
 * This class counts colored cells in rows and columns of an image grid.
 * Every number that is used represents one color different numbers where 0
 * represents no color.
 * 
 */
public class RGBCellCounts {
  String [][] imageGrid;

  /**
   * Constructs an RGBCellCounts object with the given image grid.
   *
   * @param imageGrid - 2D array representing the image grid.
   */
  public RGBCellCounts(String [][] imageGrid) {
    setImageGrid(imageGrid);
  }

  /**
   * Counts the colored cells (numbers) in each column for the specified color
   * number.
   *
   * @param color - The color number to count.
   * @return 2D Array list representing the counts of colored cells in each
   *         column.
   *         First dimention in array is the column index and second dimension
   *         contains numbers of cell in every chain.
   */
  public ArrayList<ArrayList<Integer>> getColumnCellCounts(String color) {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;

    ArrayList<ArrayList<Integer>> columnCellCount2DList = new ArrayList<>();

    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      String [] colorsInOneColumn = new String [numberOfRows];
      // Get same column index from every row and store it in array that represents column line
      for ( int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
         colorsInOneColumn[rowIndex] = imageGrid[rowIndex][columnIndex];
      }
      ArrayList<Integer> lineColourCounts = new ArrayList<>();
      lineColourCounts = getColorCellCountsInOneLine(colorsInOneColumn, color);
      columnCellCount2DList.add(lineColourCounts);
    }
    print2DArrayList(columnCellCount2DList);
    return columnCellCount2DList;
  }

  /**
   * Counts the colored cells (numbers) in each row for the specified color
   * number.
   *
   * @param color - The color number to count.
   * @return 2D Array list representing the counts of colored cells in each row.
   *         First dimention in array is the row index and second dimension
   *         contains numbers of cell in every chain.
   */
  public ArrayList<ArrayList<Integer>> getRowCellCounts(String color) {
    int numberOfRows = imageGrid.length;
    int numberOfColumns = imageGrid[0].length;

    ArrayList<ArrayList<Integer>> rowCellCount2DList = new ArrayList<>();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      String [] numbersInOneLine = new String [numberOfColumns];
      numbersInOneLine = imageGrid[rowIndex];
      ArrayList<Integer> lineColourCounts = new ArrayList<>();
      lineColourCounts = getColorCellCountsInOneLine(numbersInOneLine, color);
      rowCellCount2DList.add(lineColourCounts);
    }
    print2DArrayList(rowCellCount2DList);
    return rowCellCount2DList;
  }

  /**
   * Counts the colored cells in a single row/column (line) for the specified
   * color number.
   *
   * @param lineToAnalyze The array representing a row or column.
   * @param color   The color number to count.
   * @return A Array list of lists representing the counts of colored cells in
   *         each row.
   *         Only one 0 represents that there are no target color in that row
   *         0 before or after a number in returned array represents that there is
   *         some other colorat that place in that line be
   */
  private ArrayList<Integer> getColorCellCountsInOneLine(String[] lineToAnalyse, String color) {
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
        if (!lastCheckedColor.equals(color) && !lastCheckedColor.equals("white") && containsOnlyWhite) { // If it is the first time that
          // color appears in that chain
          colorCounts.add(0); // Push 0 to know that there is som other colored number in front of this.
        }
        count++;
        containsOnlyWhite = false;
      } else if (!colorValue.equals(color) && lastCheckedColor.equals(color)) {
        colorCounts.add(count); // If it is the first time some other number
        // appears after a chain of this colored number
        count = 0;
      }
      if (!colorValue.equals("white")) {
        containsOnlyWhite = false;
      }
      // If the number that is not the target color number or 0, add 0 to know that on that place is some other color number in this line
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

  public void print2DArrayList(ArrayList<ArrayList<Integer>> cellCounts2DList) {
    for (int cellRowIndex = 0; cellRowIndex < cellCounts2DList.size(); cellRowIndex++) {
      for (int cellColumnIndex = 0; cellColumnIndex < cellCounts2DList.get(cellRowIndex).size(); cellColumnIndex++) {
        System.out.print(cellCounts2DList.get(cellRowIndex).get(cellColumnIndex) + " ");
      }
      System.out.println();
    }
  } 

  /**
     * Validates and sets the image grid.
     *
     * @param imageGrid The 2D array representing the image grid.
     * @throws IllegalArgumentException throws if the image grid is null or has invalid dimensions.
     */
  private void setImageGrid(String [][] imageGrid) {
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
