# Svar på teorispørsmål

Her besvares teorispørsmål. Hvis du bruker Eclipse, så velg **Markdown Source** nederst til venstre, for å redigere. Du trenger ikke tenke på formattering, men vi har brukt markdown for å lage overskriften.

### 1.1 - for-each, Iterable og Iterator
I Java har vi et interface som heter Iterable. Dette forteller oss at et objekt inneholder en mengde av noe, hvor det er mulig å iterere over denne mengden. Dette gjøres ved at et objekt som implementerer Iterable må kunne gi oss en Iterator som itererer over disse objektene. 
```java
public interface Iterable<T> {
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    Iterator<T> iterator();

    // .. snipped ...
}
```
En iterator er et objekt som er ansvarlig for å holde styr på hvor vi er i en mengde med objekter når vi itererer over dem, og har metoder for å gi oss det neste objektet i iterasjonen, og for å sjekke om det finnes et neste objekt. Noen iteratorer har også støtte for "remove"-metoden, som lar oss fjerne et objekt fra en mengde mens vi itererer. Dette er ikke mulig med f. eks. en vanlig enhanced for loop (for-each syntaks), og vil utløse en concurrent modification exception siden vi endrer på listen samtidig som vi itererer over den. #forEach er en nyere implementasjon i Java, og lar oss ta nytte av lambda funksjoner og enkle Consumer interfaces for å utføre en operasjon for alle elementene i f. eks. en collection. Iterator har også en #forEachRemaining, som fungerer på lignende måte som Iterable#forEach, hvor den da kjører den gitte funksjonen på de resterende elementene.

Et objekt som implementerer Iterable gir også støtte for den såkalte enhanced for-loop syntaksen, eller for-each syntaksen som omtalt her.
```java
for (T obj : someIterable) {
     System.out.println(obj);
}
```
Dette er en forenkling av koden under gjort av Java. Disse to snippetsene gjør akkurat det samme, men det er mye lettere å lese og forstå det over enn det under. I tillegg er det enklere å skrive, krever mindre variabler og gir en generelt ryddigere opplevelse av koden som er viktig dersom noen andre skal jobbe på den senere.
```java
Iterator<T> iterator = someIterable.iterator();
while (iterator.hasNext()) {
    T obj = iterator.next();
    System.out.println(obj);
}
```