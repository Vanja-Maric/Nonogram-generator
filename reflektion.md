# Kapitel 2
| Namn och förklarning | Reflektion och regler från Clean Code |
| -------- | -------- |
| Klassnamn __BlackWhiteCellCounts__ | __Use Pronounceable Names__ - Jag har funderat på att förkorta namnet på metoden BWCellCounts för att det inte ska bli för långt. Men då blev namnet väldigt svårt att uttala, och det krävde till och med en kommentar för att förklara vad BW betyder. Därför bestämde jag mig för att namnge klassen "BlackWhiteCellCounts".| 
| | __Class Names__ - Jag funderade på att lägga till ordet "Processor" eller "Generator" i namnet, men klassnamnet bör inte innehålla ett verb. Därför skapade jag en annan klass med namnet "GridGetter", eftersom jag tror att endast namnet "Grid" inte ger tillräcklig information om klassens syfte, och här är det ett undantag där "getter" passar bra.|
| Metodnamn __getAllRowsBlackCellCounts()__ | __Intention revealing names__ - För att undvika onödiga kommentarer som förklarar metodnamn har jag valt att använda ett något längre namn för metoden som helt och hållet avslöjar dess avsikt. |
|| __Method Names__ - "Accessors, mutators, and predicates should be named for their value and prefixed with get, set, and is according to the javabean standard."|
| Metodnamn __getCellCountsColumns()__ | __Don’t Add Gratuitous Context__ - Metoden ligger i en klass som heter "RedGreenBlueCellCounts". Jag har funderat på att namnge metoden "getRGBCellCountsColumns" för att betona att den hämtar cellräkningar för rött, grönt och blått. Eftersom klassen redan betonar att den hanterar röda, gröna och blåa cellräkningar, känns det onödigt att inkludera "RGB" i metodnamnet.|
| Metodnamn __getCountsColorsRows()__ | __Avoid Disinformation__ - Tidigare hade jag namngett den här metoden "getColorsofCountsRows," och en annan metod i samma klass hade namnet "getColorCountsRows." Det blev mycket svårt att särskilja dem. För att undvika förvirring och felaktig information valde jag att namnge den här metoden om till "getCountsColorsRows," vilket markant underlättade att skilja den från den andra metoden. |
| int __rowIndex__ | ```for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++)``` __Use Intention-Revealing Names__ - Eftersom jag använder radindex på några ställen i denna for-loop, övervägde jag att ge den heltalsvariabel som används i for-loopen ett meningsfullt och avsiktsavslöjande namn istället för att bara använda en enda bokstav som 'i'.|
| | __Use Searchable Names__ - "rowIndex" blir mycket lättare att hitta i koden än "i".|


| Metodnamn och länk eller kod | Antal rader | Reflektion |
| -------- | -------- | -------- |
|[getOneColorCellCountsInOneLine(String[] lineToAnalyse, String color) - från line 55](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/src/nonogram/RedGreenBlueCellCounts.java)| 36 | __Blocks and Indenting__ - "This implies that the blocks within if statements, else statements, while statements, and so on should be one line long. Probably that line should be a function call." Jag tänkte att detta är lite för extremt och att det skulle bli mycket vertikalt skrollande om jag gör så. MEN, eftersom den här metoden har väldigt många if-satser, inser jag nu att det vore mycket bättre om de separerades i egna metoder. På det sättet skulle de ha beskrivande namn och alla kommentarer som finns nu skulle bli onödiga.|
||| __Small!__ - "Functions should hardly ever be 20 lines long." - Om jag applicerar principen som jag nämnde tidigare, "Blocks and Indenting", skulle denna funktion bli mycket mindre. |
|||__Command Query Separation__
CQS är i viss mån uppfylld - Den främsta syftet med denna metod är att beräkna och returnera en lista över färgantal baserat på argument. Men jag kunde separera felhantering i en separat metod.
||| __Prefer Exceptions to Returning Error Codes__ - använder Exception |
|
|[determineRedGreenBlueWhiteCell(int cell, Color red, Color green, Color blue) - från line 131](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/src/nonogram/GridGetter.java)| 17 | __Function Arguments__ - Enligt principerna för "Clean Code" bör man inte ha fyra argument i en metod. Jag har funderat mycket på att inte ta in Color-attributer och istället skapa dem som variabler inuti metoden. I denna metod har jag dock valt att ha fyra argument eftersom denna funktion kommer att anropas för varje cell i ett nonogramspel. Om jag skapar tre färger varje gång funktionen anropas kan det fungera bra för en nonogram-grid med 30 rader och 30 kolumner. Men vad händer om man vill ha en nonogram-grid med 500 rader och 500 kolumner? Då skapas det väldigt många Color-klasser, och i det fallet tycker jag att det är bättre att ta in dem som argument.|
||| __Have No Side Effects__
Metoden har inga sidoeffekter. Den gör bara den sak som är beskriven i namnet. |
|[getBlackCellCountsInOneLine( String[] lineToAnalyse) - från line 56](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/src/nonogram/BlackWhiteCellCounts.java)| 23 |__Common Monadic Forms__ - Metoden tar in en argument, använder den för att få och returnera något annnat. |
||| __Verbs and Keywords__ - Namnet på den här metoden, 'getBlackCellCountsInOneLine,' och dess parameter 'lineToAnalyse,' bildar en mycket bra 'verb/noun pair,' vilket gör koden lättare att förstå.
|
|[getCellCountsForAllRowsOrColumns(ArrayList<ArrayList<Integer>> redCountsinAllRowsOrColumns, ArrayList<ArrayList<Integer>> greenCountsInAllRowsOrColumns; ArrayList<ArrayList<Integer>> blueCountsInAllRowsOrColumns) - från line 98](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/src/nonogram/RedGreenBlueCellCounts.java)| 22 |
__Triads__ - Metod tar in 3 arguments. Vid metodanrop använder jag andra metoder som arguments. Då blir koden väldigt svårt att förstå. Jag kunde istället ha inga argumenter alls och argumenter som jag har nu kunde jag anropa som variabler inom metoden.
__Output Arguments__ -  man bör undvika dem. Alla mina argument är input-argument.
|
|[getColumnCellCountsForOneColor(String color) - från line 16](https://github.com/Vanja-Maric/Nonogram-generator/blob/main/src/nonogram/RedGreenBlueCellCounts.java)|15| __Small!__ - Metoden har färre än 20 rader. Men om man vill att alla metoder ska ha högst 4 rader, då skulle man kunna dela upp de nästlade looparna i två loopar. Första loopen skulle returnera en ArrayList som sedan skulle användas som antingen ett argument eller kallas som en variabel i den andra loopen. Båda dessa for-loopar skulle kunna separeras i egna metoder. |




I boken finns det väldigt många bra principer så länge man använder dem lagom. Till exempel den första principen som heter small! säger att det är bäst om alla metoder är max 4 rader långa. Det är väldigt extremt. Då kommer vi få väldigt många kod rader på slutet och det blir väldigt mycket vertical scrolling. Aldå detta blir lättare om man använder The stepdown rule - Läs kod uppifrån till botten: Steg-ned-regeln: Tänk på hur din kod är organiserad. Kan du enkelt läsa programmet genom att stiga ner genom listan med funktioner, där varje funktion bör följas av de på nästa abstraktionsnivå? Försök att tillämpa denna "Steg-ned-regel" på din kod.
I samma principen (Small) i boken står det också att metoder ska vara max 20 rader och jag anser att det blir mycker rimligare och jag håller helt med.