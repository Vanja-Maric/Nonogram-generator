# Nonogram Generator

## Beskrivning
Nonogram Generator är en modul som omvandlar PNG-bilder till nonogram grid. Den konverterar inte bara bilder till spelbara pussel, utan genererar även räknenycklar (cell counts) och ger ledtrådar för att hjälpa till att lösa de skapade rutnätet. 

## Installation
För att använda Nonogram Generator, följ dessa enkla steg:

1. Ladda ner följande JAR: [jar]()
2. lägga till den till din app

## Tillgängliga Klasser och Metoder
Här är en översikt över de tillgängliga klasserna och deras metoder:

### Klass GridGetter(String imagePath, int numberOfColumns, int numberOfRows)
```GridGetter gridGetter = new GridGetter("src/images/sun.png", 15, 15);```

#### METOD 1:
- ```public String[][] getBlackAndWhiteGrid()```

##### Beskrivning:
- Denna metod laddar bilden, ändrar storleken på bilden och genererar en svart och vit nonogram-grid.
- Den returnerar en 2D-array där varje cell är antingen "black" (svart) eller "white" (vit) beroende på färgen i den ursprungliga bilden.
- Gridens dimensioner motsvarar antalet rader och kolumner som du angav vid skapandet av GridGetter-klassen.

##### Exempel:
```String[][] blackAndWhiteGrid = gridGetter.getBlackAndWhiteGrid();```

#### METOD 2:
- ```public String[][] getRedBlueGreenWhiteGrid()```

##### Beskrivning:
- Denna metod laddar bilden, ändrar storleken på bilden och genererar en röd, grön, blå och vit nonogram-grid.
- Den returnerar en 2D-array där varje cell är antingen "red" (röd), "green" (grön), "blue" (blå)  eller "white" (vit) beroende på färgen i den ursprungliga bilden.
- Gridens dimensioner motsvarar antalet rader och kolumner som du angav vid skapandet av GridGetter-klassen.

##### Exempel:
```String[][] blackAndWhiteGrid = gridGetter.getRedBlueGreenWhiteGrid();```

## Klass HintGetter(String[][] imageGrid) 
```HintGetter hintGetterBW = new HintGetter(blackAndWhiteGrid);```

#### METOD 1:
- ```public String getHint(int cellsRowIndex, int cellsColumnIndex) ```

##### Beskrivning:
- Denna metod returnerar en hint för laddade gridden genom att returnera färg för cellen som har specificerad rad index och kolumn index.

##### Exempel:
```System.out.println(hintGetterBW.getHint(13, 5));```

### Klass BWCellCounts(String[][] imageGrid) 
```new BWCellCounts(nonogramGrid).getAllColumnsBlackCellCount()```

#### METOD 1:
- ```ArrayList<ArrayList<Integer>> getAllRowsBlackCellCounts()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande svarta celler som finna i varje rad med mellanrum mellan grupper av svarta celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande svarta celler som finns i den raden med mellanrum mellan grupper av svarta celler. 
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för raderna i griden.

##### Exempel:
``` Box verticalBoxforButtonRows = Box.createVerticalBox();```
    ```NonogramCountsKeysUI nonogramCounsKeys = new NonogramCountsKeysUI();```
```for (int row = 0; row < nonogramGrid.length; row++) {```
     ``` Box buttonRowBox = getOneNonogramRow(nonogramGrid[row]);```
      ```if (isBlackAndWhite) {```
        ```nonogramCounsKeys.addNonogramOneRowCounts(new BWCellCounts(nonogramGrid).getAllRowsBlackCellCounts().get(row),```
            ```buttonRowBox);```
    ```  } else {```
        ```RGBCellCounts rgbCellCounts = new RGBCellCounts(nonogramGrid);```
    ```    nonogramCounsKeys.addNonogramOneRowCounts(rgbCellCounts.getColorCellCountsRows().get(row), buttonRowBox);```
    ```    nonogramCounsKeys.addNonogramOneRowColoursOfCounts(rgbCellCounts.getColorsOfCellCountsRows().get(row),```
         ```   buttonRowBox);```
     ``` }```
    ```  verticalBoxforButtonRows.add(buttonRowBox);```
  ```  }```

#### METOD 2:
- ```public ArrayList<ArrayList<Integer>> getAllColumnsBlackCellCount()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande svarta celler som finna i varje kolumn med mellanrum mellan grupper av svarta celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande svarta celler som finns i den kolumnen med mellanrum mellan grupper av svarta celler. 
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för kolumnerna i griden.

##### Exempel:
``` Box verticalBoxforButtonRows = Box.createVerticalBox();```
    ```NonogramCountsKeysUI nonogramCounsKeys = new NonogramCountsKeysUI();```
   ``` if (isBlackAndWhite) {```
   ```   verticalBoxforButtonRows.add(```
          ```nonogramCounsKeys.addNonogramAllColumnsCounts(new BWCellCounts(nonogramGrid).getAllColumnsBlackCellCount()));```
    ```}```

### Klass GBCellCounts(String[][] imageGrid) 
```RGBCellCounts rgbCellCounts = new RGBCellCounts(nonogramGrid);```

#### METOD 1:
- ```public ArrayList<ArrayList<Integer>> getColorCellCountsRows()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande röda, gröna eller blåa celler som finna i varje rad med mellanrum mellan grupper av sammafärgade celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande sammafärgade celler som finns i den raden.
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för raderna i griden.

##### Exempel:
```nonogramCounsKeys.addNonogramOneRowCounts(rgbCellCounts.getColorCellCountsRows().get(row), buttonRowBox);```

#### METOD 2:
- ```ArrayList<Integer>> getColorCellCountsColumns()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande röda, gröna eller blåa celler som finna i varje kolumn med mellanrum mellan grupper av sammafärgade celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande sammafärgade celler som finns i den kolumnen.
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för kolumnerna i griden.

##### Exempel:
```Box horizontalColumnCounts = nonogramCountsKeysUI.addNonogramAllColumnsCounts(rgbCellCounts.getColorCellCountsColumns());```

#### METOD 3:
- ```ArrayList<ArrayList<String>> getColorsOfCellCountsRows()```

##### Beskrivning:
- Denna metod skapar färg av räknenycklar (cell counts) för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en färg för varje räknenyckel i den raden.
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för raderna i griden.

##### Exempel:
```nonogramCounsKeys.addNonogramOneRowColoursOfCounts(rgbCellCounts.getColorsOfCellCountsRows().get(row),buttonRowBox);```


#### METOD 4:
- ```ArrayList<ArrayList<String>> getColorsOfCellCountsColumns()```

##### Beskrivning:
- Denna metod skapar färg (red, green or blue) av räknenycklar (cell counts) för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en färg för varje räknenyckel i den kolumnen.
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för kolumnerna i griden.

##### Exempel:
```Box horizontalColorsOfColumnCounts = nonogramCountsKeysUI.addNonogramAllColumnsColorsOfCounts(rgbCellCounts.getColorsOfCellCountsColumns());```

## TEST-APP OCH TESTNING
Du hittar en test-app för denna modul på denna [länk](https://github.com/Vanja-Maric/Nonogram-test-app/tree/main).
I detta repositorium i mappen tests/testrapport hittar du testning dokumentation:
- [Test beskrivning](https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testsDescription.md)
- [Test specifikation](https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testspecification.md) - innehåller ett antal testfall där du med hjälp av testapplikationen kan testa nonogram-generatormogulen manuellt
- [Testresultaten]()https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testResultalen.md
