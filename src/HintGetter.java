public class HintGetter {
  private int [][] nonogramGrid;
  private int columnIndex;
  private int rowIndex;

  public HintGetter (int [][] nonogramGrid, int columnIndex, int rowIndex) {
    setNonogramGrid(nonogramGrid);
    setColumnIndex(columnIndex);
    setRowIndex(rowIndex);
  }

  public int getHint () {
    if (nonogramGrid[rowIndex][columnIndex] == 0) {
      return 0;
    } else {
      return 1;
    }
  } 

  private void setNonogramGrid (int [][] nonogramGrid) {
    this.nonogramGrid = nonogramGrid;
  }

  private void setColumnIndex (int columnIndex) {
    this.columnIndex = columnIndex;
  }

  public void setRowIndex(int rowIndex) {
    this.rowIndex = rowIndex;
  }
}
