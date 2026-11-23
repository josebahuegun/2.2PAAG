# 2. saioa: bilaketa-leihoa Scene Builderrekin

**Helburua:** leiho bat Scene Builderrekin diseinatzea, kontroladore batekin lotzea eta CSS bidez estiloa ematea.

Proiektu hau 1. saioko soluzioa da. Jarraitu bere gainean.

## Lortu beharreko leihoa

```
┌──────────────────────────────────────────────────┐
│  Liburuak bilatu                                 │   ← top
│                                                  │
│  Testua:             [______________________]    │
│  Generoa:            [Guztiak          ▼]        │
│  Urtea (gutxienez):  ───────●──────────  2000    │   ← center (GridPane)
│  Formatua:           (●) Paperean  ( ) Digitala  │
│                      [ ] Eskuragarri daudenak    │
│                                                  │
│                           [Garbitu]  [Bilatu]    │   ← bottom
│  Bilatzen: "..." | Eleberria | 2000-tik ...      │
└──────────────────────────────────────────────────┘
```

## Urratsak

**1. Ezagutu Scene Builder (10')**
- Ireki `liburu-form.fxml` Scene Builderren. Aldatu botoiaren testua eta gorde.
- Ireki fitxategia IntelliJen: zer aldatu da XMLan?

**2. Diseinatu leihoa (35')**
- Sortu `bilaketa.fxml` fitxategi berria `resources/eus/liburutegia/` karpetan. Erroa: `BorderPane`.
- Kokatu osagaiak marrazkiaren arabera.
- Lotu bi `RadioButton`-ak `ToggleGroup` berean (*Properties → Toggle Group*).
- Eman `fx:id` bat kontroladoreak erabiliko dituen osagaiei (*Code* atala).

**3. Kontroladorea (30')**
- Scene Builderren, *Controller* atalean, idatzi `eus.liburutegia.controller.BilaketaController`.
- *View → Show Sample Controller Skeleton*: kopiatu eta sortu klasea IntelliJen.
- `initialize()` metodoan:
  - Bete ComboBox-a generoekin ("Guztiak", "Eleberria", "Poesia"...).
  - Erakutsi Slider-aren balioa ondoko etiketan, eta eguneratu mugitzean.
- **Bilatu:** erakutsi aukeratutako irizpide guztiak beheko etiketan.
- **Garbitu:** itzuli dena hasierako egoerara.
- Aldatu `App` klasean kargatzen den FXMLa (`bilaketa.fxml`).

**4. CSSa (20')**
- Sortu `estiloa.css` FXMLaren karpeta berean eta lotu Scene Builderren (*Properties → Stylesheets*).
- Gutxienez: atzeko kolorea, izenburu handiagoa, "Bilatu" botoi nabarmendua (`styleClass`) eta mezuaren kolorea (`#id`).

**Azkarrentzat**
- Testu-eremuan Enter sakatzean ere bilatu dadila.
- "Bilatu" botoiak kolorea alda dezala sagua gainean dagoenean (`:hover`).
