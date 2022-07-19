Uwagi:

- upewnij się, że masz zainstalowana openjdk 11.0.15 i w pomie też powinna być 11 ustawiona, 
- zwróć uwage na sposób tworzenia i inicjalizacji obiektów
- programuj do interfejsów, nie wiąż klas do konkretnych klas, tylko do interfejsów
- zwróć uwage na konwencje nazewnicze w Javie https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html
- unikaj wszelkich instrukcji skoku typu: goto
- zamknelismy pierwszy etap aplikacji, teraz mrozimy mastera i kolejne rzeczy robisz na branchu
dajesz mi do sprawdzenia (code review), a po zatwierdzeniu mergujesz do mastera



TODO
- napisz unit testy do klasy MemoryBookDAO
- dodaj persistent DAO, które będzie zapisywać dane w bazie MySQL


Pytania: 
- Czy poprawnie interpretuję poniższe elementy świeżo dodanego kodu?
    - Obiekt klasy ApplicationContext inicjalizowany w Main służy do tworzenia obiektów bez ręcznego definiowania zależności określonych w konstruktorze
    - Elementy opatrzone tagiem @Service albo @AutoWired są oznaczone, jako obiekty do samodzielnego tworzenia przez kontener
    - Adnotacja @PostConstruct nie jest ściśle związana ze springiem, po wyjściu z konstruktora wykonuje się automatycznie
    - W klasie LibraryConfig określamy:
      - w jakich plikach szukać obiektów oznaczonych tagami @Service i @Autowired - poprzez adnotację @ComponentScan
      - Jak tworzyć obiekty systemowe bez konstruktorów domyślnych
- W przypadku posiadania dwóch klas implementujących ten sam interfejs (np. MemoryBookDAO i MySQLBookDAO) -  jak wskazujemy która implementacja zostanie utworzona jak będziemy chcieli przez ApplicationContext wygenerować BookDAO?