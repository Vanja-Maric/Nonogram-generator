package nonogram;

import java.util.ArrayList;

public class MainTest {
  public static void main(String[] args) {
    NonogramGridCreator nonogramGridCreator = new NonogramGridCreator("src/nonogram/images/sun.png", 15, 10);
    String [][] ngc =  nonogramGridCreator.getBlackAndWhiteGrid();
    String [][] ngc2 =  nonogramGridCreator.getRedBlueGreenWhiteNonogramGrid();
    for (String[] strings : ngc2) {
     for (String strings2 : strings) {
       System.out.print(strings2);
       System.out.print(" ");
     }
     System.out.println();
    }

    BlackWhiteNonogramCellCounts blackWhiteNonogramCellCounts = new BlackWhiteNonogramCellCounts(ngc);
    ArrayList<ArrayList <Integer>> columns  = blackWhiteNonogramCellCounts.getBlackCellCountsInAllColumns();
     ArrayList<ArrayList <Integer>> rows  = blackWhiteNonogramCellCounts.getBlackCellCountsInAllRows();


    for ( ArrayList <Integer> intss : rows) {
      for (Integer intss2 : intss) {
        System.out.print(intss2);
        System.out.print(" ");
      }
      System.out.println();
    }

  }
}
