Uwagi:

TODO

- Dodaj walidację metod API tak, aby zwracały adekwatne kody błędów (np. "404 not found" kiedy dodajemy książkę z author_id nieistniejącym w bazie)
- metoda createBook w RestLibraryAPI nie wczytuje danych

Status:
Występuje problem podczas wychodzenia z aplikacji. W logu kontenera po każdym wyjściu pojawia się komunikat:
-"2022-07-25 10:09:42 22 [Warning] Aborted connection 22 to db: 'library_db' user: 'library_app' host: '172.17.0.1' (Got an error reading communication
packets)"

Pytania:

