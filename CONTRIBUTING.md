# Contributing Guidelines
bitte einmal komplett lesen :)

## Generelle Konventionen
zum erhöhen der Lesbarkeit
- Nach ~80 Spalten einen Zeilenumbruch verwenden
- keine Tabs nur Leerzeichen verwenden!
- Mindestens 2 Leerzeichen zum einrücken verwenden (besser 4)

## Bezeichnungen
|Typ | Regel | Beispiel |
|----|:-----:|----------|
| Klassen | Name sollte ein Nomen sein. Das erste Wort muss mit einem Großbuchstaben beginnen | class ImageSprite; | 
| Interfaces | Name muss mit Großbuchstaben beginnen | interface RasterDelegate; | 
| Methoden | Name sollte ein Verb sein und mit einem Kleinbuchstaben beginnen | runFast(); |
| Variablen | möglichst aussagekräftige Namen verwenden. Mit Kleinbuchstaben beginnen | int screenWidth; |
| Konstanten | Nur Großbuchstaben, Wörte sind mit Underscore ("_") zu trennen | static final int SCREEN_WIDTH; |
| Packages | nur Kleinbuchstaben| com.github.lhrb.sinnvollerpaketpfad

## Kommentare
Jede Klasse/Methode <b>muss</b> kommentiert werden!

Im Kommentarblock über der Klasse/Methode muss angegeben, 
welche funktionalität zur Verfügung gestellt wird. 
Also "<b>Was</b> macht die Klasse/Methode". 
Das "Wie" kann, falls notwendig, mit inline Kommentaren beschrieben werden.
```java
/**
 * Angeben was die Methode macht
 * @param x beschreibtung 
 * @return beschreibung
 * @throws Exception beschreibung
*/
public int beispiel(int x) throws Exception{
  //inline Kommentare können hilfreich sein
  return 1;
}
```
Es gibt noch viel mehr [javadoc tags](https://www.tutorialspoint.com/java/java_documentation.htm)

## Test Klassen
- Zu jeder Klasse muss eine entsprechende JUnit Test Klasse angelegt werden!
- Jede Methode braucht eine entsprechende Test Methode!
- Es sollte mindestens ein Test Case dargestellt werden 
# Git Tutorial

- Download & Install git
- Erstelle eine account auf [github](https://github.com)

#### User Config
Run
```
git config --global user.name "Your Name"
git config --global user.email "your_email@whatever.com"
```

#### Clone Repository
```
git clone https://github.com/lhrb/swec8.git
```

### Wichtig nicht im master branch arbeiten

#### Erstelle einen Feature Branch

```
git checkout -b feature_name
```
Bitte den Präfix "feature" benutzen

#### Add
nach der Bearbeitung einer Datei kann man diese mittels
```
git add dateiname
```
dem Index hinzufügen.

#### Commit
Die Datei zum HEAD hinzufügen
```
git commit -m "commit message"
```
- Präfixe wie "Add, Delete, Update, Create" benutzen!
- Versuchen jede Änderung, wenn möglich, in einen einzelnen Commit
- Einen mehrzeiligen Commit erstellt man, indem man die Anführungzeichen nicht schließt.
```
git commit -m "Add ....
Create ...
Update ... "
```
#### Push
Die Dateien zum Repository senden
```
git push origin branch_name
```
branch_name ist der name eures feature branch ;)


#### Zum weiterlesen
- (https://rogerdudler.github.io/git-guide/)
- (https://githowto.com/)
- (https://git-scm.com/doc)


