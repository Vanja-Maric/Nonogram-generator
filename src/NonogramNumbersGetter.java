import java.util.ArrayList;

public class NonogramNumbersGetter {
  int [][] pictureGrid;

  public NonogramNumbersGetter(int [][] pictureGrid) {
    setPictureGrid(pictureGrid);
  }
  
  public ArrayList<ArrayList<Integer>> getRowNumbers () {
    int numberOfRows = pictureGrid.length;
    int numberOfColumns = pictureGrid[0].length;

    // Count 1 in Rows is 2D ArrayList and not 2D Array because I do not know how many groups of 1s are in every row
    // First D in this array is number of row -1 
    // Second D in this array is groups of 1s
    ArrayList<ArrayList<Integer>> count1InRows = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      ArrayList<Integer> rowList = new ArrayList<>(); // Make new underlist for every row
      int count = 0;

      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        if (pictureGrid[rowIndex][columnIndex] == 1) {
        count++;
        } else {
          if (count != 0) {
          rowList.add(count);
          count = 0; 
          }
        }
      }
       if (count != 0) {
            rowList.add(count); // Add last chain if 1 was last in the row
        }

        count1InRows.add(rowList); // Add uder list to main 2D Array
    }

    // Print 2D array list - groups of 1 in every row
    for (int i = 0; i < count1InRows.size(); i++) {
      for (int z = 0; z < count1InRows.get(i).size(); z++) {
      System.out.print(count1InRows.get(i).get(z) + " ");
      }
      System.out.println();
    }
    return count1InRows;
  }

  public void setPictureGrid(int[][] pictureGrid) {
    // Validera??? 
    this.pictureGrid = pictureGrid;
  }
}
