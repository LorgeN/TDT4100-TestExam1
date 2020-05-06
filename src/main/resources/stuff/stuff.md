# Småplukk

## 1 - Iterasjon med iteratorer

Disse oppgavene dreier seg om ulik bruk av iteratorer. Med mindre noe annet er sagt, så skal klassen(e) implementeres.

### 1.1 - for-each, Iterable og Iterator

Forklar med tekst og kode sammenhengen mellom for-each-syntaksen, altså den på formen `for (<type> <var> : <uttrykk>) ...`, og **Iterable**- og **Iterator**-grensesnittene.

Skriv svaret i [svar.md](../../../../svar.md)-fila.

### 1.2 - StringBuilderCharacterIterator

[StringBuilderCharacterIterator](StringBuilderCharacterIterator.java)-klassen skal implementere en iterator av tegnene (Character) inni en `StringBuilder`. Klassen `StringBuilder` er en `String`-aktig klasse med den (viktige) forskjellen at instanser kan endres etter opprettelsen. Klassen testes av StringBuilderCharacterIteratorTest.

- Implementer StringBuilderCharacterIterator.

### 1.3 - StringBooleanMetaIterator

Klassen [StringBooleanMetaIterator](StringBooleanMetaIterator.java) er en såkalt meta-iterator, ved at den bruker andre iteratorer som sin kilde av elementer. Akkurat denne meta-iteratoren tar inn to andre iteratorer, hvilke elementer i den første som skal videreformidles eller filtreres bort styres av den andre. Den første itererer over strenger, den andre itererer over boolske verdier. De to iteratorene kan antas å være like lange.

Eksempel: Hvis den første iteratoren gir ut sekvensen `"meta-iteratorer", "er", "ikke", "kult"` og den andre gir ut `true, true, false, true` så skal StringBooleanMetaIterator-instansen gi ut `"meta-iteratorer", "er", "kult"`. Det tredje elementet (`"ikke"`) ble altså utelatt siden den tredje logiske verdien var `false`.

`main`-metoden er allerede påbegynt med dette som eksempel, bruk den til å teste din egen kode.

Klassen testes av StringBooleanMetaIteratorTest.

- Implementer StringBooleanMetaIterator.
- Du kan forvente at de to sekvensene (strenger og boolske verdier) er like lange.

### 1.4 - EverySecondMetaIterator og EverySecondMetaIteratorTest

[EverySecondMetaIterator](EverySecondMetaIterator.java) er også en meta-iterator. Den tar inn en annen iterator og slipper gjennom  _annenhvert_  element.

Eksempel: Hvis iterator-argumentet gir ut `"1", "2", "3", "4"`, så skal EverySecondIterator-instansen selv gi ut `"1", "3"`. Dette er testet i både `main`-metoden og [EverySecondMetaIteratorTest](EverySecondMetaIteratorTest.java)-testklassen.

Klassen er "ferdig"-implementert i den forstand at den virker for et eksempel-tilfelle. Den har imidlertid en  _fundamental_  feil som gjør at den strengt tatt ikke virker riktig for eksemplet, det er bare at testen ikke er grundig nok. Implementasjonen gjør også andre feil som gjør at den kræsjer for andre tilfeller.

Din oppgave er skrive to testmetoder som identifiserer begge feilene (ved at testmetoden utløser unntak på vanlig testmetodevis): 

- En testmetode skal identifiserer et annet tilfelle som implementasjonen kræsjer på.
- En annen testmetoden skal identifisere den fundamentale feilen.

Du skal altså  _ikke_  rette feilene, men skrive gode testmetoder for å identifisere dem.

## 2 - Funksjonelle grensesnitt og lambda

Her er oppgaven å skrive de manglende metodene i [LambdaUtilities](LambdaUtilities.java) iht. det som står i javadoc-en over metodene. Metodesignaturene (parameter- og retur-typer) skal  _ikke_  endres.

- Implementer metodene beskrevet i [LambdaUtilities](LambdaUtilities.java). 
- Over hver metode i kildekoden finner dere en beskriver forventet oppførsel. 

`main`-metoden inneholder eksempler må bruk av metodene. (Vi har ikke laget noen testklasse for dere til denne oppgaven.)