# strength-and-honor

<h1><strong>Projekt sandbox do zabawy, poszerzania wiedzy i szlifowania skilli</strong></h1>
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
Aplikacja łączy się pod spodem z bazą danych MySQL przy pomocy Hibernate. W bazie zapisywane są dane zarejestrowanych użytkowników. Po uzupełnieniu wymaganych danych aplikacja umożliwia użytkownikowi obliczenie wskaźników BMR oraz BMI. Odbywa się to poprzez połączenie z  RestAPI wystawionym z aplikacji https://github.com/Debski-A/BmrBmiWebService. Aplikacją łączy się również z OpenWeatherMap (https://openweathermap.org/api) i pobiera pomiar temperatury z obszaru określonego przy pomocy miasta.

Warstwa widoku bazuje na Thymeleaf, Bootstrapie i drobnym customowym JS.
Aplikacja obłożona jest testami, a do testów warstwy DAO korzysta z wygenerowanej bazy typu in-memory - H2.

Pomysły na przyszłość:
1. zDockerować projekt.
2. Wystawić na świat
3. Dodać jakieś nowe ciekawe funkcjonalności
4. Zrobić podział aplikacji, tzn. wystawić RESTowe API zamiast warstwy widoku z wykorzystaniem Thymeleaf
5. Zaimplementować frontend z użyciem Angulara lub Reacta lub jeszcze czegoś innego??
6. Zaimplementować interfejs Androidowy, który łączy się z backendowym API
