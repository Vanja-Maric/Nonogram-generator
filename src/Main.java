class Main {
  public static void main(String[] args) {
    GridGetter imageLoader = new GridGetter("src/images/sun.png",20,20);
    int[][] BWgrid = imageLoader.getBlackAndWhiteGrid();
    System.out.println();
    int[][] RGBgrid = imageLoader.getRedBlueGreenWhiteGrid();

    RGBKeyNumbersGetter rgbKeyNumbersGetter = new RGBKeyNumbersGetter(RGBgrid);
    // rgbKeyNumbersGetter.getColumnColorNumbers(2);
    rgbKeyNumbersGetter.getRowColorNumbers(2);
    HintGetter solver = new HintGetter(RGBgrid);

    // System.out.println(solver.getHint(8, 6));


    /*nonogramNumbersGetter.getRowNumbers();
    nonogramNumbersGetter.getColumnNumbers(); */
  }
}

// Has alfa true?????