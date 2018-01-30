# strength-and-honor

<h1><strong>Projekt showcase by Adam Dębski</strong></h1>
<h2>Opis projektu</h2>

Prowadzony projekt ma na celu doskonalenie umiejętności związanych z programowaniem webowym. Głównym narzędziem jest framework <strong>Spring MVC 4</strong>.
Pozostałe technologie to:
<ul>
  <li>Spring Security 4</li>
  <li>Hibernate 5</li>
  <li>serwer bazy danych MySql</li>
  <li>Thymeleaf 3</li>
  <li>Bootstrap 3</li>
  <li>junit, hamcrest, mockito</li>
  <li>Hikari Cp</li>
  <li>Log4j2</li>
</ul>
<p>Projekt jest aplikacją webową z możliwością prowadzenia własnego konta. W przyszłości Użytkownicy będą mieli do wykorzystania kalkulatory do 
obliczania prawidłowej masy ciała etc. Będzie dodana również możliwość publikowania własnych treści. Obecnie aplikacja obsługuje 2 języki - polski
i angielski. Rejestracja odbywa się poprzez wysyłanie linka (który jest adresem url z unikalnym tokenem) na adres mailowy podany przez Użytkownika.
Następnie Użytkownik klika na adres url, który otrzymał na adres skrzynki pocztowej (sprawdzane jest czy w bazie istnieje Użytkownik z takim tokenem)
i przechodzi do ustalania hasła do konta. Jeśli wszystko się zgadza token jest 'nullowany' hasło szyfrowane a Użytkownik zapisywany 
do bazy danych. Po zalogowaniu Użytkownik ma możliwość uzupełnienia swoich danych w zakładce Profil znajdującej sie w menu nawigacyjnym.
Wszystkie czynności związane z rejestracją, logowaniem i zmianą danych Użytkownika są odpowiednio walidowane i obsługują i18n (przy pomocy messages.properties).</p>

Diagram modelu bazy danych wykorzystywanej w aplikacji:
![sah_db_diagram](https://user-images.githubusercontent.com/20265160/34764530-1c8bb542-f5ef-11e7-9dea-78ca59d6d1b3.png)
<p>Jak widać na załączonym diagramie tabela users jest połączona relacją 1:1 z tabelą users_details, a ich klucze główne są zależne od siebie
tzn. users_details wykorzystuje klucz główny tabeli users (zatem encja users_details jest zależna od encji users i nie może bez niej istnieć. Tabele body_type, frequency_of_activity, sex są połączone z tabelą users_detals niezależną relacją 1:1 (usunięcia którejś z nich nie skutkuje usunięciem encji users_details). Jako, że Użytkownik może posiadać wiele ról - tabele users i roles są połączone sobią relacją M:N. Model bazy jest znormalizowany do III postaci i korzysta z tabel słownikowych.</p>

<p>Aplikacja podzielona jest na trzy warstwy - zgodnie z koncepcją Model View Controller. Warstwa Model dzieli się na pakiety Entities, Services i Daos. Klasy w pakiecie Entities mapowane są na relacje bazy danych przy pomocy Hibernate (technologia odpowiada również za walidacje niektórych pól).Klasy z pakietu Daos również wykorzystują Hibernate - korzystają z sesji, hql oraz mechanizmów do tworzenia query. Projekt posiada kilka kontekstów konfiguracyjnych w celu zwiększenia przejrzystości. Np. kontekst security-context.xml umożliwia konfiguracje komponentów Spring Security. Ponadto umożliwia to uruchamianie wyszczególnionych kontekstów podczas testów automatycznych (co przyspiesza przebieg tych testów).</p>

<p>Po przemyśleniach podzieliłem obsługę wyjątków na trzy poziomy. Wyjątki rzucane przez klasy Hibernate wyłapywane są w wartswie Daos i następnie przekazywane do Services jako RepositroyException. Te z kolei wyłapywane są w warstwie Services i przekazywane dalej jako ServicesException. Zaimplementowałem taki podział z uwagi na możliwość wykorzystywania aspektów.</p>

<p>W warstwie widoku aplikacja korzysta z frameworka Thymeleaf. Umożliwia to m.in. podział plików html na fragmenty oraz niezależne przygotowywanie ich bez konieczności działania aplikacji na serwerze. Jednak gdy aplikacja jest uruchomiona na serverze to Thymeleaf pozwala na przetwarzanie atrybutów z przedrostkiem 'th:'. Np. 'th:replace' pozwala na podmiane części pliku na któryś z wcześniej przygotowanych fragmentów. Do plików html 'podpięty' jest również framework Bootstrap. Upraszcza on tworzenie front-endu i pozwala wykorzystywać przygotowane szablony kodu css i javascript.</p>

<p>Do logowania wykorzystałem bibliotekę Log4j2. Logi wypisywane są na konsoli oraz zapisywane są do pliku app.log.</p>

<p>Aplikacja obłożona jest testami. Z początku starałem się wykorzystać technikę TDD. Niestety z uwagi na braki praktyczne i teoretyczne postanowiłem korztystać z TDD tylko przy testach jednostkowych (jestem świadomy, że w takim wypadku nie można tu mówić o technice TDD :) ). Code coverage aplikacji wynosi ponad 80%.</p>

<h2>Dodatkowe informacje na temat projektu: </h2>
<ul>
  <li>Aplikacja korzysta z CharacterEncodingFilter. Ustawione kodowanie to UTF-8.</li>
  <li>Wykorzystywana jest springowa klasa JavaMailSenderImpl.</li>
  <li>Aplikacja korzysta z H2 in-memory database do przeprowadzania testów integracyjnych warstwy Dao. Buduje bazę danych na podstawie adnotacji w klasach encji i następnie wykonuje skrypt import.sql, który wypełnia bazę podstawowymi danymi.</li>
</ul>

<h2>Lista TODO:</h2>
<strong>
  <ul>
    <li>Lepszym rozwiązaniem byłoby korzystanie z czystego JPA API (EntityManager). Dałoby to niezależność od biblioteki Hibernate. Jednakże dla celów poznawczych postanowiłem "pobawić się" API Hibetnate'a.
    </li>
    <li>Należy poprawić budowę klas z pakietu Daos. Niektóre interfejsy posiadają zbyt wiele metod - łamana jest zasada SRP. To samo tyczy się Services.</li>
    <li>
      Napisać aspekty wyłapujące określone wyjątki.
    </li>
     <li>
      Lepiej wykorzystać możliwości frameworka Boostrap. Poumieszczać content w div'ach klasy 'container', 'row' i 'column'. Odpowiednio je rozmieścić i zdefiniować zachowanie tych div'ów (tzn. przystosować wygląd strony do urządzenia 'col-xs', 'col-sm', 'col-md', col-lg').
    </li>
    <li>
      Poprawić jakość niektórych testów. Uzupełnić testy integracyjne. Wypróbować testy warstwy widoku (Selenium).
    </li>
     <li>
      Log4j zapisuje wszystkie logi do jednego pliku. Należałoby rozdzielić to na kilka plików, ustalić ich wielkość, oznaczyć je datą i określić ich 'żywotność'.
    </li>
     <li>
      Wprowadzić możliwość publikowania treści na wspólną tablicę.
    </li>
    <li>
      Dodać RESTowy web service. który oblicza wskaźniki BMI, BMR.  
    </li>
     <li>
      Wykorzystać RESTowy web service pozwalający uzyskać informacje o pogodzie. Użytkownik po wpisaniu nazwy miasta będzie miał możliwość sprawdzić lokalną pogodę (Dla praktyki i poznania obsługi REST web services).  
    </li>
    <li>
      Wykorzystać wątki dla procesu wysyłania maila podczas rejestracji. Użytkownik nie będzie czekał aż system wyśle maila i zwróci odpowiedź. Mail będzie wysyłany w tle, a użytkownik od razu dostanie odpowiedź.
    </li>
    <li>
      Wprowadzić upload i pobieranie plików (np. zdjęć profilowych). Skorzystać z API jakiegoś zewnętrznego serwera.
    </li>
     <li>
      Zaimplementować chat.
    </li>
     <li>
      Zaimplementować prowizoryczny koszyk z wykorzystaniem Spring Web Flow.
    </li>
     <li>
      Napisać komentarze javadoc.
    </li>
    <li>
      Opublikować aplikację w internecie.
    </li>
  </ul>
</strong>

<h2>Jak uruchomić projekt na serwerze lokalnym?</h2>
xyz
