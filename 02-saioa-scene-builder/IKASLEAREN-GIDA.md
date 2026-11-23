# 2. saioa: Scene Builder

## 1. Zer da Scene Builder

FXML fitxategiak sagu bidez editatzeko programa bat da. **Ez du Java koderik sortzen: FXMLa bakarrik.** Atzo eskuz idatzi zenuten XML bera sortzen du. Gordetzean, fitxategia osorik berridazten du, eta eskuz idatzitako iruzkinak gal daitezke.

Lau gune ditu:

- **Library** (ezkerrean, goian): osagaiak. Arrastatu eta jaregin.
- **Hierarchy** (ezkerrean, behean): nodoen zuhaitza. Hemen errazagoa da nodoak hautatzea eta mugitzea.
- **Inspector** (eskuinean), hiru fitxa:
  - *Properties*: testua, CSSa...
  - *Layout*: tamaina, marjinak, GridPaneko errenkada eta zutabea...
  - *Code*: `fx:id` eta gertaerak (`On Action`...). **Hemen lotzen da FXMLa kontroladorearekin.**
- **Controller** (ezkerrean, behean): kontroladorearen klasea (`fx:controller`).

Trikimailua: ***View → Show Sample Controller Skeleton***. Kontroladorearen eskeletoa sortzen du, `@FXML` eremu guztiekin. Kopiatu eta itsatsi, eta izen-akatsak saihestuko dituzu.

## 2. Edukiontziak (layout-ak)

- **BorderPane:** bost gune (top, bottom, left, right, center). Leihoaren egitura orokorrerako.
- **GridPane:** errenkadak eta zutabeak. Formularioetarako.
- **HBox / VBox:** osagaiak lerro batean, horizontalean edo bertikalean.

## 3. Osagai berriak

| Osagaia | Zertarako | Zer du berezia |
|---|---|---|
| `ComboBox` | Zerrenda bateko aukera bat | Aukerak `initialize()`-n betetzen dira, kodez |
| `Slider` | Zenbaki bat tarte batean | `min`, `max`, `value` FXMLan; balioaren aldaketa listener batekin |
| `RadioButton` + `ToggleGroup` | Aukera bakarra hainbatetatik | Talde berean egon behar dute; bestela, biak aukeratu daitezke |
| `CheckBox` | Bai/ez | `isSelected()` |

## 4. `initialize()`: zertarako

FXMLLoader-ek automatikoki deitzen dio, `@FXML` eremu guztiak bete ondoren. **FXMLan idatzi ezin dena** egiteko erabiltzen da. Adibidez, ComboBox bat betetzeko:

```java
@FXML
private void initialize() {
    generoaCombo.getItems().addAll("Guztiak", "Eleberria", "Poesia");
    generoaCombo.getSelectionModel().selectFirst();
}
```

## 5. Gertaerak: bi modu

**a) FXMLko atributuak**, osagaiak zerbait *egiten* duenean. Scene Builderren: *Code → On Action*.

```xml
<Button text="Bilatu" onAction="#bilatu"/>
<TextField onAction="#bilatu"/>   <!-- TextField batean: Enter sakatzean -->
```

Beste batzuk: `onMouseClicked`, `onKeyReleased`...

**b) Listener bat `initialize()`-n**, osagaiaren *balio bat aldatzen* denean. Slider-ak ez du "balioa aldatu da" atributurik:

```java
urteaSlider.valueProperty().addListener((obs, zaharra, berria) ->
        urteaLabel.setText(String.valueOf(berria.intValue())));
```

Oraingoz, errezeta gisa hartu: "balio bat aldatzean zerbait egin nahi baduzu, honela". Aurrerago ikusiko dugu zergatik funtzionatzen duen.

## 6. CSSa

Web-eko CSSaren antzekoa da, bi desberdintasunekin: propietateek `-fx-` aurrizkia dute, eta fitxategia FXMLari lotzen zaio:

```xml
<stylesheets>
    <URL value="@estiloa.css"/>   <!-- @ = FXMLaren karpeta berean -->
</stylesheets>
```

**Ideia nagusia: kontrolek klase bat dute automatikoki; edukiontziek ez, eta zuek jarri behar diezue.**

Kontrol bakoitzak estilo-klase bat du lehenetsita: `Button` → `.button`, `Label` → `.label`, `TextField` → `.text-field`, `ComboBox` → `.combo-box`, `CheckBox` → `.check-box`... Edukiontziek (`BorderPane`, `GridPane`, `HBox`, `VBox`) ez dute.

| CSSan | FXMLan | Zeri aplikatzen zaio |
|---|---|---|
| `.button` | Ezer ez: kontrolaren klase lehenetsia da | Mota horretako kontrol guztiei |
| `.nagusia` | `styleClass="nagusia"` (puntu gabe!) | Klase hori dutenei. Edukiontzi bati estiloa emateko modu ohikoena |
| `#mezuaLabel` | `fx:id="mezuaLabel"` | Osagai bakar horri |

Kasu bereziak:
- **`.root`**: eszenaren erro-nodoari automatikoki ematen zaio, edozein motatakoa izanda ere.
- **`:hover`** sasi-klasea: sagua gainean dagoenerako.
- **Kontuz:** `button` (puntu gabe, web-eko moduan) ez da JavaFXko bidea.

## 7. Ohiko akatsak

| Sintoma | Kausa |
|---|---|
| Scene Builderren gordetakoa ez da agertzen exekutatzean | IntelliJek ez du fitxategia berriz kargatu: *File → Reload All from Disk* |
| ComboBox-a hutsik | `initialize()`-k ez du `@FXML` edo izena gaizki dago, eta ez zaio deitzen |
| Bi RadioButton-ak aukeratu daitezke | Ez daude `ToggleGroup` berean |
| `NullPointerException` `getSelectedToggle()` deitzean | Ez dago aukerarik hautatuta: jarri `selected="true"` lehenengoari |
| CSSa ez da aplikatzen | Fitxategia ez dago FXMLaren karpeta berean, edo `-fx-` aurrizkia falta da |
| `.nagusia` ez da aplikatzen | FXMLan `styleClass=".nagusia"` idatzi da, puntuarekin |
| Scene Builderrek ez du kontroladorearen klasea aurkitzen | Normala da: idatzi izen osoa eskuz |
