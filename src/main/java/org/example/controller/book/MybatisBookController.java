package org.example.controller.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.BookMybatis;
import org.example.repository.book.mybatis.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/book/mybatis")
public class MybatisBookController {
    private final BookRepository bookRepository;

    @GetMapping("show")
    public ResponseEntity<List<BookMybatis>> findALl() {
        List<BookMybatis> booklist = bookRepository.findAll();
        return ResponseEntity.ok(booklist);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<BookMybatis> findById(@PathVariable("id") Long id) {
        BookMybatis findBook = bookRepository.findById(id);

        if (findBook == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(findBook);
    }

    @PostMapping("/save")
    public ResponseEntity<BookMybatis> save(
            @RequestParam("title") String title,
            @RequestParam("author") String author
    ) {
        BookMybatis newBook = new BookMybatis(null, title, author);
        int affetedRows = bookRepository.save(newBook);

        if (affetedRows == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        int affectedRows = bookRepository.delete(id);
        if (affectedRows==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글 삭제 실패");

        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
