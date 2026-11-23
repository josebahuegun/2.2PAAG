# 1. saioa: FXMLren sarrera

## 1. Zer da FXML

FXML **Java objektuak sortu eta konfiguratzeko modu bat da, XMLz idatzita**. Orain arte bista Java klase batean eraiki duzue (`new`, `set...`). FXMLrekin, bista bera fitxategi batean deskribatzen da, eta exekuzioan `FXMLLoader` klaseak irakurri eta objektuak sortzen ditu.

Helburua: **diseinua (zer dago non) eta logika (zer gertatzen da) bereiztea**.

- **Alde onak:** bista irakurterrazagoa da, kodetik bereizita dago, eta editore grafiko batekin (Scene Builder) egin daiteke.
- **Alde txarrak:** FXMLa eta Java izenen bidez lotzen dira, eta izen bat gaizki idaztea **exekuzio-errorea** da, ez konpilaziokoa.

## 2. Javatik FXMLra: lau arau

| Araua | Java hutsean | FXMLan |
|---|---|---|
| **Elementua = objektu bat** | `new TextField()` | `<TextField/>` |
| **Atributua = setter bat** | `button.setText("Gorde")` | `<Button text="Gorde"/>` |
| **Elementu umeak = edukiontziaren umeak** | `root.getChildren().add(label)` | `<GridPane> <Label/> </GridPane>` |
| **Atributu "estatikoak" = edukiontziaren murrizketak** | `GridPane.setRowIndex(label, 2)` | `<Label GridPane.rowIndex="2"/>` |

Bi xehetasun:

- **Balio konplexuak** (adibidez, `Insets` bat) etiketa minuskula batekin idazten dira: `<padding><Insets top="20" .../></padding>`. Hori `root.setPadding(new Insets(...))` da.
- **Inportazioak:** erabilitako klase bakoitzak bere inportazioa behar du: `<?import javafx.scene.control.Button?>`.

Kontuz `GridPane`-rekin: Javako `add(nodoa, zutabea, errenkada)` metodoan zutabea lehenik doa. FXMLan `GridPane.columnIndex` eta `GridPane.rowIndex` dira.

## 3. View/Controller-etik FXMLra

| | Orain arte (Java hutsa) | FXMLrekin |
|---|---|---|
| Bista | Java klase bat | `.fxml` fitxategi bat |
| Nork sortzen du kontroladorea | Zuek: `new Controller(...)` | `FXMLLoader`-ek, `fx:controller` irakurrita |
| Nola lortzen ditu kontroladoreak nodoak | Bistaren getter-ekin | **Injekzioa:** `FXMLLoader`-ek eremuak betetzen ditu (`@FXML`) |
| Nola lotzen dira gertaerak | Kontroladoreak: `setOnAction(...)` | FXMLak: `onAction="#gorde"` |

Bistak (FXMLak) badaki zein den bere kontroladorea. Kontroladoreak ez du bista sortzen: nodoak jasotzen ditu.

## 4. Zer egiten du `FXMLLoader.load()` metodoak

Ordena honetan:

1. FXML fitxategia irakurri eta nodo guztiak sortzen ditu.
2. `fx:controller` badago, kontroladorea sortzen du, **argumenturik gabeko eraikitzailearekin**.
3. `fx:id` bakoitzeko, izen bereko `@FXML` eremua betetzen du.
4. `onAction="#gorde"` bezalako atributuak kontroladoreko metodoei lotzen dizkie.
5. Kontroladoreak `initialize()` metodoa badu, orduan deitzen dio.
6. Erro-nodoa itzultzen du.

Ondorioa: **eraikitzailean `@FXML` eremuak oraindik `null` dira**. Hasierako konfigurazioa `initialize()`-n egiten da.

## 5. Lotura-arauak

- **`fx:controller="pakete.osoa.Klasea"`**: erro-elementuan bakarrik. FXML bakoitzak kontroladore bakarra du.
- **`fx:id="izenburuaField"`** ↔ **`@FXML private TextField izenburuaField;`**: izenak berdin-berdinak (maiuskulak barne), eta mota bateragarria.
- **`@FXML`** behar da eremua edo metodoa `private` bada. Beti `private` + `@FXML` erabiliko dugu.
- **`onAction="#gorde"`**: `#` ikurrak "kontroladoreko metodoa" esan nahi du. Metodoak ez du argumenturik, edo `ActionEvent` bat jasotzen du.
- **`initialize()`**: aukerakoa, argumenturik gabe, eta `@FXML`-ekin `private` bada.

## 6. FXMLa kargatzea

```java
FXMLLoader loader = new FXMLLoader(App.class.getResource("liburu-form.fxml"));
Parent root = loader.load();
```

- **Bide erlatiboa** (`/` gabe): `App` klasearen paketearekiko bilatzen da → `src/main/resources/eus/liburutegia/liburu-form.fxml`.
- **Bide absolutua** (`/` hasieran): `resources` karpetaren errotik → `"/eus/liburutegia/liburu-form.fxml"`.
- Mavenen: `.java` fitxategiak `src/main/java`-n, eta `.fxml` fitxategiak `src/main/resources`-en, **pakete-egitura berarekin**.

## 7. `module-info.java`: `opens`

`FXMLLoader`-ek isla (*reflection*) erabiltzen du eremu eta metodo pribatuetara sartzeko. Moduluek hori debekatzen dute, paketea irekitzen ez bada:

```java
opens eus.liburutegia.controller to javafx.fxml;
```

Lerro hori paketea sortu ondoren gehitu: pakete hutsa irekitzeak konpilazio-errorea ematen du.

## 8. Ohiko akatsak

| Sintoma | Kausa |
|---|---|
| `IllegalStateException: Location is not set` | FXMLaren bidea okerra, edo fitxategia ez dago `resources`-en pakete egokian |
| `LoadException: TextField is not a valid type` | `<?import?>` falta da |
| `NullPointerException` kontroladorean | `fx:id` eta eremuaren izena ez datoz bat, `@FXML` falta da, edo eremua eraikitzailean erabili da |
| `LoadException: Error resolving onAction='#gorde'` | Metodoaren izena ez dator bat, edo `@FXML` falta da |
| `IllegalAccessException` | `opens` falta da `module-info.java`-n |
| `ClassNotFoundException` | `fx:controller`-eko izena ez da osoa edo gaizki idatzita dago |

Errore bat agertzen denean, irakurri mezua osorik, `Caused by:` lerroraino. Gehienetan, izen bat ez dator bat.
