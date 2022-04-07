## Logbog

**Mandag den 5. april 2022**: \
Sammen - Setup af startkode til github repo og første version af database samt E/R diagram.

**Tirsdag den 6. april 2022**: \
Sammen - Mockup i figma og begyndelse på java kode i form af entities på baggrund E/R diagram for databasen.

**Onsdag den 7. april 2022**: \
Michael og Nicolai - Basic test setup for IDataMapper og alle der implementer det. Lidt JSP og start på UserMapper.

**Torsdag den 8. april 2022**: \
Michael og Nicolai - Standard tests kørt og UserMapper og Cupcake...Mapper næsten færdig, OrderMapper færdig senere i dag.

## Baggrund
Vi har landet en vigtig opgave fra Olsker Cupcakes. Det er endnu et dybdeøkologisk iværksættereventyr fra Bornholm, som har ramt den helt rigtige opskrift. Et par hipstere fra København har været forbi bageriet, og indsamlet nogle krav og lavet en halvfærdig mockup af en tænkt forside. En mockup er en meget løs skitse, som viser hvordan det færdige website skal se ud. Det er selvfølgelig ikke alt som er med, og som er tænkt igennem, så det er vores opgave at stille spørgsmål til manglende funktionalitet, komme med forslag osv.

Når man som leverandør skal løse sådan en opgave, er det godt at dele opgaven op i små etaper. Så tænk fra starten over hvor lidt vi kan lave for at få den første prototype i luften. Med andre ord: vi skal ikke lade os forblænde af hipsternes farver og striber og mange funktionaliteter. Vi æder elefanten lidt ad gangen.

## Userstories (funktionelle krav)
Det første kundemøde mundede ud i en række såkaldte user-stories. De beskriver på kort form hvilke brugere, som har hvilke behov og hvad de ønsker at opnå. Det kan godt være at der dukker flere user-stories op undervejs i processen, eller I vælger at stryge nogle af dem:

US-1: Som kunde kan jeg bestille og betale cupcakes med en valgfri bund og top, sådan at jeg senere kan køre forbi butikken i Olsker og hente min ordre.

US-2 Som kunde kan jeg oprette en konto/profil for at kunne betale og gemme en en ordre.

US-3: Som administrator kan jeg indsætte beløb på en kundes konto direkte i MySql, så en kunde kan betale for sine ordrer.

US-4: Som kunde kan jeg se mine valgte ordrelinier i en indkøbskurv, så jeg kan se den samlede pris.

US-5: Som kunde eller administrator kan jeg logge på systemet med email og kodeord. Når jeg er logget på, skal jeg kunne se min email på hver side (evt. i topmenuen, som vist på mockup’en).

US-6: Som administrator kan jeg se alle ordrer i systemet, så jeg kan se hvad der er blevet bestilt.

US-7: Som administrator kan jeg se alle kunder i systemet og deres ordrer, sådan at jeg kan følge op på ordrer og holde styr på mine kunder.

US-8: Som kunde kan jeg fjerne en ordre fra min indkøbskurv, så jeg kan justere min ordre.

US-9: Som administrator kan jeg fjerne en ordre, så systemet ikke kommer til at indeholde udgyldige ordrer. F.eks. hvis kunden aldrig har betalt.

## Ikke-funktionelle krav
1. Der laves en mockup i Draw.io eller lignende, som viser de websider den færdige løsning kommer til at bestå af.
2. Ordrer, kunder og øvrige data skal gemmes i en database.
3. Databasen skal normaliseres på 3. normalform med mindre andet giver bedre mening.
4. Kildekoden skal deles på GitHub.
5. Det færdige produkt skal udvikles i Java, MySql, HTML, CSS, Twitter Bootstrap og køre på en Tomcat webcontainer.
6. Løsningen skal udvikles med udgangspunkt i vores startkode.

## Deliveries (det som skal leveres)
Et EER diagram over databasen.
Et kørende website, som løser Olsker Cupcakes behov. Vi har ikke lavet en virtuel maskine i skyen til at hoste vores website på nettet, så det er fint nok at I kan vise løsningen frem på jeres egen laptop.
Hver user story implementeres en ad gangen. Kunden vil som minimum se en løsning på de første seks.
Når de første 6 user-stories er implementeret, tager I resten en ad gangen og ser hvor langt I når.
Der løber flere krav på senere på måneden i form af en lille rapport og anden teknisk dokumentation. Det optimale ville være hvis I er færdige med at kode webshoppen til påske og så bruger et par dage efter påske på dokumentationen. Der indkaldes til briefing på rapporten tirsdag den 19. april kl. 09.00 - 11.00.
I skal også indsætte et link i rapporten til en kort video-demo af jeres færdige løsning. Brug et screencast værktøj og sørg for at videoen ikke varer længere end 2-3 minutter. I kan fx bruge https://screencast-o-matic.com/screen-recorder eller optagefunktionen i Zoom. Videoen kan I uploade til YouTube på et skjult link eller lignende.

## MVC arkitektur

![MVC arkitektur](documentation/mvc.jpg)