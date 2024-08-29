package org.example.repository.book.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JpaBookRepository {
    private final EntityManager em;

    public List<Book> findAll() {
        String jpql = "select b from Book b";
        List<Book> bookList = em.createQuery(jpql, Book.class).getResultList();
        return bookList;
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    public Book save(Book newBook) {
        em.persist(newBook);
        return newBook;
    }

    public void delete(Long id) {
        Book book = findById(id);
        if (book != null) {
            em.remove(book);
        }
    }
}
