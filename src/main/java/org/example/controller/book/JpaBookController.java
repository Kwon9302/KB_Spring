package org.example.controller.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Book;
import org.example.repository.book.jpa.JpaBookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Transactional
@RequestMapping("/book/jpa")
public class JpaBookController {

    private final JpaBookRepository jpaBookRepository;

    @GetMapping("show")
    public ResponseEntity<List<Book>> findALl() {
        List<Book> booklist = jpaBookRepository.findAll();
        return ResponseEntity.ok(booklist);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") Long id) {
        Book findBook = jpaBookRepository.findById(id);

        if (findBook == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(findBook);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> save(
            @RequestParam("title") String title,
            @RequestParam("author") String author
    ) {
        Book newBook = new Book(null, title, author);
        Book addedBook = jpaBookRepository.save(newBook);

        if (addedBook == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Book findBook = jpaBookRepository.findById(id);
        if (findBook==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        jpaBookRepository.delete(findBook.getId());
        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
