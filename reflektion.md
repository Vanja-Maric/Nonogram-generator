| Namn och förklarning | Reflektion och regler från Clean Code |
| -------- | -------- |
| Klassnamn __BlackWhiteCellCounts__ | __Use Pronounceable Names__ - Jag har funderat på att förkorta namnet på metoden BWCellCounts för att det inte ska bli för långt. Men då blev namnet väldigt svårt att uttala, och det krävde till och med en kommentar för att förklara vad BW betyder. Därför bestämde jag mig för att namnge klassen "BlackWhiteCellCounts".| 
| | __Class Names__ - Jag funderade på att lägga till ordet "Processor" eller "Generator" i namnet, men klassnamnet bör inte innehålla ett verb. Därför skapade jag en annan klass med namnet "GridGetter", eftersom jag tror att endast namnet "Grid" inte ger tillräcklig information om klassens syfte, och här är det ett undantag där "getter" passar bra.|
| Metodnamn __getAllRowsBlackCellCounts()__ | __Intention revealing names__ - För att undvika onödiga kommentarer som förklarar metodnamn har jag valt att använda ett något längre namn för metoden som helt och hållet avslöjar dess avsikt. GET????????|
| Metodnamn __getCellCountsColumns()__ | __Don’t Add Gratuitous Context__ - Metoden ligger i en klass som heter "RedGreenBlueCellCounts". Jag har funderat på att namnge metoden "getRGBCellCountsColumns" för att betona att den hämtar cellräkningar för rött, grönt och blått. Eftersom klassen redan betonar att den hanterar röda, gröna och blåa cellräkningar, känns det onödigt att inkludera "RGB" i metodnamnet.|
| Metodnamn __getCountsColorsRows()__ | __Avoid Disinformation__ - Tidigare hade jag namngett den här metoden "getColorsofCountsRows," och en annan metod i samma klass hade namnet "getColorCountsRows." Det blev mycket svårt att särskilja dem. För att undvika förvirring och felaktig information valde jag att namnge den här metoden om till "getCountsColorsRows," vilket markant underlättade att skilja den från den andra metoden. |
| int __rowIndex__ | ```for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++)``` __Use Intention-Revealing Names__ - Eftersom jag använder radindex på några ställen i denna for-loop, övervägde jag att ge den heltalsvariabel som används i for-loopen ett meningsfullt och avsiktsavslöjande namn istället för att bara använda en enda bokstav som 'i'.|
| | __Use Searchable Names__ - "rowIndex" blir mycket lättare att hitta i koden än "i".|


| Metodnamn och länk eller kod | Antal rader | Reflektion |
| -------- | -------- | -------- |
| <code>
private ArrayList<Integer> getOneColorCellCountsInOneLine(String[] lineToAnalyse, String color) {
    color = color.toLowerCase().trim();
    if (!color.equals("red") && !color.equals("green") && !color.equals("blue")) {
        throw new IllegalArgumentException("Please eneter a valid color name - red, green or blue.");
    }
    ArrayList<Integer> colorCounts = new ArrayList<>(); // Make a new sublist that will be added to 2D array
    String lastCheckedColor = "white";
    boolean containsOnlyWhite = true;
    int count = 0;
    for (String colorValue : lineToAnalyse) {
        if (colorValue.equals(color)) {
            if (!lastCheckedColor.equals(color) && !lastCheckedColor.equals("white") && containsOnlyWhite) {
                // If it is the first time that the color appears in that chain
                colorCounts.add(0); // Push 0 to know that there is some other colored number in front of this.
            }
            count++;
            containsOnlyWhite = false;
        } else if (!colorValue.equals(color) && lastCheckedColor.equals(color)) {
            colorCounts.add(count); // If it is the first time some other number
            count = 0; // appears after a chain of this colored number
        }
        if (!colorValue.equals("white")) {
            containsOnlyWhite = false;
        }
        // If the number that is not the target color number or 0, add 0 to know that on
        // that place is some other color number in this line
        if (!lastCheckedColor.equals(colorValue) && !colorValue.equals("white") && !colorValue.equals(color)) {
            colorCounts.add(0);
        }
        lastCheckedColor = colorValue;
    }
    if (count != 0) {
        colorCounts.add(count);
    }
    if (containsOnlyWhite) {
        colorCounts.add(0);
    }
    return colorCounts;
}
</code>| 35 ||
||||
||||
||||
||||
||||



