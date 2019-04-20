#strength-and-honor

Stack technologiczny:
<ul>
    <li>Spring Boot</li>
    <li>Spring Security</li>
    <li>Thymeleaf</li>
    <li>Bootstrap</li>
    <li>Hibernate</li>
    <li>MySQL</li>
    <li>JUnit, Mockito</li>
</ul>

<h2>Opis projektu</h2>
Aplikacja webowa umożliwiająca prowadzenie konta i pisanie postów. Po napisaniu posta jego treść jest automatycznie tłuamczona na inne języki.
Obecnie osługiwane są dwa języki. Pokrycie kodu testami wynosi ponad 80%.

Użytkownik może:
1.Założyć konto
    - Rejestracja odbywa się poprzez wysłanie linka aktywacyjnego na podany adres email. Po kliknięciu w link użytkownik
    przekierowany jest do aplikacji webowej w celu podania hasła. Jeśli hasło jest wystarczająca "silne" tworzone jest nowe konto.
    - Po udanej rejestracji można logować się do konta.
    - Konto może mieć dwie role - ROLE_ADMIN oraz ROLE_USER. Na ten moment każde nowe konto ma ROLE_USER, ROLE_ADMIN
    można ustawić z poziomu bazy danych.
    - ROLE_ADMIN umożliwia pisanie, edycję oraz kasowanie postów.
2.Uzupełnić profil
    - Dane takie jak wiek, waga, wzrost, płeć, typ budowy ciała, częstotliwość treningów wykorzystywane są do obliczania
    wsaźników BMI (wskaźnik masy ciała) i BMR (wskaźnik podstawowej przemiany materii).
    - Wskaźniki te obliczane są w innej aplikacji (https://github.com/Debski-A/BmrBmiWebService). Aplikacje łączą się przy pomocy protokołu SOAP.
    - Wszystkie wprowadzane dane są walidowane i mają dozwolone wartości minimalne i maksymalne.
3.Sprawdzić pogodę
    - Po wprowadzeniu nazwy miasta przy pomocy protokołu REST wysyłane jest zapytanie do OpenWeatherAPI. W odpowiedzi przychodzą wszelkie informacje
    dotyczące lokalnej pogody
4.Zarządzać postami
    - Użytkownik z ROLE_ADMIN może tworzyć, edytować i kasować posty. Posty podpisywane są przez autora i oznaczane datą oraz wyświetlane na stronie głównej.
    - Każdy zarządzany post w danym języku jest jednocześnie zarządzany w innych językach, tzn. w trakcie tworzenia, edycji posta, przed zapisaniem treści do bazy danych,
    przez HTTP zostaje wysłane zapytanie do Google Translation API z treścią danego posta, kodem kraju w którego języku napisana jest treść oraz kodem kraju w którego
    języku ma nastąpić tłumaczenie. Wynik jest rozpropagowywany na inne języki i zapisywany do bazy danych.
    - Podczas zmiany języka następuje prosta filtracja postów i wyświetlane są treści postów w danym języku.

Do zrobienia:
1.Dodać obsługę kolejnego języka
2.Umożliwić dodawanie komentarzy zalogowanym użytkownikom
3.Zaimplementować chat
