import java.util.ArrayList;

public class RGBKeyNumbersGetter {
  int[][] pictureGrid;

  public RGBKeyNumbersGetter(int[][] pictureGrid) {
    setPictureGrid(pictureGrid);
  }

  public ArrayList<ArrayList<Integer>> getRowColorNumbers(int colorNumber) {
    int numberOfRows = pictureGrid.length;
    int numberOfColumns = pictureGrid[0].length;

    ArrayList<ArrayList<Integer>> countPickedNumbersInRows = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      int lastNumber = 0;
      boolean only0 = true;
      int count = 0;
      ArrayList<Integer> rowListPickedColour = new ArrayList<>(); // Make a new sublist for every row
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        if (pictureGrid[rowIndex][columnIndex] == colorNumber) {
          if (lastNumber != colorNumber && lastNumber != 0) {
            rowListPickedColour.add(0); // Push 0 to know that there is some other colored number in front of this.
          }
          count++;
          lastNumber = colorNumber;
          only0 = false;
        } else {
          if (lastNumber == colorNumber) {
            rowListPickedColour.add(count);
            lastNumber = pictureGrid[rowIndex][columnIndex];
            count = 0;
          }
        }
      }
      if (only0) {
        rowListPickedColour.add(0);
      }
      if (count != 0) {
        rowListPickedColour.add(count);
      }

      countPickedNumbersInRows.add(rowListPickedColour);
    }
    // Print 2D ArrayList - groups of the specified color in every row
    for (int i = 0; i < countPickedNumbersInRows.size(); i++) {
      for (int z = 0; z < countPickedNumbersInRows.get(i).size(); z++) {
        System.out.print(countPickedNumbersInRows.get(i).get(z) + " ");
      }
      System.out.println();
    }
    return countPickedNumbersInRows;
  }

  public ArrayList<ArrayList<Integer>> getColumnColorNumbers(int colorNumber) {
    int numberOfRows = pictureGrid.length;
    int numberOfColumns = pictureGrid[0].length;

    ArrayList<ArrayList<Integer>> countPickedNumbersInColumns = new ArrayList<>();
    for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
      int lastNumber = 0;
      boolean only0 = true;
      int count = 0;
      ArrayList<Integer> columnListPickedColour = new ArrayList<>(); // Make a new sublist for every column
      for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
        if (pictureGrid[rowIndex][columnIndex] == colorNumber) {
          if (lastNumber != colorNumber && lastNumber != 0) {
            columnListPickedColour.add(0); // Push 0 to know that there is som oter colored number in front of this.
          }
          count++;
          lastNumber = colorNumber;
          only0 = false;
        } else {
          if (lastNumber == colorNumber) {
            columnListPickedColour.add(count);
            lastNumber = pictureGrid[rowIndex][columnIndex];
            count = 0;
          }
        }
      }
      if (only0) {
        columnListPickedColour.add(0);
      }
      if (count != 0) {
        columnListPickedColour.add(count);
      }

      countPickedNumbersInColumns.add(columnListPickedColour);
    }
    // Print 2D ArrayList - groups of 1 in every column
    for (int i = 0; i < countPickedNumbersInColumns.size(); i++) {
      for (int z = 0; z < countPickedNumbersInColumns.get(i).size(); z++) {
        System.out.print(countPickedNumbersInColumns.get(i).get(z) + " ");
      }
      System.out.println();
    }
    return countPickedNumbersInColumns;
  }

  public void setPictureGrid(int[][] pictureGrid) {
    // Validera???
    this.pictureGrid = pictureGrid;
  }
}
