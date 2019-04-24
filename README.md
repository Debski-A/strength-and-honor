#strength-and-honor

Stack technologiczny:
<ul>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>Hibernate</li>
    <li>MySQL</li>
    <li>Thymeleaf</li>
    <li>Bootstrap, jQuery</li>
    <li>JUnit, Mockito</li>
</ul>

<h2>Opis projektu</h2>
<p>Aplikacja webowa umożliwiająca prowadzenie konta i pisanie postów. Po napisaniu posta jego treść jest automatycznie tłuamczona na inne języki.
Obecnie osługiwane są dwa języki. Pokrycie kodu testami wynosi ponad 80%.</p><br/>

Użytkownik może:
<ol>
    <li>Założyć konto
        <ul>
            <li>Rejestracja odbywa się poprzez wysłanie linka aktywacyjnego na podany adres email. Po kliknięciu w link użytkownik
                przekierowany jest do aplikacji webowej w celu podania hasła. Jeśli hasło jest wystarczająca "silne" tworzone jest nowe konto.</li>
            <li>Po udanej rejestracji można logować się do konta.</li>
            <li>Konto może mieć dwie role - ROLE_ADMIN oraz ROLE_USER. Na ten moment każde nowe konto ma ROLE_USER, ROLE_ADMIN
                można ustawić z poziomu bazy danych.</li>
            <li>ROLE_ADMIN umożliwia pisanie, edycję oraz kasowanie postów.</li>
        </ul>
    </li>
    <li>Uzupełnić profil
        <ul>
            <li>Dane takie jak wiek, waga, wzrost, płeć, typ budowy ciała, częstotliwość treningów wykorzystywane są do obliczania
                wsaźników BMI (wskaźnik masy ciała) i BMR (wskaźnik podstawowej przemiany materii).</li>
            <li>Wskaźniki te obliczane są w innej aplikacji (https://github.com/Debski-A/BmrBmiWebService). Aplikacje łączą się przy pomocy protokołu SOAP.</li>
            <li>Wszystkie wprowadzane dane są walidowane i mają dozwolone wartości minimalne i maksymalne.</li>
        </ul>
    </li>
    <li>Sprawdzić pogodę
        <ul>
            <li>Po wprowadzeniu nazwy miasta przy pomocy protokołu REST wysyłane jest zapytanie do OpenWeatherAPI. W odpowiedzi przychodzą wszelkie informacje
                dotyczące lokalnej pogody.</li>
        </ul>
    </li>
    <li>Zarządzać postami
        <ul>
            <li>Użytkownik z ROLE_ADMIN może tworzyć, edytować i kasować posty. Posty podpisywane są przez autora i oznaczane datą oraz wyświetlane na stronie głównej.</li>
            <li>Każdy zarządzany post w danym języku jest jednocześnie zarządzany w innych językach, tzn. w trakcie tworzenia, edycji posta, przed zapisaniem treści do bazy danych,
                przez HTTP zostaje wysłane zapytanie do Google Translation API z treścią danego posta, kodem kraju w którego języku napisana jest treść oraz kodem kraju w którego
                języku ma nastąpić tłumaczenie. Wynik jest rozpropagowywany na inne języki i zapisywany do bazy danych.</li>
            <li>Podczas zmiany języka następuje prosta filtracja postów i wyświetlane są treści postów w danym języku.</li>
        </ul>
    </li>
</ol>
Do zrobienia:
<ol>
    <li>Dodać obsługę kolejnego języka.</li>
    <li>Umożliwić dodawanie komentarzy zalogowanym użytkownikom.</li>
    <li>Zaimplementować chat.</li>
</ol>
