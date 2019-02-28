Użycie:
    
    Komponent ten został skompilowany Java 11, więc twój kod 
    także musi kompilować sie Javą 11, 
    błąd o niezgodności wersji bytecodu jest oznaką, że kompilujesz kod
    starsza wersją javy
    
    Aby rozpocząć pracę z
    - ISharedDatabase

    należy zainicjalizować ten interfejs implementacją 
    - SharedDatabaseImpl

    ISharedDatabase db = new SharedDatabaseImpl

Wymagania co do encji:

1.  Encja może posiadać typy zadeklarowane w enumie
    DataType
    jeżeli chcesz mimo wszystko w klasie posiadać klasę nie wspieraną,
    możesz oznaczyć ją adnotacją @Transient JPA
    
2.  Encja musi deklarować gettery i settery dla wszystkich pól które nie są oznaczeone
    @Transient
    
3.  Encja musi deklarować bez argumentowy konstruktor

Poszczególne metody interfejsu

1. void createTable(Class<?> tClass);


    aby móc pracować z baza, na samym początku należy stworzyć tabelę 
    wywoułując metodę createTable z parametrem typu klasy encji
    db.createTable(MojaEncja.class);

2. Object insert(Object o);

   
    metoda ta zwraca Id wrzuconego objektu, jeżeli encja posiada Id typu int 
    czy Integer to wywołanie zwróci nam int-a z numerkiem nadanego przez bazę id.
    Jeżeli encja posiada id typu String, to musimy zadbać o to, aby przed wywołaniem
    metody insertowania, pole Id było już przez nas zainicjalizowane, należy tutaj równierz
    pamiętać, że wartość ta jest unikalna i z-insertowane obiektu ze zdublowanyą
    wartościa pola Id wyrzuci wyjątek. 

3. boolean update(Object o);

    
    metoda uaktualnia obiekty znajdujace sie juz w bazie danych. 
    
4. Object select(Class<?> tClass, Object id);

    
    todo


Połączenie z bazą


    do prawidłowego połączenia się z bazą potrzebujesz:
    1. pliku application.properties w katalogu src/main/resources
    
    
   oraz zadeklarowanych w tym pliku propertisów:
   
    - database.url
    - database.user
    - database.password

np. w taki sposób:

    database.url=jdbc:sqlite:databaseName.sqlite
    database.user=
    database.password=
    zakladajac ze user i pass sa puste

Testowanie


    po kazdym testowaniu nalezy usunac wygenerowana bazę

        


