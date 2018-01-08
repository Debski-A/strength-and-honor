# strength-and-honor

<h1><strong>Projekt showcase by Adam Dębski</strong></h1>
<h2>Opis projektu</h2>

Prowadzony projekt ma na celu doskonalenie umiejętności związanych z programowaniem webowym. Głównym narzędziem jest framework <strong>Spring MVC 4</strong>.
Pozostałe technologie to:
<ul>
  <li>Spring Security 4</li>
  <li>Hibernate 5</li>
  <li>baza danych MySql</li>
  <li>Thymeleaf 3</li>
  <li>Bootstrap 3</li>
  <li>junit, hamcrest, mockito</li>
  <li>Hikari Cp</li>
  <li>Log4j2</li>
</ul>
<p>Projekt jest aplikacją webową z możliwością prowadzenia własnego konta. W przyszłości Użytkownicy będą mieli do wykorzystania kalkulatory do 
obliczania prawidłowej masy ciała etc. Będzie dodana również możliwość publikowania własnych treści. Obecnie aplikacja obsługuje 2 języki - polski
i angielski. Rejestracja odbywa się poprzez wysyłanie linka (który jest adresem url z unikalnym tokenem) na adres mailowy podany przez Użytkownika.
Następnie Użytkownik klika na adres url, który otrzymał na adres email (sprawdzane jest czy w bazie istnieje Użytkownik z takim tokenem)
i przechodzi do ustalania hasła do konta. Jeśli wszystko się zgadza token jest 'nullowany' hasło szyfrowane a Użytkownik zapisywany 
do bazy danych. Po zalogowaniu Użytkownik ma możliwość uzupełnienia swoich danych w zakładce Profil znajdującej sie w menu nawigacyjnym.
Wszystkie czynności związane z rejestracją, logowaniem i zmianą danych Użytkownika są odpowiednio walidowane i obsługują i18n (przy pomocy messages.properties).</p>

Diagram modelu bazy danych:
![sah_db_diagram](https://user-images.githubusercontent.com/20265160/34687634-ac6eccd0-f4af-11e7-93dd-2c05c0a86734.png)
Jak widać na załączonym diagramie tabela users jest połączona relacją 1:1 z tabelą users_details, a ich klucze główne są zależne od siebie
tzn. users_details wykorzystuje klucz główny tabeli users (zatem encja users_details jest zależna od encji users i nie może bez niej istnieć). 

<h2>Jak uruchomić projekt na serwerze lokalnym?</h2>
