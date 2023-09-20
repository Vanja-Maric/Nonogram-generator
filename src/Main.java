class Main {
  public static void main(String[] args) {
    GridGetter imageLoader = new GridGetter("src/images/sun.png",20,20);
    String [][] BWgrid = imageLoader.getBlackAndWhiteGrid();
    // System.out.println();
    // String [][] RGBgrid = imageLoader.getRedBlueGreenWhiteGrid();
    BWCellCounts BWCellCounts = new BWCellCounts(BWgrid);
    // BWCellCounts.getRowBlackCellCounts();
    // System.out.println();
    BWCellCounts.getColumnBlackCellCount();
    // RGBCellCounts rgbKeyNumbersGetter = new RGBCellCounts(RGBgrid);
    // rgbKeyNumbersGetter.getColumnCellCounts(" Red  ");
    // rgbKeyNumbersGetter.getColumnColorNumbers(2);
    // rgbKeyNumbersGetter.getRowCellCounts(3);
    // HintGetter solver = new HintGetter(RGBgrid);
    // System.out.println(solver.getHint(5, 6));
    // System.out.println(solver.getHint(8, 6));


    /*nonogramNumbersGetter.getRowNumbers();
    nonogramNumbersGetter.getColumnNumbers(); */
  }
}

// Has alfa true?????