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
    
    2.  Encja musi deklarować gettery i settery dla wszystkich pól 
    które nie są oznaczeone
    @Transient
     
    3.  Encja musi deklarować bez argumentowy konstruktor

Poszczególne metody interfejsu


    opis zawarty w JavaDoc w interfejsie ISharedDatabase


Połączenie z baza

    do prawidłowego połączenia się z bazą potrzebujesz:
    pliku application.properties w katalogu src/main/resources
    
    oraz zadeklarowanych w tym pliku propertisów:
    
    - database.url
    - database.user
    - database.password


    np. w taki sposób:
    
    database.url=jdbc:sqlite:databaseName.sqlite
    database.user=
    database.password=
    zakladajac ze user i pass sa puste



