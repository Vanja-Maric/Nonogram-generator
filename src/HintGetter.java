public class HintGetter {
  private int [][] nonogramGrid;

  public HintGetter (int [][] nonogramGrid) {
    setNonogramGrid(nonogramGrid);
  }

  public int getHint (int rowIndex, int columnIndex) {
    // Validation???
    return nonogramGrid[rowIndex][columnIndex];
  } 

  private void setNonogramGrid (int [][] nonogramGrid) {
    this.nonogramGrid = nonogramGrid;
  }
}
