<details>
  <summary>Inhoud</summary>
  <ol>
    <li>
      <a href="#afvalsorteer-systeem-groep-13">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#voorbereiding">Voorbereiding</a>
      <ul>
        <li><a href="#mysql-installatie">MySQL installatie</a></li>
        <li><a href="#nodejs-npm-installatie">NodeJS/NPM</a></li>
        <li><a href="#java">Java</a></li>
      </ul>
    </li>
    <li>
        <a href="#spring-project">Spring project</a>
        <ul>
            <li><a href="#applications-propperties">Environment</a></li>
        </ul>
    </li>
    <li>
        <a href="#angular-project">Angular project</a>
        <ul>
            <li><a href="#environment">Environment</a></li>
        </ul>
        <ul>
            <li><a href="#npm-install">npm install</a></li>
        </ul>
    </li>
  </ol>
</details>


<!-- Over project -->
## Afvalsorteer systeem Groep 13
Deze readme bestand staat in hoe je het project opzet zodat u het kunt gebruiker zonder enkele problemen!

### Gemaakt met
Dit project is gemaakt met de volgende frameworks

Frontend:
* [![Angular][Angular.io]][Angular-url]

Backend:
* [![Spring][Spring.io]][Spring-url]

<!-- Voorbereiding project -->
## Voorbereiding 
Voordat u het project kan gebruiken, moet u eerst een paar dingen downloaden zodat u gebruik kan maken van Angular en Spring.

### MySQL installatie
MySQL database heeft u nodig zodat u de data van de applicatie kan opslaan en ophalen.

Download mysql van de [website](https://dev.mysql.com/downloads/), selecteer de MySQL versie die u nodig heeft en volg de stappen om het te installeren.

### NodeJS NPM installatie
NodeJS/NPM heeft u nodig om de frontend te kunnen starten.

Ga naar de node [website](https://nodejs.org/en/) en download de LTS versie. Volg vervolgens de stappen om node te isntalleren

### Java
Java heeft u nodig om de backend van de applicatie te runnen.

Ga naar de gegeven [link](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html) en download de juiste java kit voor uw systeem.


<!-- Over spring gedeelte van project -->
## Spring project
Download het project van de github `IPSEN2-BE` die u heeft gekregen.

### Applications propperties

Ga naar het bestand `src/main/resources/application.properties` en plaats dit erin:

```

spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_naam
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
jwt_secret=HASJFHkjlasdfhsduiafh48923yrhishfa789chJKAHLDCUN349yh8hkjshdcv89DJKf


```

Verander de volgende waardes:

- Bij `spring.datasource.url` zie je aan het eind "db_naam", verander dat naar uw eigen db naam
- Bij `spring.datasource.username` moet u uw MySQL gebruikdersnaam invoeren
- Bij `spring.datasource.password` moet u uw MySQL wachtwoord invoeren



<!-- Over angular gedeelte van project -->
## Angular project
Download het project van de github `IPSEN3_Angular` die u heeft gekregen.

### Environment
Ga naar `src/app/environments/environment.ts` en verander de url van de `apiUrl` naar de gewneste url waar de backend op draait

### Npm install
Om het project te starten heeft u het mapje `node_modules` nodig. 

Om dit mapje te krijgen moet u de commando `npm install` uitvoeren.

Wanneer u dit mapje heeft, kunt u de volgende commando's uitvoeren:

`ng serve` -- dit is om het project te starten in developer mode. Dan kunt u dingen in de code aanpassen en word het live veranderd.

`ng build` -- wanneer u de applicatie wilt hosten moet u deze commando uitvoeren. Er word dan een nieuw mapje gemaakt in `dist/AfvalSorteerSysteem`. Hier staan een paar bestanden in die u nodig heeft om de app te hosten. Copieer deze en plaats ze in de server waar het gehost moet worden.


<br>
<br>
<br>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Spring.io]: https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg
[Spring-url]: https://spring.io/

