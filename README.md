# strength-and-honor

<h2>Opis projektu</h2>

Stack technologiczny:
<ul>
  <li>Spring MVC 5</li>
  <li>Spring Security 5</li>
  <li>Hibernate 5</li>
  <li>serwer bazy danych MySql</li>
  <li>Thymeleaf 3</li>
  <li>Bootstrap 3</li>
  <li>junit, hamcrest, mockito</li>
  <li>Hikari Cp</li>
  <li>Log4j2</li>
  <li>embedded Tomcat 8</li>
</ul>
<p>Aplikacja webowa w architekturze MVC z wbudowanym Tomcat'em i możliwościa skompilowania do "big fat jar"</p>
Aplikacja łączy się pod spodem z bazą danych MySQL przy pomocy Hibernate. W bazie zapisywane są dane zarejestrowanych użytkowników. Po uzupełnieniu wymaganych danych aplikacja umożliwia użytkownikowi obliczenie wskaźników BMR oraz BMI. Odbywa się to poprzez połączenie z  SOAP API wystawionym z aplikacji https://github.com/Debski-A/BmrBmiWebService. Aplikacją łączy się również z OpenWeatherMap (https://openweathermap.org/api) i pobiera pomiar temperatury z obszaru określonego przy pomocy miasta.

Obecnie aplikacja obsługuje 2 języki - polski i angielski. Rejestracja odbywa się poprzez wysyłanie linka (który jest adresem url z unikalnym tokenem) na adres mailowy podany przez Użytkownika. Następnie Użytkownik klika na adres url, który otrzymał na adres skrzynki pocztowej (sprawdzane jest czy w bazie istnieje Użytkownik z takim tokenem) i przechodzi do ustalania hasła do konta. Jeśli wszystko się zgadza token jest 'nullowany' hasło szyfrowane a Użytkownik zapisywany do bazy danych. Po zalogowaniu Użytkownik ma możliwość uzupełnienia swoich danych w zakładce Profil znajdującej sie w menu nawigacyjnym. Wszystkie czynności związane z rejestracją, logowaniem i zmianą danych Użytkownika są odpowiednio walidowane i obsługują i18n (przy pomocy messages.properties).

Diagram modelu bazy danych wykorzystywanej w aplikacji: 
![sah_db_diagram](https://user-images.githubusercontent.com/20265160/34764530-1c8bb542-f5ef-11e7-9dea-78ca59d6d1b3.png)

Aplikacja podzielona jest na trzy warstwy - zgodnie z koncepcją Model View Controller. Warstwa Model dzieli się na pakiety Entities, Services i Daos. Klasy w pakiecie Entities mapowane są na relacje bazy danych przy pomocy Hibernate (technologia odpowiada również za walidacje niektórych pól).Klasy z pakietu Daos również wykorzystują Hibernate - korzystają z sesji, hql oraz mechanizmów do tworzenia query. Projekt posiada kilka kontekstów konfiguracyjnych w celu zwiększenia przejrzystości. Np. kontekst security-context.xml umożliwia konfiguracje komponentów Spring Security. Ponadto umożliwia to uruchamianie wyszczególnionych kontekstów podczas testów automatycznych (co przyspiesza przebieg tych testów).

Obsługa wyjątków podzielona jest na trzy poziomy. Wyjątki rzucane przez klasy Hibernate wyłapywane są w wartswie Daos i następnie przekazywane do Services jako RepositroyException. Te z kolei wyłapywane są w warstwie Services i przekazywane dalej jako ServicesException. Zaimplementowano taki podział z uwagi na możliwość wykorzystywania aspektów.

W warstwie widoku aplikacja korzysta z frameworka Thymeleaf. Umożliwia to m.in. podział plików html na fragmenty oraz niezależne przygotowywanie ich bez konieczności działania aplikacji na serwerze. Jednak gdy aplikacja jest uruchomiona na serverze to Thymeleaf pozwala na przetwarzanie atrybutów z przedrostkiem 'th:'. Np. 'th:replace' pozwala na podmiane części pliku na któryś z wcześniej przygotowanych fragmentów. Do plików html 'podpięty' jest również framework Bootstrap. Upraszcza on tworzenie front-endu i pozwala wykorzystywać przygotowane szablony kodu css i javascript.

Do logowania wykorzystano bibliotekę Log4j2. Logi wypisywane są na konsoli oraz zapisywane są do pliku app.log.

<h2>Dodatkowe informacje na temat projektu: </h2>
<ul>
  <li>Aplikacja korzysta z CharacterEncodingFilter. Ustawione kodowanie to UTF-8.</li>
  <li>Wykorzystywana jest springowa klasa JavaMailSenderImpl.</li>
  <li>Aplikacja korzysta z H2 in-memory database do przeprowadzania testów integracyjnych warstwy Dao. Buduje bazę danych na podstawie adnotacji w klasach encji i następnie wykonuje skrypt import.sql, który wypełnia bazę podstawowymi danymi.</li>
</ul>

Pomysły na przyszłość:
1. zDockerować projekt.
2. Wystawić na świat
3. Dodać jakieś nowe ciekawe funkcjonalności
4. Zrobić podział aplikacji, tzn. wystawić RESTowe API zamiast warstwy widoku z wykorzystaniem Thymeleaf
5. Zaimplementować frontend z użyciem Angulara lub Reacta lub jeszcze czegoś innego??
6. Zaimplementować interfejs Androidowy, który łączy się z backendowym API
