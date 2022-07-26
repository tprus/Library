package works.buddy.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import works.buddy.library.api.LibraryAPI;

@Service
public class LibraryController {
    @Autowired
    @Qualifier("restLibraryAPI")
    private LibraryAPI libraryAPI;

    // TODO Ta klasa miała być odpowiednikiem ConsoleBookManager, ale nie wiem co tu napisać
}
