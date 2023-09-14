class Main {
  public static void main(String[] args) {
    GridGetter imageLoader = new GridGetter("src/images/sun.png",20,20);
    int[][] BWgrid = imageLoader.getBlackAndWhiteGrid();
    System.out.println();
    int[][] RGBgrid = imageLoader.getRedBlueGreenWhiteGrid();

    /*NonogramNumbersGetter nonogramNumbersGetter = new NonogramNumbersGetter(grid);
    HintGetter solver = new HintGetter(grid, 8, 1);
    System.out.println(solver.getHint());
    nonogramNumbersGetter.getRowNumbers();
    nonogramNumbersGetter.getColumnNumbers(); */
  }
}

// Has alfa true?????