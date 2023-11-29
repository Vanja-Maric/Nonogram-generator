# Nonogram Generator Vesrsion 1.0.0

## Beskrivning
Nonogram Generator är en modul som omvandlar PNG-bilder till nonogram grid. Den konverterar inte bara bilder till grid för spelbara pussel, utan genererar även räknenycklar (cell counts) och ger ledtrådar för att hjälpa till att lösa de skapade rutnätet. 
Mer information om hur man spelar Nonogram-spelet finns här: [Nonogram](https://sv.wikipedia.org/wiki/Japanskt_bildkryss)

## Installation
För att använda Nonogram Generator, följ dessa enkla steg:

1. Ladda ner följande JAR: [jar](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/nonogramGenerator.jar)
2. Integrera JAR-filen i din applikation:
- Om du utvecklar en Java-applikation, lägg till den nedladdade JAR-filen (nonogramGenerator.jar) i ditt projekts biblioteksmapp. Detta kan oftast göras genom att högerklicka på projektets bibliotekssektion i din IDE (som Eclipse eller IntelliJ IDEA) och välja "Lägg till externa JAR-filer".
- Om din utvecklingsmiljö är annorlunda, eller om du använder ett byggverktyg som Maven eller Gradle, behöver du lägga till JAR-filen i ditt beroendehanteringssystem. För Maven och Gradle, lägg till en referens till JAR-filen i din pom.xml eller build.gradle-fil.
- Efter att ha lagt till JAR-filen, se till att projektet ombyggs för att reflektera de nya beroendena.

## Tillgängliga Klasser och Metoder
Här är en översikt över de tillgängliga klasserna och deras metoder:

***
### Klass NonogramGridCreator(String imagePath, int numberOfColumns, int numberOfRows)
```NonogramGridCreator nonogramGridCreator = new NonogramGridCreator("src/images/sun.png", 15, 15);```

#### METOD 1:
- ```public String[][] getBlackAndWhiteGrid()```

##### Beskrivning:
- Denna metod laddar bilden, ändrar storleken på bilden och genererar en svart och vit nonogram-grid.
- Den returnerar en 2D-array där varje cell är antingen "black" (svart) eller "white" (vit) beroende på färgen i den ursprungliga bilden.
- Gridens dimensioner motsvarar antalet rader och kolumner som du angav vid skapandet av NonogramGridCreator-klassen.

##### Exempel:
```String[][] blackAndWhiteGrid = nonogramGridCreator.getBlackAndWhiteGrid();```

***

### Klass HintGetter(String[][] imageGrid) 
```HintGetter hintGetterBW = new HintGetter(blackAndWhiteGrid);```

#### METOD 1:
- ```public String getHint(int cellsRowIndex, int cellsColumnIndex) ```

##### Beskrivning:
- Denna metod returnerar en hint för laddade gridden genom att returnera färg för cellen som har specificerad rad index och kolumn index.

##### Exempel:
```System.out.println(hintGetterBW.getHint(13, 5));```

***

### Klass BlackWhiteCellCounts(String[][] imageGrid) 
```new BlackWhiteCellCounts(nonogramGrid).getBlackCellCountsInAllColumns()```

#### METOD 1:
- ```ArrayList<ArrayList<Integer>> getBlackCellCountsInAllRows()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande svarta celler som finns i varje rad med mellanrum mellan grupper av svarta celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande svarta celler som finns i den raden med mellanrum mellan grupper av svarta celler. 
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för raderna i griden.

##### Exempel:
```
Box verticalBoxforButtonRows = Box.createVerticalBox();<br>
 NonogramCountsKeysUI nonogramCounsKeys = new NonogramCountsKeysUI();<br>
 for (int row = 0; row < nonogramGrid.length; row++) {<br>
 Box buttonRowBox = getOneNonogramRow(nonogramGrid[row]);<br>
 if (isBlackAndWhite) {<br>
 nonogramCounsKeys.addNonogramOneRowCounts(new BlackWhiteCellCounts(nonogramGrid).getBlackCellCountsInAllRows().get(row),<br>
 buttonRowBox);<br>
 } else {
 RedGreenBLueCellCounts RedGreenBLueCellCounts = new RedGreenBLueCellCounts(nonogramGrid);<br>
 nonogramCounsKeys.addNonogramOneRowCounts(RedGreenBLueCellCounts.getCellCountsRows().get(row), buttonRowBox);<br>
 nonogramCounsKeys.addNonogramOneRowColoursOfCounts(RedGreenBLueCellCounts.getCountsColorsRows().get(row),v
 buttonRowBox);<br>
 }<br>
 verticalBoxforButtonRows.add(buttonRowBox);<br>
 }
 ```

#### METOD 2:
- ```public ArrayList<ArrayList<Integer>> getBlackCellCountsInAllColumns()```

##### Beskrivning:
- Denna metod skapar räknenycklar (cell counts) för sammanhängande svarta celler som finna i varje kolumn med mellanrum mellan grupper av svarta celler för laddade gridden.
- Den returnerar en 2D ArrayList där varje ArrayList innom den består av en eller flera nummer som visar hur många sammanhängande svarta celler som finns i den kolumnen med mellanrum mellan grupper av svarta celler. 
- Indexen i den yttre ArrayListen i 2D ArrayList motsvarar indexen för kolumnerna i griden.

##### Exempel:
"Box verticalBoxforButtonRows = Box.createVerticalBox();
 NonogramCountsKeysUI nonogramCounsKeys = new NonogramCountsKeysUI(); <br>
 if (isBlackAndWhite) { <br>
 verticalBoxforButtonRows.add( <br>
 nonogramCounsKeys.addNonogramAllColumnsCounts(new BlackWhiteCellCounts(nonogramGrid).getBlackCellCountsInAllColumns())); <br>
 }

## Test-App och testning
Du hittar en test-app för denna modul på denna [länk](https://github.com/Vanja-Maric/Nonogram-test-app/tree/main).
I detta repositorium i mappen tests/testrapport hittar du testning dokumentation:
- [Test beskrivning](https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testraport/testsDescription.md)
- [Test specifikation](https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testraport/testSpecification.md) - innehåller ett antal testfall där du med hjälp av testapplikationen kan testa nonogram-generatormogulen manuellt
- [Testresultaten](https://github.com/Vanja-Maric/Nonogram-test-app/blob/main/tests/testraport/testResults.md)

## Buggrapporter/issues
1. Den nuvarande versionen fungerar pålitligt endast med bilder i PNG-format.
2. På grund av så liten antal av celler i gridden blir det väldigt svårt att känna igen de grundläggande former och konturer som finns i original bilden i nonogram gridden om antalet rader och/eller kolumner är färre än 10.

## License
[Öppna här](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/LICENSE)

## Hur du kan bidra
Du är välkommen att bidra till detta projekt. Följ dessa steg:

1. Skapa en fork av den här depån (repositoriet).
2. Gör dina ändringar.
3. Skicka in en Pull Request.